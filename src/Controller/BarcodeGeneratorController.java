package Controller;

import Model.DataBaseArray.ArrayData;
import Model.Excel.DataExcel;
import Model.PDF.PdfCreator;
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
    private DataExcel dataExcel;
    private ArrayData arrayData;
    private PdfCreator pdfCreator;
    private File file;
    private String namePah;
    private int checker;
    private JFileChooser chooser;
    private String enterpriseName;
    private String choose = "BS";


    public BarcodeGeneratorController(BarcodeView view, DataExcel dataExcel, ArrayData arrayData, PdfCreator pdfCreator){
        this.view = view;
        this.dataExcel = dataExcel;
        this.arrayData = arrayData;
        this.pdfCreator = pdfCreator;

        this.view.addSearchExcel(new SearchExcelFile());
        this.view.showAutor(new ShowAutor());
    }

    public String getNamePah(){
        String filePah = namePah.toString();
        return filePah;
    }

    public class SearchExcelFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

                chooser = new JFileChooser();
                file = new File("C:");
                chooser.setCurrentDirectory(file);
                checker = chooser.showOpenDialog(null);

                if(checker == JFileChooser.APPROVE_OPTION){

                    namePah = chooser.getSelectedFile().toString();
                    try {

                        arrayData = dataExcel.getDataExcel(getNamePah());
                        choose = view.getSelectAgreement();
                        enterpriseName = view.getEnterpriseName();


                        pdfCreator.setPdfCreator(arrayData,choose,enterpriseName  );
                        JOptionPane.showMessageDialog(null, "Plik został przetworzony");
                        view.clearEnterpriseFileTextield("");


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Niepoprawny format pliku!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Nie został wybrany plik!");

                }
        }
    }


    public class ShowAutor implements ActionListener{
        @Override
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Author: Damian Skórka");
        }
    }

}
