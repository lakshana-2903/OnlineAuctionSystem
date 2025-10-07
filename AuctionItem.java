package auctionapp;

public class AuctionItem {
    private int itemId;
    private String title;
    private double basePrice;
    private static int count = 1;

    public AuctionItem(String title, double basePrice) {
        this.itemId = count++;
        this.title = title;
        this.basePrice = basePrice;
    }

    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() {
        return "Item " + itemId + ": " + title + " (Base ₹" + basePrice + ")";
    }
}
