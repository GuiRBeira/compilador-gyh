#!/bin/bash

# Cores para o terminal
CYAN='\033[0;36m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

TEST_DIR="tests/programs"

if [ -z "$1" ]; then
    clear
    echo -e "${YELLOW}Qual programa você quer mostrar para ela?${NC}"
    echo -e "${CYAN}Uso: ./scripts/show_off.sh <nome_do_arquivo>${NC}"
    echo ""
    echo -e "${GREEN}Arquivos disponíveis em $TEST_DIR:${NC}"
    ls $TEST_DIR
    exit 1
fi

FILE_PATH="$TEST_DIR/$1"

if [ ! -f "$FILE_PATH" ]; then
    echo -e "${RED}Erro: Arquivo '$FILE_PATH' não encontrado!${NC}"
    exit 1
fi

clear
echo -e "${CYAN}================================================================${NC}"
echo -e "${YELLOW}           DEMONSTRAÇÃO DO ANALISADOR LÉXICO - GYH             ${NC}"
echo -e "${CYAN}================================================================${NC}"
echo ""

echo -e "${GREEN}[+] CÓDIGO DE ENTRADA ($1):${NC}"
echo "----------------------------------------------------------------"
cat -n "$FILE_PATH"
echo "----------------------------------------------------------------"
echo ""

javac -d build src/main/java/gyh/*.java

echo -e "${GREEN}[+] TOKENS IDENTIFICADOS (DIVISÃO PERFEITA):${NC}"
echo -e "${CYAN}----------------------------------------------------------------${NC}"
printf "%-18s | %-16s | %-20s\n" "TIPO DO TOKEN" "LEXEMA" "VALOR"
echo -e "${CYAN}----------------------------------------------------------------${NC}"

# Nova lógica de processamento mais robusta para a tabela
java -cp build gyh.Main "$FILE_PATH" 2>&1 | while read -r line; do
    if [[ "$line" == "Token ["* ]]; then
        # Extrai cada campo usando regex para evitar problemas com '='
        type=$(echo "$line" | sed -n 's/.*type=\([^,]*\),.*/\1/p')
        lexeme=$(echo "$line" | sed -n 's/.*lexeme=\([^,]*\),.*/\1/p')
        intVal=$(echo "$line" | sed -n 's/.*intValue=\([^,]*\),.*/\1/p')
        realVal=$(echo "$line" | sed -n 's/.*realValue=\([^,]*\),.*/\1/p')
        strVal=$(echo "$line" | sed -n 's/.*stringValue=\([^]]*\).*/\1/p' | cut -d',' -f1)
        
        val="-"
        if [ "$intVal" != "null" ]; then val="$intVal"; fi
        if [ "$realVal" != "null" ]; then val="$realVal"; fi
        if [ "$strVal" != "null" ]; then val="\"$strVal\""; fi

        printf "%-18s | %-16s | %-20s\n" "$type" "$lexeme" "$val"
    elif [[ "$line" == "ERRO LÉXICO:"* ]]; then
        # Formata o erro para caber na tabela
        err_msg=$(echo "$line" | cut -d':' -f2-)
        printf "${RED}%-18s | %-16s | %-20s${NC}\n" "ERRO" "$err_msg" "-"
    fi
done

echo -e "${CYAN}----------------------------------------------------------------${NC}"
echo -e "${YELLOW}Análise Léxica Concluída com Sucesso!${NC}"
