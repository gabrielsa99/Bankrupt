package bankrupt.player;

import bankrupt.core.Property;

public class CautiousPlayer extends Player {
    
    public CautiousPlayer() {
        super("Cauteloso");
    }
    
    @Override
    public boolean decideBuy(Property property) {
        return (coins - property.getSalePrice()) >= 80;
    }
}