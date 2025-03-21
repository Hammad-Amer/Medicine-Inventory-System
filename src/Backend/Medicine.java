package Backend;

public class Medicine {
    private String name;
    private String description;
    private int price;
    private int stock;
    private String manDate;
    private String expDate;

    public Medicine(String name, String description, int price, int stock, String manDate, String expDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.manDate = manDate;
        this.expDate = expDate;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public String getManDate() { return manDate; }
    public String getExpDate() { return expDate; }
}
