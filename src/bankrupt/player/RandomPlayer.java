package bankrupt.player;

import bankrupt.core.Property;
import java.util.Random;

public class RandomPlayer extends Player {
    private static final Random random = new Random();
    
    public RandomPlayer() {
        super("Aleatório");
    }
    
    @Override
    public boolean decideBuy(Property property) {
        return random.nextBoolean(); // 50% de chance
    }
}