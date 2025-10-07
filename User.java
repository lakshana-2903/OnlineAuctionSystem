package auctionapp;

public class User {
	    protected int id;
	    protected String name;
	    protected String email;
	    private static int count = 1;

	    public User(String name, String email) {
	        this.id = count++;
	        this.name = name;
	        this.email = email;
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }

	    @Override
	    public String toString() {
	        return id + " - " + name + " (" + email + ")";
	    }
}
