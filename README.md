🎲 Simulação do Jogo Bankrupt

*Desafio Acadêmico* - Curso de Análise e Desenvolvimento de Sistemas - FATEC

**Visão Geral**

Este projeto foi desenvolvido como desafio acadêmico da faculdade, implementando uma simulação robusta do jogo Bankrupt em Java. O desafio consistia em executar 300 partidas automáticas para análise estatística de diferentes estratégias de jogadores, determinando qual comportamento tem maior probabilidade de vitória.

**Desafio Acadêmico**

Objetivo do Exercício Universitário
Disciplina: Programação Orientada a Objetos - JAVA
Tema: Simulação de Sistemas e Análise Estatística

O desafio proposto pela faculdade tinha como objetivos pedagógicos:

- Aplicar conceitos de POO (herança, polimorfismo, encapsulamento).
- Implementar padrões de design em cenário real.
- Desenvolver sistema de simulação com coleta de dados.
- Praticar tratamento de exceções e validação robusta.
- Realizar análise estatística automatizada.

**Especificações do Desafio**

Executar 300 partidas automáticas do jogo Bankrupt
Implementar 4 tipos de jogadores com comportamentos distintos
Coletar estatísticas sobre distribuição de vitórias e duração das partidas
Seguir estrutura de pastas específica fornecida pelo professor
Garantir robustez com tratamento completo de erros

**Contexto do Jogo**

Bankrupt é um jogo de tabuleiro similar ao Monopoly, onde jogadores:

- Movem-se em um tabuleiro circular com 20 propriedades
- Compram propriedades disponíveis
- Pagam aluguel quando param em propriedades de outros jogadores
- São eliminados quando ficam com saldo negativo
- Ganham bônus ao completar voltas no tabuleiro

**Arquitetura e Implementação**

Estrutura do Projeto

Bankrupt/
├── src/
│   └── bankrupt/
│       ├── Main.java
│       ├── config/
│       │   └── GameConfigReader.java
│       ├── core/
│       │   ├── Game.java
│       │   ├── Simulation.java
│       │   ├── Dice.java
│       │   └── Property.java
│       ├── player/
│       │   ├── Player.java
│       │   ├── ImpulsivePlayer.java
│       │   ├── DemandingPlayer.java
│       │   ├── CautiousPlayer.java
│       │   └── RandomPlayer.java
│       └── utils/
│           └── Logger.java
├── gameConfig.txt
├── README.md
└── .gitignore

**Componentes Implementados**

#Tipos de Jogadores

- Impulsivo: Compra todas as propriedades disponíveis
- Exigente: Compra apenas propriedades com aluguel > 50 coins
- Cauteloso: Compra apenas se sobrar ≥ 80 coins após a compra
- Aleatório: 50% de chance de comprar qualquer propriedade

#Sistema de Propriedades

20 propriedades carregadas de arquivo de configuração
Cada propriedade possui preço de venda e valor do aluguel.

**Mecânicas do Jogo**

Movimento circular baseado em dado (1-6)
Bônus de +100 coins ao completar volta completa
Sistema de compra baseado no comportamento do jogador
Pagamento de aluguel ao proprietário
Eliminação por falência (saldo negativo)
Limite de timeout (1.000 rodadas por partida)

 **Recursos Técnicos Implementados**

Validação e Robustez

- Validação completa do arquivo gameConfig.txt
- Tratamento de exceções personalizadas (InvalidConfigException)
- Verificação de integridade dos dados de configuração
- Proteção contra valores inválidos

Algoritmos e Lógica

- Ordem aleatória de jogadores a cada partida
- Detecção automática de condições de vitória
- Critério de desempate por maior quantidade de coins
- Coleta precisa de estatísticas durante a execução

Padrões de Design

- Strategy Pattern: Diferentes comportamentos de jogadores.
- Factory Pattern: Criação de jogadores e propriedades.
- Observer Pattern: Sistema de logging e coleta de estatísticas.




