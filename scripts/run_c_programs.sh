#!/bin/bash

# Cores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}   Compilação e Execução dos Códigos C  ${NC}"
echo -e "${BLUE}========================================${NC}"

# Verificar se gcc está disponível
if ! command -v gcc &> /dev/null; then
    echo -e "${RED}❌ Erro: gcc não encontrado no sistema! Instale o gcc para continuar.${NC}"
    exit 1
fi

# Procurar arquivos .c
c_files=(output/*.c)
if [ ! -e "${c_files[0]}" ]; then
    echo -e "${YELLOW}⚠️ Nenhum arquivo .c gerado foi encontrado em output/.${NC}"
    echo -e "${YELLOW}Rode o compilador primeiro para gerar os arquivos .c.${NC}"
    exit 1
fi

for file in "${c_files[@]}"; do
    filename=$(basename "$file")
    binary="${file%.c}"
    
    echo -e "\n${YELLOW}Compilando $filename...${NC}"
    gcc "$file" -o "$binary"
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Compilado com sucesso! Executando...${NC}"
        
        # Executar com entradas predefinidas para os programas interativos
        if [[ "$filename" == "fatorial.c" ]]; then
            echo -e "${BLUE}  [Entrada sugerida: 5]${NC}"
            output=$(echo "5" | "$binary")
            echo -e "  Saída: $output"
        elif [[ "$filename" == "comentario.c" ]]; then
            echo -e "${BLUE}  [Entrada sugerida: 4]${NC}"
            output=$(echo "4" | "$binary")
            echo -e "  Saída: $output"
        elif [[ "$filename" == "complexo_fibonacci.c" ]]; then
            echo -e "${BLUE}  [Entrada sugerida: 6]${NC}"
            output=$(echo "6" | "$binary")
            echo -e "  Saída: $output"
        elif [[ "$filename" == "complexo_operacoes.c" ]]; then
            echo -e "${BLUE}  [Entrada sugerida: 1 5.0]${NC}"
            output=$(echo -e "1\n5.0" | "$binary")
            echo -e "  Saída:\n$output"
        elif [[ "$filename" == "calculadora.c" ]]; then
            echo -e "${BLUE}  [Entrada sugerida: 1 10.0 20.0 0]${NC}"
            output=$(echo -e "1\n10.0\n20.0\n0" | "$binary" | tail -n 20)
            echo -e "  Saída (Últimas 20 linhas):\n$output"
        else
            # Executa diretamente sem entrada ou com entrada vazia
            output=$("$binary" < /dev/null 2>&1)
            echo -e "  Saída: $output"
        fi
        
        # Remover binário após execução para manter o diretório limpo
        rm "$binary"
    else
        echo -e "${RED}❌ Falha na compilação de $filename${NC}"
    fi
done

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}   Todos os programas executados!${NC}"
echo -e "${GREEN}========================================${NC}"
