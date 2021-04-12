import javafx.scene.paint.Color;

public class Flavor {

    private final String name;
    private final String desc;
    private final Color color;


    public Flavor(String name, String desc, Color hex){
        this.desc=desc;
        this.name=name;
        color=hex;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public Color getColor(){
        return color;
    }
    public String toString(){
        return  name;
    }

}
