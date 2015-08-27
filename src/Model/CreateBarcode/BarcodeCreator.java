package Model.CreateBarcode;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;


import java.awt.image.BufferedImage;

/**
 * Created by Damian on 2015-08-22.
 */
public class BarcodeCreator {

    public BarcodeCreator(){
    }

    public BufferedImage getImage(String numberCard){
        BufferedImage image = null;
        try {
            image = BarcodeImageHandler.getImage(BarcodeFactory.createCode128(numberCard));
        } catch (BarcodeException ex){
            ex.printStackTrace();
        } catch (OutputException e) {
            e.printStackTrace();
        }
        return image;
    }
}
