package auctionapp;

public class Bid {
    private double amount;
    private User bidder;

    public Bid(User bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public double getAmount() { return amount; }
    public User getBidder() { return bidder; }

    @Override
    public String toString() {
        return bidder.getName() + " bid ₹" + amount;
    }
}

