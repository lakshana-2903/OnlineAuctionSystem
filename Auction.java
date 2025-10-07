package auctionapp;

import java.util.ArrayList;

public class Auction {
    private int auctionId;
    private AuctionItem item;
    private User seller;
    private ArrayList<Bid> bids = new ArrayList<>();
    private boolean isClosed = false;
    private static int count = 1;

    public Auction(AuctionItem item, User seller) {
        this.auctionId = count++;
        this.item = item;
        this.seller = seller;
    }

    public int getAuctionId() { return auctionId; }
    public AuctionItem getItem() { return item; }

    public void placeBid(Bid bid) {
        if (isClosed) {
            System.out.println("Auction closed!");
            return;
        }
        if (bids.isEmpty() && bid.getAmount() >= item.getBasePrice()) {
            bids.add(bid);
            System.out.println("Bid placed successfully!");
        } else if (!bids.isEmpty() && bid.getAmount() > getHighestBid().getAmount()) {
            bids.add(bid);
            System.out.println("Bid placed successfully!");
        } else {
            System.out.println("Bid too low!");
        }
    }

    public Bid getHighestBid() {
        if (bids.isEmpty()) return null;
        Bid high = bids.get(0);
        for (Bid b : bids)
            if (b.getAmount() > high.getAmount())
                high = b;
        return high;
    }

    public void closeAuction() {
        isClosed = true;
        if (getHighestBid() != null)
            System.out.println("Winner: " + getHighestBid().getBidder().getName() + " - ₹" + getHighestBid().getAmount());
        else
            System.out.println("No valid bids. Auction closed without winner.");
    }

    @Override
    public String toString() {
        return "Auction " + auctionId + " for " + item.getTitle() + " (Seller: " + seller.getName() + ")";
    }
}
