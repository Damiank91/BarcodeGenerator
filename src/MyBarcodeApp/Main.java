package MyBarcodeApp;

import Controller.BarcodeGeneratorController;
import Model.DataBaseArray.ArrayData;
import Model.Excel.DataExcel;
import Model.PDF.PdfCreator;
import View.BarcodeView;

public class Main {

    public static void main(String[] args){

        BarcodeView view = new BarcodeView();
        DataExcel dataExcel = new DataExcel();
        ArrayData arrayData = new ArrayData();
        PdfCreator pdfCreator = new PdfCreator();

        BarcodeGeneratorController barcodeGeneratorController = new BarcodeGeneratorController(view, dataExcel, arrayData, pdfCreator);
        view.setVisible(true);

    }
}
