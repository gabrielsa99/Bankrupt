package bankrupt.player;

import bankrupt.core.Property;

public abstract class Player {
    protected int coins;
    protected final String behavior;
    
    public Player(String behavior) {
        this.coins = 300;
        this.behavior = behavior;
    }
    
    public abstract boolean decideBuy(Property property);
    
    public int getCoins() {
        return coins;
    }
    
    public void addCoins(int amount) {
        this.coins += amount;
    }
    
    public boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        }
        return false;
    }
    
    public String getBehavior() {
        return behavior;
    }
    
    public boolean isBankrupt() {
        return coins < 0;
    }
}