package Controller;

import Model.Array.ArrayData;
import Model.Array.WriteToFile;
import Model.PdfCreator.CreatePdf;
import View.BarcodeView;
import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Damian on 2015-08-27.
 */
public class BarcodeGeneratorController {

    private BarcodeView view;
    private WriteToFile writeToFile;
    private ArrayData arrayData;
    private CreatePdf pdfCreator;
    private File file;
    private String namePah;
    private int checker;
    private JFileChooser chooser;
    private String enterpriseName;
    private String chooseAgreement = "BS";


    public BarcodeGeneratorController(BarcodeView view, WriteToFile writeToFile, ArrayData arrayData, CreatePdf pdfCreator){
        this.view = view;
        this.writeToFile = writeToFile;
        this.arrayData = arrayData;
        this.pdfCreator = pdfCreator;

        this.view.addSearchExcel(new searchExcelFile());
        this.view.showAuthor(new showAuthor());
    }

    public String getNamePah(){
        String filePah = namePah.toString();
        return filePah;
    }

    public class searchExcelFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

                chooser = new JFileChooser();
                file = new File("C:");
                chooser.setCurrentDirectory(file);
                checker = chooser.showOpenDialog(null);

                if(checker == JFileChooser.APPROVE_OPTION){

                    namePah = chooser.getSelectedFile().toString();
                    try {

                        arrayData = writeToFile.getDataExcel(getNamePah());
                        chooseAgreement = view.getSelectAgreement();
                        enterpriseName = view.getEnterpriseName();

                        pdfCreator.setPdfCreator(arrayData,chooseAgreement,enterpriseName);
                        JOptionPane.showMessageDialog(null, "Plik został przetworzony");
                        view.clearEnterpriseTextField("");

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas wyboru pliku!");
                    } catch (DocumentException e1) {
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas tworzenia pliku PDF!");
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Niepoprawny format pliku!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Nie został wybrany plik!");
                }
        }
    }


    public class showAuthor implements ActionListener{
        @Override
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Author: Damian Skórka");
        }
    }

}
