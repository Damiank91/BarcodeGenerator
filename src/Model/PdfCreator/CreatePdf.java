package Model.PdfCreator;

import Model.Array.ArrayData;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Damian on 2015-08-22. Klasa która tworzy plik pdf z danych które otrzymuje od Array
 */
public class CreatePdf {


    private String BSfirstHeadline = "Administratorem danych osobowych uzytkowników kart programu MultiSport jest Benefit Systems SA z siedziba w Warszawie (00-097)\n" +
            "przy ul. Fredry 6 („BS”). Twoje dane osobowe pozyskane zostały za posrednictwem pracodawcy na podstawie zawartej z BS umowy o\n" +
            "świadczenie usług i wykorzystywane sa do celów realizacji tej umowy. Podanie jakichkolwiek danych osobowych jest dobrowolne,\n" +
            "jednak ich brak uniemozliwia prawidłowa realizacje umowy zwiazanej z programem MultiSport. Masz prawo dostepu do tresci swoich\n" +
            "danych, ich poprawiania oraz żądania zaprzestania ich przetwarzania zgodnie z ustawą z dnia 29 sierpnia 1997 r. o ochronie\n" +
            "danych osobowych.";


    private String FSfirstHeadline = "Administratorem danych osobowych uzytkowników kart programu MultiSport jest FitSport Polska S.A. z siedziba w Warszawie(00-099)\n" +
            "przy ul. Canaletta 4 („FSP”). Twoje dane osobowe pozyskane zostały za posrednictwem pracodawcy na podstawie zawartej z FSP\n" +
            "umowy o świadczenie usług i wykorzystywane sa do celów realizacji tej umowy. Podanie jakichkolwiek danych osobowych jest\n" +
            "dobrowolne, jednak ich brak uniemozliwia prawidłowa realizacje umowy zwiazanej z programem MultiSport. Masz prawo dostepu do tresci swoich\n" +
            "danych, ich poprawiania oraz żądania zaprzestania ich przetwarzania zgodnie z ustawą z dnia 29 sierpnia 1997 r. o ochronie\n" +
            "danych osobowych.";


    private String BSsecondHeadline = "Wyrazam zgode na przetwarzanie moich danych osobowych przez Benefit Systems SA z siedziba w Warszawie przy ul. Fredry 6,\n" +
            "zgodnie z ustawa z dnia 29 sierpnia 1997 r. o ochronie danych osobowych, w celu korzystania z karty w ramach programu MultiSport.";

    private String FSsecondHeadline = "Wyrazam zgode na przetwarzanie moich danych osobowych przez FitSport Polska S.A.z siedziba w Warszawie przy ul. Canaletta 4,\n" +
            "zgodnie z ustawa z dnia 29 sierpnia 1997 r. o ochronie danych osobowych, w celu korzystania z karty w ramach programu MultiSport.";

    private String thirdHeadline = "Nazwa i siedziba firmy, nr id: ";
    private String lp = "L.p.";
    private String nameAndSurname = "Nazwisko i imię pracownika";
    private String numberCard = "Numer karty";
    private String barcode = "Kod kreskowy nr karty";
    private String signature = "Data i podpis";


    public void setPdfCreator(ArrayData arrayData, String choose, String enterpriseName) throws IOException, DocumentException {


        Document document = new Document(PageSize.A4, 25, 25, 25, 25);// A4- standardowa wielkość 25mm
        String nameDocument = enterpriseName + ".pdf"; // nazwa pliku taka jak nazwa firmy

        PdfWriter.getInstance(document, new FileOutputStream(nameDocument));
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


        } catch (IOException ex) {
            ex.printStackTrace();
        }


         /*
        *  create the table
        * */


        PdfPTable table = new PdfPTable(5); // 5 columns
        table.setWidthPercentage(100); // tabela na cala strone
        table.setHeaderRows(4); // metoda która mówi, że pierwsze 4 wiersze to nagłówki, powtarzają się na każdej stronie

        table.setWidths(new float[]{8, 25, 22, 40, 45}); // procentowo szerokość kolumn

        PdfPCell cell_1;
        PdfPCell cell_2;

        /*
        * wybór nagłówków w zależności od wyboru użytkownika w GUI
        * */

        if (choose.equals("BS")) {
            cell_1 = new PdfPCell(new Paragraph(BSfirstHeadline, font));
            cell_2 = new PdfPCell(new Paragraph(BSsecondHeadline, font));
        } else {
            cell_1 = new PdfPCell(new Paragraph(FSfirstHeadline, font));
            cell_2 = new PdfPCell(new Paragraph(FSsecondHeadline, font));
        }

        /*
         * ustawienie stylu kolumn
         */
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


        PdfPCell cell_4 = new PdfPCell(new Paragraph(enterpriseName, boldFont));
        cell_4.setColspan(3);
        cell_4.setFixedHeight(18);
        table.addCell(cell_4);

        /*
         * wpisanie nagłówkow do pdf
         */

        PdfPCell[] cells = new PdfPCell[5];
        cells[0] = new PdfPCell(new Paragraph(lp, font));
        cells[1] = new PdfPCell(new Paragraph(nameAndSurname, font));
        cells[2] = new PdfPCell(new Paragraph(numberCard, font));
        cells[3] = new PdfPCell(new Paragraph(barcode, font));
        cells[4] = new PdfPCell(new Paragraph(signature, font));
        for (int i = 0; i < cells.length; i++) {
            cells[i].setFixedHeight(25);
            table.addCell(cells[i]);

        }

        for (int i = 0; i < arrayData.getSize(); i++) {

            PdfPCell myCell;

            myCell = new PdfPCell(Phrase.getInstance(arrayData.dataArrayList.get(i).getLp().toString()));
            myCell.setFixedHeight(30);
            table.addCell(myCell);

            String surname = arrayData.dataArrayList.get(i).getSurname() + " " + arrayData.dataArrayList.get(i).getName();
            myCell = new PdfPCell(new Paragraph(surname, font));
            myCell.setFixedHeight(30);
            table.addCell(myCell);


            myCell = new PdfPCell(Phrase.getInstance((arrayData.dataArrayList.get(i).getNumberCard())));
            myCell.setFixedHeight(30);
            table.addCell(myCell);
            /*
            * dodanie barcode-image do komórki
            */

            BufferedImage bufferedImage = arrayData.dataArrayList.get(i).getImage();
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
