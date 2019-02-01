public class Buy {
    private String sessionNumber;
    private String productNumber;
    private int price;
    private int quantity;

    public Buy(String sessionNumber, String productNumber, int price, int quantity){
        this.sessionNumber=sessionNumber;
        this.productNumber=productNumber;
        this.price=price;
        this.quantity=quantity;
    }

    public String getSessionNumber(){return this.sessionNumber;}
    public String getProductNumber(){return this.productNumber;}
    public int getPrice(){return this.price;}
    public int getQuantity(){return this.quantity;}
}
