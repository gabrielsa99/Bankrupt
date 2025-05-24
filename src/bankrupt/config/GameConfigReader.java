package bankrupt.config;

import bankrupt.core.Property;
import java.io.*;
import java.util.*;

public class GameConfigReader {
    
    public static class InvalidConfigException extends Exception {
        public InvalidConfigException(String message) {
            super(message);
        }
    }
    
    public static List<Property> loadProperties(String filePath) throws InvalidConfigException {
        List<Property> properties = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null && lineNumber < 20) {
                lineNumber++;
                
                try {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length != 2) {
                        throw new InvalidConfigException(
                            "Linha " + lineNumber + " inválida: deve conter exatamente 2 números"
                        );
                    }
                    
                    int salePrice = Integer.parseInt(parts[0]);
                    int rentAmount = Integer.parseInt(parts[1]);
                    
                    if (salePrice <= 0 || rentAmount <= 0) {
                        throw new InvalidConfigException(
                            "Linha " + lineNumber + ": valores devem ser positivos"
                        );
                    }
                    
                    properties.add(new Property(salePrice, rentAmount));
                    
                } catch (NumberFormatException e) {
                    throw new InvalidConfigException(
                        "Linha " + lineNumber + ": valores devem ser números inteiros válidos"
                    );
                }
            }
            
            if (properties.size() != 20) {
                throw new InvalidConfigException(
                    "Arquivo deve conter exatamente 20 propriedades, encontradas: " + properties.size()
                );
            }
            
        } catch (FileNotFoundException e) {
            throw new InvalidConfigException("Arquivo gameConfig.txt não encontrado: " + filePath);
        } catch (IOException e) {
            throw new InvalidConfigException("Erro ao ler arquivo: " + e.getMessage());
        }
        
        return properties;
    }
}