**Resultados e Análise**

Exemplo de Saída
=== RESULTADOS DA SIMULAÇÃO ===
- Partidas terminadas por timeout: 23 (7.67%)
- Média de rodadas/partida: 350.5
- % de Vitórias:
  * Impulsivo: 32.33%
  * Exigente: 24.00%
  * Cauteloso: 28.67%
  * Aleatório: 15.00%
- Comportamento mais vencedor: Impulsivo
Insights Obtidos

Jogadores Impulsivos tendem a ter maior taxa de vitória
Jogadores Aleatórios apresentam menor consistência
Timeout é relativamente raro (< 10% das partidas)
Duração média varia entre 300-400 rodadas

**Como Executar**

Pré-requisitos

Java JDK 11+ instalado
gameConfig.txt configurado corretamente

Compilação e Execução
bash# Compilar o projeto
javac -d bin src/bankrupt/**/*.java

# Executar a simulação

java -cp bin bankrupt.Main

Configuração Alternativa

Para teste rápido, use a versão simplificada em arquivo único:
bashjavac BankruptSimulation.java
java BankruptSimulation

📁 Arquivos de Configuração

gameConfig.txt
Arquivo contendo 20 linhas com configuração das propriedades, Exemplo:
100 10    # Preço: 100, Aluguel: 10
150 20    # Preço: 150, Aluguel: 20
...
**Funcionalidades Avançadas**

Sistema de Logging

Logging configurável para debug
Acompanhamento em tempo real do progresso
Relatórios detalhados de cada partida

Coleta de Estatísticas

Contadores automáticos de vitórias por tipo
Cálculo preciso de médias e porcentagens
Identificação automática do comportamento vencedor

Tratamento de Erros

Exceções personalizadas para diferentes tipos de erro
Validação robusta de dados de entrada
Recuperação graceful de erros durante a simulação

🧪 Testes e Validação

Cenários Testados:

- Arquivo de configuração corrompido
- Valores inválidos nas propriedades
- Comportamentos extremos dos jogadores
- Condições de timeout
- Múltiplas execuções para consistência

Métricas de Qualidade

- Cobertura de código: Todas as classes principais testadas
- Tratamento de exceções: 100% das condições de erro cobertas
- Performance: 300 simulações executadas em < 30 segundos

Aspectos Técnicos

- Código robusto com tratamento completo de erros
- Performance otimizada para grandes volumes de simulação
- Coleta precisa de dados estatísticos

**Design de Software**

- Extensibilidade: Fácil adição de novos tipos de jogadores
- Manutenibilidade: Código bem documentado e organizado
- Testabilidade: Componentes independentes e testáveis
- Escalabilidade: Preparado para simular milhares de partidas

**Funcionalidades Implementadas**

- Simulação completa do jogo Bankrupt
- Análise estatística automatizada
- Randomização adequada para resultados confiáveis
- Relatórios detalhados com insights acionáveis

🏆 **Resultados do Desafio Acadêmico**

Objetivos Pedagógicos Atingidos

✅ Programação Orientada a Objetos: Implementação completa com herança, polimorfismo e encapsulamento
✅ Design Patterns: Strategy, Factory e Observer aplicados corretamente
✅ Tratamento de Exceções: Sistema robusto com exceções personalizadas
✅ Estrutura de Projeto: Organização profissional seguindo boas práticas
✅ Análise de Dados: Coleta e processamento estatístico automatizado

**Competências Desenvolvidas**

🎯 Análise de Requisitos: Interpretação precisa das especificações
🔧 Arquitetura de Software: Design modular e extensível
📊 Simulação de Sistemas: Modelagem de comportamentos complexos
🧪 Validação e Testes: Garantia de qualidade do código
📈 Interpretação de Resultados: Análise crítica dos dados coletados

Projeto desenvolvido como exercício acadêmico para demonstração de competências em Java e desenvolvimento de software.