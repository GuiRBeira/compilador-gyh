#!/bin/bash

# Cores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}    Instalador do Compilador GYH       ${NC}"
echo -e "${BLUE}========================================${NC}"

# 1. Verificar dependência: Java
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Erro: JRE/JDK Java não encontrado! O compilador precisa do Java para rodar.${NC}"
    exit 1
fi

# 2. Determinar comando Maven
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
if command -v mvn &> /dev/null; then
    MVN_CMD="mvn"
else
    if [ -f "$SCRIPT_DIR/.maven/bin/mvn" ]; then
        MVN_CMD="$SCRIPT_DIR/.maven/bin/mvn"
    else
        echo -e "${RED}❌ Erro: Maven não encontrado! Não foi possível construir o pacote.${NC}"
        exit 1
    fi
fi

# 3. Compilar e empacotar em JAR executável
echo -e "${YELLOW}Construindo o compilador (gerando fat JAR)...${NC}"
(cd "$SCRIPT_DIR" && $MVN_CMD clean package -DskipTests)

JAR_FILE="$SCRIPT_DIR/target/compilador-gyh-1.0-SNAPSHOT-jar-with-dependencies.jar"
if [ ! -f "$JAR_FILE" ]; then
    echo -e "${RED}❌ Erro: Falha ao construir o arquivo JAR executável!${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Compilação concluída com sucesso!${NC}"

# 4. Definir locais de instalação (Local ou Global)
GLOBAL=false
if [[ "$1" == "--global" || "$1" == "-g" ]]; then
    GLOBAL=true
fi

if [ "$GLOBAL" = true ]; then
    INSTALL_DIR="/usr/local/share/compilador-gyh"
    BIN_DIR="/usr/local/bin"
    
    echo -e "${YELLOW}Instalando globalmente (requer sudo)...${NC}"
    sudo mkdir -p "$INSTALL_DIR"
    sudo cp "$JAR_FILE" "$INSTALL_DIR/compilador-gyh.jar"
    
    echo -e "${YELLOW}Criando comando 'gyh' em $BIN_DIR...${NC}"
    sudo bash -c "cat > $BIN_DIR/gyh" <<EOF
#!/bin/bash
# Executável do compilador GYH

