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

# Compilar
echo -e "\n${YELLOW}[1/3] Compilando o analisador léxico...${NC}"
javac -d build src/main/java/gyh/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Falha na compilação!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Compilação bem-sucedida!${NC}"

# Baixar JUnit se não existir
if [ ! -f lib/junit-platform-console-standalone-1.10.0.jar ]; then
    echo -e "\n${YELLOW}Baixando JUnit...${NC}"
    mkdir -p lib
    wget -q -O lib/junit-platform-console-standalone-1.10.0.jar \
        https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar
fi

# Compilar testes
echo -e "\n${YELLOW}[2/3] Compilando os testes...${NC}"
javac -cp "build:lib/junit-platform-console-standalone-1.10.0.jar" \
    -d build src/test/java/gyh/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Falha na compilação dos testes!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Testes compilados!${NC}"

# Executar testes
echo -e "\n${YELLOW}[3/3] Executando os testes...${NC}"
echo -e "${BLUE}----------------------------------------${NC}"

java -jar lib/junit-platform-console-standalone-1.10.0.jar \
    --class-path build \
    --scan-class-path \
    --include-classname '.*Test' \
    --details tree

# Testes com programas de exemplo
echo -e "\n${YELLOW}Testando programas de exemplo...${NC}"
echo -e "${BLUE}----------------------------------------${NC}"

for file in tests/programas/*.gyh; do
    if [ -f "$file" ]; then
        echo -n "  Testando $(basename "$file")... "
        java -cp build LexicalAnalyzer "$file" > /dev/null 2>&1
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✓ OK${NC}"
        else
            echo -e "${RED}✗ FALHOU${NC}"
        fi
    fi
done

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}   Todos os testes concluídos!${NC}"
echo -e "${GREEN}========================================${NC}"