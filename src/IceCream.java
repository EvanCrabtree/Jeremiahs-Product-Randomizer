import javafx.scene.paint.Color;

public class IceCream  {
    private final String flavor;
    private final Color color;

    public IceCream(String flav){
        flavor=flav;
        switch(flav){
            case "Chocolate":
                color = Color.web("rgb(189,163,109)");
                break;
            case "Vanilla":
                color = Color.web("rgb(255,255,255)");
                break;
            default:
                color=null;
        }
    }
    public String getFlavor(){
        return flavor;
    }
    public Color getColor(){
        return color;
    }
    public String toString(){
        return flavor + " Ice Cream";
    }
}

