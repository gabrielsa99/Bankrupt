package bankrupt.player;

import bankrupt.core.Property;

public class ImpulsivePlayer extends Player {
    
    public ImpulsivePlayer() {
        super("Impulsivo");
    }
    
    @Override
    public boolean decideBuy(Property property) {
        return true; // Sempre compra
    }
}