package Model.PDF;

import Model.DataBaseArray.ArrayData;
import View.BarcodeView;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Damian on 2015-08-22.
 */
public class PdfCreator {


    private String BSfirstHeadline = "Administratorem danych osobowych użytkowników kart programu MultiSport jest Benefit " +
            "Systems SA z siedzibą w Warszawie (00-097) przy ul. Fredry 6 („BS”). Twoje dane osobowe pozyskane zostały za " +
            "pośrednictwem pracodawcy na podstawie zawartej z BS umowy o świadczenie usług i wykorzystywane są do celów " +
            "realizacji tej umowy. Podanie jakichkolwiek danych osobowych jest dobrowolne, jednak ich brak uniemożliwia " +
            "prawidłową realizację umowy związanej z programem MultiSport. Masz prawo dostępu do treści swoich danych oraz " +
            "ich poprawiania, żądania zaprzestania ich przetwarzania ze względu na swoją szczególną sytuację, a także wniesienia " +
            "sprzeciwu, zgodnie z ustawą o ochronie danych osobowych.";


    private String FSfirstHeadline = "Administratorem danych osobowych użytkowników kart programu MultiSport jest FitSport Polska S.A. z " +
            "siedzibą w Warszawie(00-099) przy ul. Canaletta 4 („FSP”). Twoje dane osobowe pozyskane zostały za pośrednictwem pracodawcy na " +
            "podstawie zawartej z FSP umowy o świadczenie usług i wykorzystywane są do celów realizacji tej umowy. Podanie jakichkolwiek" +
            " danych osobowych jest dobrowolne, jednak ich brak uniemożliwia prawidłową realizację umowy związanej z programem MultiSport. " +
            "Masz prawo dostępu do treści swoich danych oraz ich poprawiania, żądania zaprzestania ich przetwarzania ze względu na swoją " +
            "szczególną sytuację, a także wniesienia sprzeciwu, zgodnie z ustawą o ochronie danych osobowych.";


    private String BSsecondHeadline = "Wyrażam zgodę na przetwarzanie moich danych osobowych przez Benefit Systems SA z " +
            "siedzibą w Warszawie przy ul. Fredry 6, zgodnie z ustawą z dnia 29 sierpnia 1997 r. o ochronie danych osobowych, " +
            "w celu korzystania z karty w ramach programu MultiSport.";

    private String FSsecondHeadline = "Wyrażam zgodę na przetwarzanie moich danych osobowych przez FitSport Polska S.A.z siedzibą w Warszawie przy ul. " +
            "Canaletta 4, zgodnie z ustawą z dnia 29 sierpnia 1997 r. o ochronie danych osobowych, w celu korzystania z karty w ramach programu MultiSport.";

    private String thirdHeadline = "Nazwa i siedziba firmy, nr id: ";
    private String lp = "L.p.";
    private String name = "Imię i nazwisko pracownika";
    private String numberCard = "Numer karty";
    private String barcode = "Kod kreskowy nr karty";
    private String signature = "Data i podpis";



    public PdfCreator(ArrayData arrayData, String choose) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4,25 ,25,25,25);// ze strona A4 no i wielko�� akapit�w 25 to standard
        String headerHoose = choose;
        String coWybrac = null;

        PdfWriter.getInstance(document, new FileOutputStream("BarcodeGenerator.pdf"));
        document.open();

         /*
        *  create the UTF-8 encoding
        * */

        Font font = null;
        Font boldFont = null;
        try {
            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1250, BaseFont.EMBEDDED);

            font = new Font(baseFont, 9, Font.NORMAL);
            boldFont = new Font(baseFont, 9, Font.BOLD);


        } catch (IOException ex){
            ex.printStackTrace();
        }


         /*
        *  create the table
        * */



        PdfPTable table = new PdfPTable(5); // 5 columns
        table.setWidthPercentage(100); // tabela na cala strone
        table.setHeaderRows(4); // ile wierszy to nag��wek ( na ka�dej stronie b�d� sie pojawia�

        table.setWidths(new float[]{8, 25, 22, 40, 45}); // procentowo wielko�� kolumn

        PdfPCell cell_1 = null;
        PdfPCell cell_2 = null;
        if(headerHoose.equals("BS")){
            cell_1 = new PdfPCell(new Paragraph(BSfirstHeadline, font));
            cell_2 = new PdfPCell(new Paragraph(BSsecondHeadline, font));
        } else {
            cell_1 = new PdfPCell(new Paragraph(FSfirstHeadline, font));
            cell_2 = new PdfPCell(new Paragraph(FSsecondHeadline, font));
        }


        cell_1.setColspan(5);
        cell_1.setFixedHeight(70);
        table.addCell(cell_1);

        cell_2.setColspan(5);
        cell_2.setFixedHeight(30);
        table.addCell(cell_2);

        PdfPCell cell_3 = new PdfPCell(new Paragraph(thirdHeadline, boldFont));
        cell_3.setColspan(2);
        cell_3.setFixedHeight(18);
        table.addCell(cell_3);


        PdfPCell cell_4 = new PdfPCell(new Paragraph(BarcodeView.getEnterpriseName().toString(), boldFont)); //
        cell_4.setColspan(3);
        cell_4.setFixedHeight(18);
        table.addCell(cell_4);


        PdfPCell[] cells = new PdfPCell[5];
        cells[0] = new PdfPCell(new Paragraph(lp, font));
        cells[1] = new PdfPCell(new Paragraph(name, font));
        cells[2] = new PdfPCell(new Paragraph(numberCard, font));
        cells[3] = new PdfPCell(new Paragraph(barcode, font));
        cells[4] = new PdfPCell(new Paragraph(signature, font));
        for(int i=0; i < cells.length; i++){
            cells[i].setFixedHeight(25);
            table.addCell(cells[i]);

        }

        for(int i = 0; i< arrayData.getSize(); i++){

            PdfPCell myCell;

            myCell = new PdfPCell(Phrase.getInstance(arrayData.getArrayList.get(i).getLp().toString()));
            myCell.setFixedHeight(30);
            table.addCell(myCell);

            String surname = arrayData.getArrayList.get(i).getName()+ " " + arrayData.getArrayList.get(i).getSurname();
            myCell = new PdfPCell(new Paragraph(surname, font));
            myCell.setFixedHeight(30);
            table.addCell(myCell);


            myCell = new PdfPCell(Phrase.getInstance((arrayData.getArrayList.get(i).getNumberCard())));
            myCell.setFixedHeight(30);
            table.addCell(myCell);
            /*
            * Add barcode-image to cell
            */

            BufferedImage bufferedImage =  arrayData.getArrayList.get(i).getImage();
            Image jpeg = Image.getInstance(bufferedImage, null);


            myCell = new PdfPCell();
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            jpeg.setWidthPercentage(95);
            myCell.addElement(jpeg);
            myCell.setFixedHeight(30);
            table.addCell(myCell);

            table.addCell("");

        }

        document.add(table);
        document.close();

    }

    /*
    * rzutowanie bufferedImage na Image
    * */
    public java.awt.Image bufferedImageToImage(BufferedImage bufferedImage){
        return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
    }
}