# Exibir ajuda se não houver argumentos
if [ \$# -lt 1 ]; then
    echo "Uso: gyh <arquivo.gyh> [-c] [-r] [--tokens]"
    echo "Para mais informações, digite: gyh --help"
    exit 1
fi

file_arg=""
compile_bin=false
run_bin=false
tokens_only=false

# Loop para parsear argumentos
for arg in "\$@"; do
    if [[ "\$arg" == "-c" || "\$arg" == "--compile" ]]; then
        compile_bin=true
    elif [[ "\$arg" == "-r" || "\$arg" == "--run" ]]; then
        compile_bin=true
        run_bin=true
    elif [[ "\$arg" == "--tokens" ]]; then
        tokens_only=true
    elif [[ "\$arg" == "-h" || "\$arg" == "--help" ]]; then
        echo "Compilador GYH"
        echo "Uso:"
        echo "  gyh <arquivo.gyh>            Compila o arquivo e gera o código C correspondente em output/"
        echo "  gyh <arquivo.gyh> -c         Compila o arquivo GYH e gera o executável binário correspondente em output/"
        echo "  gyh <arquivo.gyh> -r         Compila, gera o executável e executa-o imediatamente"
        echo "  gyh <arquivo.gyh> --tokens   Executa apenas a análise léxica e exibe a lista de tokens"
        echo "  gyh --help | -h              Exibe esta mensagem de ajuda"
        exit 0
    else
        if [ -f "\$arg" ]; then
            file_arg="\$arg"
        else
            echo "❌ Erro: Arquivo ou opção '\$arg' inválida!"
            exit 1
        fi
    fi
done

if [ -z "\$file_arg" ]; then
    echo "Uso: gyh <arquivo.gyh> [-c] [-r] [--tokens]"
    echo "Para mais informações, digite: gyh --help"
    exit 1
fi

if [ "\$compile_bin" = true ]; then
    if ! command -v gcc &> /dev/null; then
        echo "❌ Erro: gcc não encontrado no sistema! Instale o gcc para gerar e executar o binário."
        exit 1
    fi
fi

abs_file="\$(realpath "\$file_arg")"
java -jar $INSTALL_DIR/compilador-gyh.jar "\$abs_file" \$([ "\$tokens_only" = true ] && echo "--tokens")
java_exit=\$?

if [ \$java_exit -ne 0 ]; then
    exit \$java_exit
fi

if [ "\$compile_bin" = true ] && [ "\$tokens_only" = false ]; then
    input_filename=\$(basename "\$file_arg")
    if [[ "\$input_filename" == *.gyh ]]; then
        base_name="\${input_filename%.gyh}"
    else
        base_name="\$input_filename"
    fi
    
    c_file="\$(pwd)/output/\$base_name.c"
    binary_output="\$(pwd)/output/\$base_name"
    
    echo "Compilando código C com gcc..."
    gcc "\$c_file" -o "\$binary_output"
    if [ \$? -eq 0 ]; then
        echo "✓ Executável gerado com sucesso: \$binary_output"
        if [ "\$run_bin" = true ]; then
            echo -e "\n=== Executando \$base_name ==="
            "\$binary_output"
            echo -e "=========================="
        fi
    else
        echo "❌ Erro: Falha ao compilar com o gcc."
        exit 1
    fi
fi
EOF
    sudo chmod +x "$BIN_DIR/gyh"
else
    # Instalação Local (Usuário corrente)
    INSTALL_DIR="$HOME/.local/share/compilador-gyh"
    BIN_DIR="$HOME/.local/bin"
    
    echo -e "${YELLOW}Instalando localmente em $HOME/.local/...${NC}"
    mkdir -p "$INSTALL_DIR"
    mkdir -p "$BIN_DIR"
    cp "$JAR_FILE" "$INSTALL_DIR/compilador-gyh.jar"
    
    echo -e "${YELLOW}Criando comando 'gyh' em $BIN_DIR...${NC}"
    cat > "$BIN_DIR/gyh" <<EOF
#!/bin/bash
# Executável do compilador GYH

# Exibir ajuda se não houver argumentos
if [ \$# -lt 1 ]; then
    echo "Uso: gyh <arquivo.gyh> [-c] [-r] [--tokens]"
    echo "Para mais informações, digite: gyh --help"
    exit 1
fi

file_arg=""
compile_bin=false
run_bin=false
tokens_only=false

# Loop para parsear argumentos
for arg in "\$@"; do
    if [[ "\$arg" == "-c" || "\$arg" == "--compile" ]]; then
        compile_bin=true
    elif [[ "\$arg" == "-r" || "\$arg" == "--run" ]]; then
        compile_bin=true
        run_bin=true
    elif [[ "\$arg" == "--tokens" ]]; then
        tokens_only=true
    elif [[ "\$arg" == "-h" || "\$arg" == "--help" ]]; then
        echo "Compilador GYH"
        echo "Uso:"
        echo "  gyh <arquivo.gyh>            Compila o arquivo e gera o código C correspondente em output/"
        echo "  gyh <arquivo.gyh> -c         Compila o arquivo GYH e gera o executável binário correspondente em output/"
        echo "  gyh <arquivo.gyh> -r         Compila, gera o executável e executa-o imediatamente"
        echo "  gyh <arquivo.gyh> --tokens   Executa apenas a análise léxica e exibe a lista de tokens"
        echo "  gyh --help | -h              Exibe esta mensagem de ajuda"
        exit 0
    else
        if [ -f "\$arg" ]; then
            file_arg="\$arg"
        else
            echo "❌ Erro: Arquivo ou opção '\$arg' inválida!"
            exit 1
        fi
    fi
done

if [ -z "\$file_arg" ]; then
    echo "Uso: gyh <arquivo.gyh> [-c] [-r] [--tokens]"
    echo "Para mais informações, digite: gyh --help"
    exit 1
fi

if [ "\$compile_bin" = true ]; then
    if ! command -v gcc &> /dev/null; then
        echo "❌ Erro: gcc não encontrado no sistema! Instale o gcc para gerar e executar o binário."
        exit 1
    fi
fi

abs_file="\$(realpath "\$file_arg")"
java -jar $INSTALL_DIR/compilador-gyh.jar "\$abs_file" \$([ "\$tokens_only" = true ] && echo "--tokens")
java_exit=\$?

if [ \$java_exit -ne 0 ]; then
    exit \$java_exit
fi

if [ "\$compile_bin" = true ] && [ "\$tokens_only" = false ]; then
    input_filename=\$(basename "\$file_arg")
    if [[ "\$input_filename" == *.gyh ]]; then
        base_name="\${input_filename%.gyh}"
    else
        base_name="\$input_filename"
    fi
    
    c_file="\$(pwd)/output/\$base_name.c"
    binary_output="\$(pwd)/output/\$base_name"
    
    echo "Compilando código C com gcc..."
    gcc "\$c_file" -o "\$binary_output"
    if [ \$? -eq 0 ]; then
        echo "✓ Executável gerado com sucesso: \$binary_output"
        if [ "\$run_bin" = true ]; then
            echo -e "\n=== Executando \$base_name ==="
            "\$binary_output"
            echo -e "=========================="
        fi
    else
        echo "❌ Erro: Falha ao compilar com o gcc."
        exit 1
    fi
fi
EOF
    chmod +x "$BIN_DIR/gyh"
fi

echo -e "\n${GREEN}========================================${NC}"
echo -e "${GREEN}    Instalação concluída com sucesso!   ${NC}"
echo -e "${GREEN}========================================${NC}"

if [ "$GLOBAL" = true ]; then
    echo -e "Você já pode executar o comando ${BLUE}gyh${NC} de qualquer diretório."
else
    echo -e "O atalho local foi gerado em ${BLUE}$BIN_DIR/gyh${NC}."
    echo -e "Certifique-se de que ${BLUE}$BIN_DIR${NC} esteja no seu PATH (geralmente está por padrão)."
    echo -e "Para testar, recarregue seu terminal e digite: ${BLUE}gyh --help${NC}"
fi
