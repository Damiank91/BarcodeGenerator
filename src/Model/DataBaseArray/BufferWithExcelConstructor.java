package Model.DataBaseArray;

import java.awt.image.BufferedImage;


/**
 * Created by Damian on 2015-08-22.
 */
public class BufferWithExcelConstructor {

    private Integer lp;
    private String name;
    private String surname;
    private String numberCard;
    private BufferedImage image;

    public String getNumberCard() {
        return numberCard;
    }
    public BufferedImage getImage() {
        return image;
    }
    public Integer getLp() {
        return lp;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }


    public BufferWithExcelConstructor(Integer lp, String name, String surname, String numberCard, BufferedImage image){
        this.lp = lp;
        this.name = name;
        this.surname = surname;
        this.numberCard = numberCard;
        this.image = image;

    }

    @Override
    public String toString(){
        return getLp() + " " + getName() + " " + getSurname() + " " + getNumberCard() + " " + getImage();
    }
}


