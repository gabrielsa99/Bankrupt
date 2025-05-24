package bankrupt;

import bankrupt.core.Simulation;
import bankrupt.utils.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            Logger.log("=== INICIANDO SIMULAÇÃO BANKRUPT ===");
            
            Simulation simulation = new Simulation();
            simulation.runSimulations(300);
            
            Logger.log("=== SIMULAÇÃO CONCLUÍDA ===");
            
        } catch (Exception e) {
            System.err.println("Erro durante a simulação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}