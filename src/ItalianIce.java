import java.util.ArrayList;

public class ItalianIce extends Product {
    private final ArrayList<Flavor> flavorArrayList;

    public ItalianIce(String size, String type, ArrayList<Flavor> flavors){
        super(size,type);
        flavorArrayList=flavors;
    }
    public ArrayList<Flavor> getFlavorArrayList(){
        return flavorArrayList;
    }
}
