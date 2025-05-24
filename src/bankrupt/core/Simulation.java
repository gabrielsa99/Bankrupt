package bankrupt.core;

import bankrupt.config.GameConfigReader;
import bankrupt.utils.Logger;
import java.util.*;

public class Simulation {
    private static final int TOTAL_GAMES = 300;
    
    private Map<String, Integer> winCounts;
    private int totalRounds;
    private int timeoutGames;
    private int completedGames;
    
    public Simulation() {
        this.winCounts = new HashMap<>();
        this.totalRounds = 0;
        this.timeoutGames = 0;
        this.completedGames = 0;
        
        // Inicializa contadores
        winCounts.put("Impulsivo", 0);
        winCounts.put("Exigente", 0);
        winCounts.put("Cauteloso", 0);
        winCounts.put("Aleatório", 0);
    }
    
    public void runSimulations(int totalGames) {
        Logger.log("Iniciando " + totalGames + " simulações...");
        
        for (int i = 0; i < totalGames; i++) {
            try {
                Game game = new Game();
                game.play();
                
                collectStatistics(game);
                
                if ((i + 1) % 50 == 0) {
                    Logger.log("Progresso: " + (i + 1) + "/" + totalGames + " jogos completados");
                }
                
            } catch (GameConfigReader.InvalidConfigException e) {
                Logger.log("Erro na configuração do jogo " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                Logger.log("Erro inesperado no jogo " + (i + 1) + ": " + e.getMessage());
            }
        }
        
        printResults();
    }
    
    private void collectStatistics(Game game) {
        if (game.isEndedByTimeout()) {
            timeoutGames++;
        } else {
            totalRounds += game.getRounds();
            completedGames++;
        }
        
        if (game.getWinner() != null) {
            String behavior = game.getWinner().getBehavior();
            winCounts.put(behavior, winCounts.get(behavior) + 1);
        }
    }
    
    private void printResults() {
        System.out.println("\n=== RESULTADOS DA SIMULAÇÃO ===");
        System.out.printf("- Partidas terminadas por timeout: %d (%.2f%%)\n", 
                         timeoutGames, (timeoutGames * 100.0 / TOTAL_GAMES));
        
        if (completedGames > 0) {
            double avgRounds = (double) totalRounds / completedGames;
            System.out.printf("- Média de rodadas/partida: %.1f\n", avgRounds);
        } else {
            System.out.println("- Média de rodadas/partida: N/A (nenhum jogo completado sem timeout)");
        }
        
        System.out.println("- % de Vitórias:");
        
        // Calcula porcentagens
        List<Map.Entry<String, Integer>> sortedWins = new ArrayList<>(winCounts.entrySet());
        sortedWins.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<String, Integer> entry : sortedWins) {
            double percentage = (entry.getValue() * 100.0) / TOTAL_GAMES;
            System.out.printf("  * %s: %.2f%%\n", entry.getKey(), percentage);
        }
        
        if (!sortedWins.isEmpty()) {
            String mostWinner = sortedWins.get(0).getKey();
            System.out.println("- Comportamento mais vencedor: " + mostWinner);
        }
    }
}
