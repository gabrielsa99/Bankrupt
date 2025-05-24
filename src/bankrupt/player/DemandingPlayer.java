package bankrupt.player;

import bankrupt.core.Property;

public class DemandingPlayer extends Player {
    
    public DemandingPlayer() {
        super("Exigente");
    }
    
    @Override
    public boolean decideBuy(Property property) {
        return property.getRentAmount() > 50;
    }
}