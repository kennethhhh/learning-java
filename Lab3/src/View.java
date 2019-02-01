public class View {
    private String sessionNumber;
    private String productNumber;
    private int price;

    public View(String sessionNumber, String productNumber, int price){
        this.sessionNumber=sessionNumber;
        this.productNumber=productNumber;
        this.price=price;
    }

    public String getSessionNumber(){return this.sessionNumber;}
    public String getProductNumber(){return this.productNumber;}
    public int getPrice(){return this.price;}
}
