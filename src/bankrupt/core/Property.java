package bankrupt.core;

import bankrupt.player.Player;

public class Property {
    private final int salePrice;
    private final int rentAmount;
    private Player owner;
    
    public Property(int salePrice, int rentAmount) {
        this.salePrice = salePrice;
        this.rentAmount = rentAmount;
        this.owner = null;
    }
    
    public int getSalePrice() {
        return salePrice;
    }
    
    public int getRentAmount() {
        return rentAmount;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    public boolean hasOwner() {
        return owner != null;
    }
}
