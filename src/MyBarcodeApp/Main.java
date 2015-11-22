package MyBarcodeApp;

import Controller.BarcodeGeneratorController;
import Model.Array.ArrayData;
import Model.Array.WriteToFile;
import Model.PdfCreator.CreatePdf;
import View.BarcodeView;

public class Main {

    public static void main(String[] args){

        BarcodeView view = new BarcodeView();
        WriteToFile dataExcel = new WriteToFile();
        ArrayData arrayData = new ArrayData();
        CreatePdf pdfCreator = new CreatePdf();

        BarcodeGeneratorController barcodeGeneratorController = new BarcodeGeneratorController(view, dataExcel, arrayData, pdfCreator);
        view.setVisible(true);

    }
}
