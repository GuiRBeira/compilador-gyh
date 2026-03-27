# Compilador GYH

[![CI Status](https://github.com/seu-usuario/compilador-gyh/actions/workflows/ci.yml/badge.svg)](https://github.com/seu-usuario/compilador-gyh/actions/workflows/ci.yml)
[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![JUnit](https://img.shields.io/badge/JUnit-5.10.0-green.svg)](https://junit.org/junit5/)
[![License](https://img.shields.io/badge/license-MIT-yellow.svg)](LICENSE)

Compilador para a linguagem **GYH**, desenvolvido como projeto da disciplina de Compiladores. A linguagem GYH é uma linguagem de programação simples baseada na linguagem ALGUMA (UFSCar), com suporte a variáveis, expressões aritméticas/relacionais, estruturas condicionais e de repetição.

## 📋 Índice

- [Linguagem GYH](#-linguagem-gyh)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
  - [Instalando Java com Jabba](#1-instalando-java-com-jabba)
  - [Instalando JUnit](#2-instalando-junit)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
  - [Compilando o Analisador Léxico](#compilando-o-analisador-léxico)
  - [Executando a Análise Léxica](#executando-a-análise-léxica)
  - [Executando os Testes](#executando-os-testes)
- [Exemplos](#-exemplos)
  - [Programa Fatorial](#programa-fatorial)
  - [Saída da Análise Léxica](#saída-da-análise-léxica)
- [Especificação da Linguagem](#-especificação-da-linguagem)
  - [Palavras-chave](#palavras-chave)
  - [Operadores](#operadores)
  - [Tokens](#tokens)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Próximos Passos](#-próximos-passos)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

## 📖 Linguagem GYH

A linguagem GYH é uma linguagem de programação didática com as seguintes características:

- **Declaração de variáveis**: inteiras (`INT`) e reais (`REAL`)
- **Expressões aritméticas**: `+`, `-`, `*`, `/`
- **Expressões relacionais**: `>`, `>=`, `<`, `<=`, `==`, `!=`
- **Atribuição**: `:=`
- **Expressões lógicas**: `E`, `OU`
- **Condicional**: `SE-ENTAO-SENAO`
- **Repetição**: `ENQTO-INI-FIM`
- **Comentários**: `#` até o fim da linha

### Exemplo de programa:

```gyh
:DEC
parametro:INT
fatorial:INT
:PROG

# Calcula o fatorial de um número inteiro
LER parametro
fatorial := parametro
SE parametro == 0 ENTAO
    fatorial := 1
SENAO
    ENQTO parametro > 1
    INI
        fatorial := fatorial * (parametro - 1)
        parametro := parametro - 1
    FIM
FIM
IMPRIMIR fatorial
```

🔧 Pré-requisitos
Linux, macOS ou Windows (com WSL recomendado)

Git (para clonar o repositório)

Curl (para instalação do jabba)

📦 Instalação
1. Instalando Java com Jabba
Recomendamos usar jabba (Java Version Manager), similar ao nvm para Node.js, para gerenciar versões do Java de forma isolada.

Linux / macOS:
```bash
# Instalar jabba
curl -sL https://github.com/Jabba-Team/jabba/raw/main/install.sh | bash && . ~/.jabba/jabba.sh

# Instalar Java 21 (LTS)
jabba install 21

# Usar Java 21
jabba use 21

# Verificar instalação
java -version
javac -version
```
Windows (PowerShell):
```powershell
# Instalar jabba
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
Invoke-Expression (Invoke-WebRequest https://github.com/Jabba-Team/jabba/raw/main/install.ps1 -UseBasicParsing).Content

# Instalar Java 21
jabba install 21

# Usar Java 21
jabba use 21
```

2. Instalando JUnit
O JUnit é necessário para executar os testes unitários. O script de testes já faz o download automático, mas você pode baixar manualmente:

```bash
mkdir -p lib
wget -O lib/junit-platform-console-standalone-1.10.0.jar \
  https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar
```

3. Clonando o repositório
```bash
git clone https://github.com/seu-usuario/compilador-gyh.git
cd compilador-gyh
📁 Estrutura do Projeto
text
compilador-gyh/
├── .github/
│   └── workflows/
│       └── ci.yml              # GitHub Actions CI
├── src/
│   ├── main/java/gyh/
│   │   ├── FR.java             # Leitor de arquivos (buffer duplo)
│   │   ├── Token.java          # Representação de token
│   │   ├── TokenType.java      # Tipos de token da linguagem GYH
│   │   └── LexicalAnalyzer.java # Analisador léxico
│   └── test/java/gyh/
│       └── LexicalAnalyzerTest.java # Testes unitários (46 testes)
├── tests/
│   └── programs/
│       ├── fatorial.gyh        # Programa exemplo
│       ├── expressao.gyh       # Expressões aritméticas
│       ├── comentario.gyh      # Teste de comentários
│       └── erros.gyh           # Casos de erro léxico
├── scripts/
│   └── run_tests.sh            # Script para executar testes localmente
├── lib/                        # Bibliotecas (JUnit)
├── .gitignore
└── README.md
```

🚀 Como Executar
## Compilando o Analisador Léxico
```bash
# Compilar todos os arquivos
javac -d build src/main/java/gyh/*.java
```
Executando a Análise Léxica
```bash
# Executar com um arquivo de teste
java -cp build gyh.LexicalAnalyzer tests/programs/fatorial.gyh
```
Executando os Testes
Via script (recomendado):
```bash
# Tornar o script executável (primeira vez)
chmod +x scripts/run_tests.sh

# Executar todos os testes
./scripts/run_tests.sh
```
Manualmente:
```bash
# Compilar os testes
javac -cp "build:lib/junit-platform-console-standalone-1.10.0.jar" \
      -d build src/test/java/gyh/*.java

# Executar os testes
java -jar lib/junit-platform-console-standalone-1.10.0.jar \
     --class-path build \
     --scan-class-path
```
📝 Exemplos
Programa Fatorial
Arquivo: tests/programs/fatorial.gyh

```gyh
:DEC
parametro:INT
fatorial:INT
:PROG

# Calcula o fatorial de um número inteiro
LER parametro
fatorial := parametro
SE parametro == 0 ENTAO
    fatorial := 1
SENAO
    ENQTO parametro > 1
    INI
        fatorial := fatorial * (parametro - 1)
        parametro := parametro - 1
    FIM
FIM
IMPRIMIR fatorial
```
Saída da Análise Léxica
```bash
$ java -cp build gyh.LexicalAnalyzer tests/programs/fatorial.gyh

Análise Léxica - Linguagem GYH
================================
Token [type=Delim, lexeme=:, intValue=null, realValue=null, stringValue=null]
Token [type=PCDec, lexeme=DEC, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=Delim, lexeme=:, intValue=null, realValue=null, stringValue=null]
Token [type=PCInt, lexeme=INT, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=Delim, lexeme=:, intValue=null, realValue=null, stringValue=null]
Token [type=PCInt, lexeme=INT, intValue=null, realValue=null, stringValue=null]
Token [type=Delim, lexeme=:, intValue=null, realValue=null, stringValue=null]
Token [type=PCProg, lexeme=PROG, intValue=null, realValue=null, stringValue=null]
Token [type=PCLer, lexeme=LER, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=Atrib, lexeme=:=, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=PCSe, lexeme=SE, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=OpRelIgual, lexeme===, intValue=null, realValue=null, stringValue=null]
Token [type=NumInt, lexeme=0, intValue=0, realValue=null, stringValue=null]
Token [type=PCEntao, lexeme=ENTAO, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=Atrib, lexeme=:=, intValue=null, realValue=null, stringValue=null]
Token [type=NumInt, lexeme=1, intValue=1, realValue=null, stringValue=null]
Token [type=PCSenao, lexeme=SENAO, intValue=null, realValue=null, stringValue=null]
Token [type=PCEnqto, lexeme=ENQTO, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=OpRelMaior, lexeme=>, intValue=null, realValue=null, stringValue=null]
Token [type=NumInt, lexeme=1, intValue=1, realValue=null, stringValue=null]
Token [type=PCIni, lexeme=INI, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=Atrib, lexeme=:=, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=OpAritMult, lexeme=*, intValue=null, realValue=null, stringValue=null]
Token [type=AbrePar, lexeme=(, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=OpAritSub, lexeme=-, intValue=null, realValue=null, stringValue=null]
Token [type=NumInt, lexeme=1, intValue=1, realValue=null, stringValue=null]
Token [type=FechaPar, lexeme=), intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=Atrib, lexeme=:=, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=parametro, intValue=null, realValue=null, stringValue=null]
Token [type=OpAritSub, lexeme=-, intValue=null, realValue=null, stringValue=null]
Token [type=NumInt, lexeme=1, intValue=1, realValue=null, stringValue=null]
Token [type=PCFim, lexeme=FIM, intValue=null, realValue=null, stringValue=null]
Token [type=PCFim, lexeme=FIM, intValue=null, realValue=null, stringValue=null]
Token [type=PCImprimir, lexeme=IMPRIMIR, intValue=null, realValue=null, stringValue=null]
Token [type=Var, lexeme=fatorial, intValue=null, realValue=null, stringValue=null]
Token [type=EOF, lexeme=EOF, intValue=null, realValue=null, stringValue=null]
```
## 📚 Especificação da Linguagem

### Palavras-chave

| Palavra | Token | Descrição |
|---------|-------|-----------|
| `DEC` | `PCDec` | Início da seção de declarações |
| `PROG` | `PCProg` | Início do programa principal |
| `INT` | `PCInt` | Tipo inteiro |
| `REAL` | `PCReal` | Tipo real |
| `LER` | `PCLer` | Comando de leitura |
| `IMPRIMIR` | `PCImprimir` | Comando de escrita |
| `SE` | `PCSe` | Condicional |
| `ENTAO` | `PCEntao` | Parte do condicional |
| `SENAO` | `PCSenao` | Parte do condicional |
| `ENQTO` | `PCEnqto` | Laço de repetição |
| `INI` | `PCIni` | Início de bloco |
| `FIM` | `PCFim` | Fim de bloco |

### Operadores

| Operador | Token | Categoria |
|----------|-------|-----------|
| `*` | `OpAritMult` | Aritmético |
| `/` | `OpAritDiv` | Aritmético |
| `+` | `OpAritSoma` | Aritmético |
| `-` | `OpAritSub` | Aritmético |
| `<` | `OpRelMenor` | Relacional |
| `<=` | `OpRelMenorIgual` | Relacional |
| `>` | `OpRelMaior` | Relacional |
| `>=` | `OpRelMaiorIgual` | Relacional |
| `==` | `OpRelIgual` | Relacional |
| `!=` | `OpRelDif` | Relacional |
| `E` | `OpBoolE` | Booleano |
| `OU` | `OpBoolOu` | Booleano |

### Tokens Especiais

| Símbolo | Token | Descrição |
|---------|-------|-----------|
| `:` | `Delim` | Delimitador |
| `:=` | `Atrib` | Atribuição |
| `(` | `AbrePar` | Parêntese esquerdo |
| `)` | `FechaPar` | Parêntese direito |
| `#` | (ignorado) | Comentário até o fim da linha |

# 🛠 Tecnologias Utilizadas
Tecnologia	Versão	Finalidade
Java	21 LTS	Linguagem de implementação
JUnit	5.10.0	Testes unitários
GitHub Actions	-	CI/CD
Jabba	-	Gerenciador de versões Java

# 📈 Próximos Passos
Análise Sintática (Parser)

Análise Semântica (Type Checker)

Geração de Código (Linguagem C)

Otimizações básicas