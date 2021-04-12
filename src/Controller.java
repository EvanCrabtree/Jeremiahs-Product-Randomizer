import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button generateProduct;
    @FXML
    private ChoiceBox<String> sizeChoiceBox, productChoice;
    @FXML
    private ImageView orderView,cupPic;
    @FXML
    private Label sizeProduct;
    @FXML
    private TabPane tabPane;
    @FXML
    private Text flavor1, flavor2, flavor3, flavor4,iceCream;


    private Image smallGelCup, medGelCup, medIceCup, smallIceCup, iceCupTemp, iceCupDisp, medGelDisp, medGelTemp, smallGelTemp, smallGelDisp;

    @FXML
    void createProduct(ActionEvent event) {
        Product made;
        int numFlavors=0;
        if(sizeChoiceBox.getSelectionModel().getSelectedItem().equals("Small")){
            numFlavors=3;
            flavor4.setVisible(false);
        }else{
            numFlavors=4;
            flavor4.setVisible(true);
        }
        ItalianIce ii = new ItalianIce(sizeChoiceBox.getSelectionModel().getSelectedItem(),productChoice.getSelectionModel().getSelectedItem(), getFlavors.returnFlavors(numFlavors));
        if(productChoice.getSelectionModel().getSelectedItem().equals("Gelati")){
            made = new Gelati(sizeChoiceBox.getSelectionModel().getSelectedItem(),productChoice.getSelectionModel().getSelectedItem(), ii, getFlavors.returnRandomIceCreamFlavor());
            iceCream.setVisible(true);
            iceCream.setText(((Gelati) made).getIceCream().toString());
            sizeProduct.setText(made.getSize() + " " + made.getType()+":");
            setImageGelati((Gelati) made);
        }else {
            iceCream.setVisible(false);
            setImageIce(ii);
            sizeProduct.setText(ii.getSize() + " " + ii.getType()+":");
        }
        if(!sizeChoiceBox.getSelectionModel().getSelectedItem().equals("Small")){
            flavor4.setText(ii.getFlavorArrayList().get(3).toString().toUpperCase());
            flavor4.setStroke(ii.getFlavorArrayList().get(3).getColor().darker());
        }

        cupPic.setVisible(true);
        orderView.setVisible(true);

        //sizeProduct.setText(made.getSize() + " " + made.getType()+":");
        sizeProduct.setVisible(true);




        flavor1.setVisible(true);
        flavor1.setText(ii.getFlavorArrayList().get(0).toString().toUpperCase());
        flavor1.setStroke(ii.getFlavorArrayList().get(0).getColor().darker());
        flavor2.setVisible(true);
        flavor2.setText(ii.getFlavorArrayList().get(1).toString().toUpperCase());
        flavor2.setStroke(ii.getFlavorArrayList().get(1).getColor().darker());
        flavor3.setVisible(true);
        flavor3.setText(ii.getFlavorArrayList().get(2).toString().toUpperCase());
        flavor3.setStroke(ii.getFlavorArrayList().get(2).getColor().darker());


    }
    void setImageGelati(Gelati gel){
        PixelReader pr;
        PixelWriter pw;
        WritableImage dest;
        int width, height;
        Color green = Color.rgb(0,255,0);
        Color white = Color.rgb(255,255,255);
        ItalianIce prod = gel.getIce();
        IceCream ic = gel.getIceCream();
        switch (prod.getSize()) {
            case "Small" -> {
                cupPic.setImage(smallGelCup);
                pr = smallGelTemp.getPixelReader();
                width = (int) smallGelDisp.getWidth();
                height = (int) smallGelDisp.getHeight();
                dest = new WritableImage(width, height);
                pw = dest.getPixelWriter();
                for (int x = 0; x < width; x++) {//change the pixel to match the color given in the Flavor Object
                    for (int y = 0; y < height; y++) {//for all the pixels in the image
                        if (pr.getColor(x, y).equals(green))//if it is green
                        {
                            Color c = Color.GAINSBORO;
                            if (y > 30 && y < 180) c = prod.getFlavorArrayList().get(0).getColor();
                            else if (y >= 180 && y < 288) c = prod.getFlavorArrayList().get(1).getColor();
                            else if (y > 288 && y < 399) c = prod.getFlavorArrayList().get(2).getColor();//flavor boundaries
                            pw.setColor(x, y, c);//set to flavors color
                        }else if (pr.getColor(x,y).equals(white))//if it is green
                        {
                            Color c = Color.GAINSBORO;//set to flavors color
                            if(ic.getColor()!=null) {
                                if (y > 0 && y < 172) c = ic.getColor();
                                else if (y >= 175 && y < 288) c = ic.getColor();
                                else if (y > 288 && y < 399) c = ic.getColor();//flavor boundaries
                            }else{//for swirl
                                if (y > 10 && y < 172) c = Color.web("rgb(255,255,255)");
                                else if (y >= 175 && y < 288) c = Color.web("rgb(189,163,109)");
                                else if (y > 288 && y < 399) c = Color.web("rgb(255,255,255)");//flavor boundaries
                            }
                            pw.setColor(x, y, c);//set to flavors color
                        }
                    }
                }
                orderView.setImage(dest);
            }
            case "Medium", "Large" -> {
                cupPic.setImage(medGelCup);
                pr = medGelTemp.getPixelReader();
                width = (int) medGelDisp.getWidth();
                height = (int) medGelDisp.getHeight();
                dest = new WritableImage(width, height);
                pw = dest.getPixelWriter();
                for (int x = 0; x < width; x++) {//change the pixel to match the color given in the Flavor Object
                    for (int y = 0; y < height; y++) {//for all the pixels in the image
                        if (pr.getColor(x, y).equals(green))//if it is green
                        {
                            Color c = Color.GAINSBORO;
                            if (y > 30 && y < 180) c = prod.getFlavorArrayList().get(0).getColor();
                            else if (y >= 180 && y < 255) c = prod.getFlavorArrayList().get(1).getColor();
                            else if (y > 255 && y < 327)
                                c = prod.getFlavorArrayList().get(2).getColor();//flavor boundaries
                            else if (y > 327 && y < 399)
                                c = prod.getFlavorArrayList().get(3).getColor();//flavor boundaries
                            pw.setColor(x, y, c);//set to flavors color
                        }else if (pr.getColor(x,y).equals(white))//if it is green
                        {
                            if(ic.getColor()!=null) {
                                Color c = Color.GAINSBORO;
                                if (y > 10 && y < 172) c = ic.getColor();
                                else if (y >= 175 && y < 255) c = ic.getColor();
                                else if (y > 255 && y < 321)
                                    c = ic.getColor();//flavor boundaries
                                else if (y > 321 && y < 399)
                                    c = ic.getColor();//flavor boundaries
                                pw.setColor(x, y, c);//set to flavors color
                            }else {
                                Color c = Color.GAINSBORO;
                                if (y > 10 && y < 172) c = Color.web("rgb(255,255,255)");
                                else if (y >= 175 && y < 255) c = Color.web("rgb(189,163,109)");
                                else if (y > 255 && y < 321)
                                    c = Color.web("rgb(255,255,255)");//flavor boundaries
                                else if (y > 321 && y < 399)
                                    c = Color.web("rgb(189,163,109)");
                                pw.setColor(x, y, c);//set to flavors color
                                }
                        }
                    }
                }
                orderView.setImage(dest);
            }
        }
    }
    void setImageIce(ItalianIce prod){
        PixelReader pr;
        PixelWriter pw;
        WritableImage dest;
        int width, height;
        Color green = Color.rgb(0,255,0);
        switch (prod.getSize()) {
            case "Small" -> {
                cupPic.setImage(smallIceCup);
                pr = iceCupTemp.getPixelReader();
                width = (int) iceCupDisp.getWidth();
                height = (int) iceCupDisp.getHeight();
                dest = new WritableImage(width, height);
                pw = dest.getPixelWriter();
                for (int x = 0; x < width; x++) {//change the pixel to match the color given in the Flavor Object
                    for (int y = 0; y < height; y++) {//for all the pixels in the image
                        if (pr.getColor(x, y).equals(green))//if it is green
                        {
                            Color c = Color.GAINSBORO;
                            if (y > 30 && y < 172) c = prod.getFlavorArrayList().get(0).getColor();
                            else if (y >= 175 && y < 291) c = prod.getFlavorArrayList().get(1).getColor();
                            else if (y > 291 && y < 399)
                                c = prod.getFlavorArrayList().get(2).getColor();//flavor boundaries
                            pw.setColor(x, y, c);//set to flavors color
                        }
                    }
                }
                orderView.setImage(dest);
            }
            case "Medium", "Large" -> {
                cupPic.setImage(medIceCup);
                pr = iceCupTemp.getPixelReader();
                width = (int) iceCupDisp.getWidth();
                height = (int) iceCupDisp.getHeight();
                dest = new WritableImage(width, height);
                pw = dest.getPixelWriter();
                for (int x = 0; x < width; x++) {//change the pixel to match the color given in the Flavor Object
                    for (int y = 0; y < height; y++) {//for all the pixels in the image
                        if (pr.getColor(x, y).equals(green))//if it is green
                        {
                            Color c = Color.GAINSBORO;
                            if (y > 30 && y < 172) c = prod.getFlavorArrayList().get(0).getColor();
                            else if (y >= 175 && y < 255) c = prod.getFlavorArrayList().get(1).getColor();
                            else if (y > 255 && y < 327)
                                c = prod.getFlavorArrayList().get(2).getColor();//flavor boundaries
                            else if (y > 327 && y < 399)
                                c = prod.getFlavorArrayList().get(3).getColor();//flavor boundaries
                            pw.setColor(x, y, c);//set to flavors color
                        }
                    }
                }
                orderView.setImage(dest);
            }
        }
    }
    @FXML
    void initialize() throws URISyntaxException, FileNotFoundException {
        assert generateProduct != null : "fx:id=\"generateProduct\" was not injected: check your FXML file 'TheLiteralApp.fxml'.";

        sizeChoiceBox.getItems().addAll("Small", "Medium", "Large");
        productChoice.getItems().addAll("Italian Ice", "Gelati");
        orderView.setVisible(false);
        sizeProduct.setVisible(false);
        flavor1.setVisible(false);
        flavor2.setVisible(false);
        flavor3.setVisible(false);
        flavor4.setVisible(false);
        iceCream.setVisible(false);
        cupPic.setVisible(false);

        smallIceCup = new Image("resources/SmallIceCup.png");
        iceCupTemp = new Image(getClass().getResource("/resources/IceCupTemplate.png").toURI().toString());
        iceCupDisp = new Image(getClass().getResource("/resources/IceCupTemplate.png").toURI().toString());
        medIceCup = new Image("/resources/MediumIceCup.png");
        smallGelCup = new Image("resources/SmallGelCup.png");
        smallGelDisp = new Image("resources/SmallGelTemp.png");
        smallGelTemp = new Image("resources/SmallGelTemp.png");
        medGelDisp = new Image("resources/medgeltemp.png");
        medGelTemp = new Image("resources/medgeltemp.png");
        medGelCup = new Image( "resources/MedGelCup.png");
        tabPane.getStylesheets().add("resources/fontstyle.css");
        try {
            getFlavors.getFlavor();
        }catch(IOException e){
            System.out.println("IO ERROR");
        }
    }
}
