Estrutura Organizacional:

Hierarquia de pastas proposta!
Separação clara de responsabilidades entre os pacotes!

Validação e Robustez:

- Validação completa do arquivo gameConfig.txt
- Tratamento de exceções personalizadas (InvalidConfigException)
- Verificação de valores positivos e formato correto

Mecânicas do Jogo:

Tabuleiro: 20 propriedades carregadas do arquivo de configuração.
Jogadores: 4 tipos com comportamentos únicos:

Impulsivo: Compra todas as propriedades
Exigente: Compra apenas se aluguel > 50
Cauteloso: Compra apenas se sobrar ≥ 80 coins
Aleatório: 50% de chance de comprar



Regras do Jogo:

- Movimento circular baseado em dado (1-6)
- Bônus de +100 coins ao completar volta
- Sistema de compra e aluguel
- Eliminação por falência
- Timeout após 1.000 rodadas
- Critério de desempate (mais coins)

Simulação e Estatísticas:

300 partidas automáticas
Coleta de dados precisos:

Distribuição de vitórias por comportamento
Média de rodadas por partida
Contagem de timeouts
Identificação do comportamento mais vencedor