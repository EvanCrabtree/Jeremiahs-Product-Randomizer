public class Gelati extends Product {

    private ItalianIce ii;
    private IceCream ic;

    public Gelati(String size, String type, ItalianIce ice, IceCream iceCream){
        super(size, type);
        ii= ice;
        ic= iceCream;
    }
    public IceCream getIceCream(){
        return ic;
    }
    public ItalianIce getIce(){
        return ii;
    }
}
