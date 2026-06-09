#!/bin/bash

# Cores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}   Testes do Compilador - Linguagem GYH${NC}"
echo -e "${BLUE}========================================${NC}"

# Determinar comando Maven a ser utilizado
if command -v mvn &> /dev/null; then
    MVN_CMD="mvn"
else
    if [ -f ".maven/bin/mvn" ]; then
        MVN_CMD=".maven/bin/mvn"
    else
        echo -e "${RED}❌ Maven não encontrado! Instale o Maven ou certifique-se de que o diretório local .maven/ existe.${NC}"
        exit 1
    fi
fi

# Compilar e Testar via Maven
echo -e "\n${YELLOW}[1/2] Compilando e executando testes unitários via Maven...${NC}"
$MVN_CMD clean test

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Falha nos testes do Maven!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Testes unitários concluídos com sucesso!${NC}"

# Testes com programas de exemplo
echo -e "\n${YELLOW}[2/2] Testando programas de exemplo...${NC}"
echo -e "${BLUE}----------------------------------------${NC}"

for file in tests/programs/*.gyh; do
    if [ -f "$file" ]; then
        filename=$(basename "$file")
        echo -n "  Testando $filename... "
        
        $MVN_CMD exec:java -Dexec.args="$file" -q > /dev/null 2>&1
        exit_code=$?

        if [[ "$filename" == *"erro"* ]]; then
            if [ $exit_code -ne 0 ]; then
                echo -e "${GREEN}✓ ERRO ESPERADO${NC}"
            else
                echo -e "${RED}✗ FALHOU (Erro não detectado)${NC}"
            fi
        else
            if [ $exit_code -eq 0 ]; then
                echo -e "${GREEN}✓ OK${NC}"
            else
                echo -e "${RED}✗ FALHOU (Erro inesperado)${NC}"
            fi
        fi
    fi
done

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}   Todos os testes concluídos!${NC}"
echo -e "${GREEN}========================================${NC}"