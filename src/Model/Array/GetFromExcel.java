package Model.Array;

import java.awt.image.BufferedImage;


/**
 * Created by Damian on 2015-08-22.
 */
public class GetFromExcel {

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


    public GetFromExcel(Integer lp, String surname, String name, String numberCard, BufferedImage image){
        this.lp = lp;
        this.name = name;
        this.surname = surname;
        this.numberCard = numberCard;
        this.image = image;

    }

    @Override
    public String toString(){
        return getLp() + " " + getSurname() + " " + getName() + " " + getNumberCard() + " " + getImage();
    }
}


