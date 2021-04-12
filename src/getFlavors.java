import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.scene.paint.Color;
import java.io.IOException;

import java.util.Random;

import java.util.ArrayList;


public class getFlavors {

  private static Elements flavors;
  private static Elements descriptions;
  private static ArrayList<Color> colorList;
  private static  ArrayList<Flavor> flavorList;


  public static void getFlavor() throws IOException  {
    flavorList=new ArrayList<>();
    colorList=new ArrayList<>();
    Document htmlDoc;
    htmlDoc = Jsoup.connect("https://jeremiahsice.com/locations/chandler-az").get();
    flavors = htmlDoc.select("div.flavor > h2:first-child");
    descriptions = htmlDoc.select("div.flavor-info > p:first-child");

    for (Element h: htmlDoc.select("h2[^data-hex]")) {
      String hex= h.attr("data-hex");
      Color c = Color.web(hex);
      colorList.add(c);
    }

    if(flavors.size() == descriptions.size()){
      for(int i=0; i < flavors.size(); i++){
        flavorList.add(new Flavor(flavors.get(i).text(),descriptions.get(i).text(), colorList.get(i)));
      }
      if(flavorList.size()==23){//checks to see if it at 23 flavors and adds candy cane
        flavorList.add(new Flavor("Candy Cane",
                "This refreshingly cool treat delivers a creamy, crunchy, minty blast with its layers of Peppermint Ice, Vanilla Soft Ice Cream, and Crushed Candy Canes",
                Color.web("rgb(217,32,43)")));
      }
    }
  }

  public static Flavor returnRandomFlavor () {
    Random rand = new Random();
    return flavorList.get(rand.nextInt(flavors.size()));
  }
  public static ArrayList<Flavor> returnFlavors(int howMany){
    Random rand = new Random();
    ArrayList<Flavor> out = new ArrayList<>();
    for(int i = 0; i <howMany;i++){
      out.add(flavorList.get(rand.nextInt(flavors.size())));
    }
    return out;
  }
  public ArrayList<Flavor> getFlavorList(){
    return flavorList;
  }

  public static IceCream returnRandomIceCreamFlavor(){
    String[] flavorArray = {"Vanilla" , "Chocolate" , "Swirl"};
    Random random = new Random();
    return new IceCream(flavorArray[random.nextInt(flavorArray.length)]);
   }


}