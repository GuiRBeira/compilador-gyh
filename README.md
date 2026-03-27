# Compilador GYH

[![CI Status](https://github.com/seu-usuario/compilador-gyh/actions/workflows/ci.yml/badge.svg)](https://github.com/seu-usuario/compilador-gyh/actions/workflows/ci.yml)

## Linguagem GYH

Compilador para a linguagem GYH, desenvolvido como projeto de faculdade.

### Status
- ✅ Análise Léxica: Completa
- 🔄 Análise Sintática: Em desenvolvimento
- ⏳ Geração de Código: Pendente

### Como executar

```bash
# Compilar
javac -d build src/main/java/gyh/*.java

# Executar análise léxica
java -cp build gyh.LexicalAnalyzer tests/programs/fatorial.gyh

# Executar todos os testes
./scripts/run_tests.sh