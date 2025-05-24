package bankrupt.core;

import bankrupt.player.*;
import bankrupt.config.GameConfigReader;
import bankrupt.utils.Logger;
import java.util.*;

public class Game {
    private static final int MAX_ROUNDS = 1000;
    private static final int STARTING_COINS = 300;
    private static final int PASS_GO_BONUS = 100;
    
    private List<Property> board;
    private List<Player> players;
    private int currentRound;
    private boolean gameEnded;
    private Player winner;
    private boolean endedByTimeout;
    
    public Game() throws GameConfigReader.InvalidConfigException {
        initializeBoard();
        initializePlayers();
        this.currentRound = 0;
        this.gameEnded = false;
        this.winner = null;
        this.endedByTimeout = false;
    }
    
    private void initializeBoard() throws GameConfigReader.InvalidConfigException {
        this.board = GameConfigReader.loadProperties("gameConfig.txt");
    }
    
    private void initializePlayers() {
        this.players = new ArrayList<>();
        players.add(new ImpulsivePlayer());
        players.add(new DemandingPlayer());
        players.add(new CautiousPlayer());
        players.add(new RandomPlayer());
        
        // Ordem aleatória dos jogadores
        Collections.shuffle(players);
    }
    
    public void play() {
        Logger.log("Iniciando nova partida...");
        
        while (!gameEnded && currentRound < MAX_ROUNDS) {
            playRound();
            currentRound++;
            
            checkGameEnd();
        }
        
        if (!gameEnded) {
            endByTimeout();
        }
        
        Logger.log("Partida finalizada em " + currentRound + " rodadas. Vencedor: " + 
                  (winner != null ? winner.getBehavior() : "Nenhum"));
    }
    
    private void playRound() {
        for (Player player : new ArrayList<>(players)) {
            if (gameEnded) break;
            
            playTurn(player);
            
            // Remove jogadores falidos
            if (player.isBankrupt()) {
                players.remove(player);
                Logger.log(player.getBehavior() + " foi eliminado (faliu)");
            }
        }
    }
    
    private void playTurn(Player player) {
        int diceRoll = Dice.roll();
        int oldPosition = getCurrentPosition(player);
        int newPosition = (oldPosition + diceRoll) % board.size();
        
        // Verifica se passou pelo início (completou uma volta)
        if (newPosition < oldPosition) {
            player.addCoins(PASS_GO_BONUS);
            Logger.log(player.getBehavior() + " passou pelo início e ganhou " + PASS_GO_BONUS + " coins");
        }
        
        Property currentProperty = board.get(newPosition);
        
        if (!currentProperty.hasOwner()) {
            // Propriedade disponível para compra
            if (player.decideBuy(currentProperty) && player.getCoins() >= currentProperty.getSalePrice()) {
                if (player.spendCoins(currentProperty.getSalePrice())) {
                    currentProperty.setOwner(player);
                    Logger.log(player.getBehavior() + " comprou propriedade por " + currentProperty.getSalePrice());
                }
            }
        } else if (!currentProperty.getOwner().equals(player)) {
            // Pagar aluguel ao dono
            int rent = currentProperty.getRentAmount();
            if (!player.spendCoins(rent)) {
                // Jogador não tem dinheiro suficiente - fica com saldo negativo
                player.addCoins(-rent); // Força o saldo negativo
                Logger.log(player.getBehavior() + " não conseguiu pagar aluguel de " + rent + " e faliu");
            } else {
                currentProperty.getOwner().addCoins(rent);
                Logger.log(player.getBehavior() + " pagou " + rent + " de aluguel para " + 
                          currentProperty.getOwner().getBehavior());
            }
        }
        
        setCurrentPosition(player, newPosition);
    }
    
    private void checkGameEnd() {
        List<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (!player.isBankrupt()) {
                activePlayers.add(player);
            }
        }
        
        if (activePlayers.size() <= 1) {
            gameEnded = true;
            winner = activePlayers.isEmpty() ? null : activePlayers.get(0);
        }
    }
    
    private void endByTimeout() {
        endedByTimeout = true;
        gameEnded = true;
        
        // Encontra o jogador com mais dinheiro
        Player richestPlayer = null;
        int maxCoins = Integer.MIN_VALUE;
        
        for (Player player : players) {
            if (!player.isBankrupt() && player.getCoins() > maxCoins) {
                maxCoins = player.getCoins();
                richestPlayer = player;
            }
        }
        
        winner = richestPlayer;
        Logger.log("Jogo terminou por timeout. Vencedor por desempate (mais coins): " + 
                  (winner != null ? winner.getBehavior() : "Nenhum"));
    }
    
    // Simula posição dos jogadores (em uma implementação real, seria um atributo)
    private final Map<Player, Integer> playerPositions = new HashMap<>();
    
    private int getCurrentPosition(Player player) {
        return playerPositions.getOrDefault(player, 0);
    }
    
    private void setCurrentPosition(Player player, int position) {
        playerPositions.put(player, position);
    }
    
    public Player getWinner() {
        return winner;
    }
    
    public int getRounds() {
        return currentRound;
    }
    
    public boolean isEndedByTimeout() {
        return endedByTimeout;
    }
}