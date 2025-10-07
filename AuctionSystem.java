package auctionapp;

import java.util.*;

public class AuctionSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> sellers = new ArrayList<>();
    static ArrayList<User> bidders = new ArrayList<>();
    static ArrayList<AuctionItem> items = new ArrayList<>();
    static ArrayList<Auction> auctions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== ONLINE AUCTION SYSTEM ===");
            System.out.println("1. Add Seller   2. Add Item  3. Create Auction  4. Register Bidder");
            System.out.println("5. Place Bid    6. Close Auction    7. View Auctions    8. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: addSeller(); break;
                case 2: addItem(); break;
                case 3: createAuction(); break;
                case 4: addBidder(); break;
                case 5: placeBid(); break;
                case 6: closeAuction(); break;
                case 7: showAuctions(); break;
                case 8: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void addSeller() {
        System.out.print("Enter seller name: ");
        String n = sc.nextLine();
        System.out.print("Enter email: ");
        String e = sc.nextLine();
        sellers.add(new User(n, e));
        System.out.println("Seller added.");
    }

    static void addBidder() {
        System.out.print("Enter bidder name: ");
        String n = sc.nextLine();
        System.out.print("Enter email: ");
        String e = sc.nextLine();
        bidders.add(new User(n, e));
        System.out.println("Bidder registered.");
    }

    static void addItem() {
        System.out.print("Enter item title: ");
        String t = sc.nextLine();
        System.out.print("Enter base price: ");
        double p = sc.nextDouble(); sc.nextLine();
        items.add(new AuctionItem(t, p));
        System.out.println("Item added.");
    }

    static void createAuction() {
        if (sellers.isEmpty() || items.isEmpty()) {
            System.out.println("Need at least one seller and one item!");
            return;
        }
        System.out.println("Select seller ID:");
        sellers.forEach(System.out::println);
        int sid = sc.nextInt(); sc.nextLine();
        User seller = sellers.stream().filter(s -> s.getId() == sid).findFirst().orElse(null);
        if (seller == null) { System.out.println("Invalid seller."); return; }

        System.out.println("Select item ID:");
        items.forEach(System.out::println);
        int iid = sc.nextInt(); sc.nextLine();
        AuctionItem item = items.stream().filter(i -> i.getItemId() == iid).findFirst().orElse(null);
        if (item == null) { System.out.println("Invalid item."); return; }

        auctions.add(new Auction(item, seller));
        System.out.println("Auction created successfully!");
    }

    static void placeBid() {
        if (bidders.isEmpty() || auctions.isEmpty()) {
            System.out.println("Need bidders and auctions first!");
            return;
        }
        System.out.println("Select Auction:");
        auctions.forEach(System.out::println);
        int aid = sc.nextInt(); sc.nextLine();
        Auction a = auctions.stream().filter(x -> x.getAuctionId() == aid).findFirst().orElse(null);
        if (a == null) { System.out.println("Invalid auction."); return; }

        System.out.println("Select bidder ID:");
        bidders.forEach(System.out::println);
        int bidid = sc.nextInt(); sc.nextLine();
        User bidder = bidders.stream().filter(b -> b.getId() == bidid).findFirst().orElse(null);
        if (bidder == null) { System.out.println("Invalid bidder."); return; }

        System.out.print("Enter bid amount: ");
        double amt = sc.nextDouble(); sc.nextLine();

        a.placeBid(new Bid(bidder, amt));
    }

    static void closeAuction() {
        System.out.println("Select Auction to close:");
        auctions.forEach(System.out::println);
        int aid = sc.nextInt(); sc.nextLine();
        Auction a = auctions.stream().filter(x -> x.getAuctionId() == aid).findFirst().orElse(null);
        if (a != null) a.closeAuction();
        else System.out.println("Invalid auction ID!");
    }

    static void showAuctions() {
        auctions.forEach(a -> {
            System.out.println(a);
            Bid high = a.getHighestBid();
            if (high != null)
                System.out.println("   Highest: " + high);
            else
                System.out.println("   No bids yet.");
        });
    }
}
