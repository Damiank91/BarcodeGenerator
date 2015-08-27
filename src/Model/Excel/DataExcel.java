package Model.Excel;

import Model.CreateBarcode.BarcodeCreator;
import Model.DataBaseArray.ArrayData;
import Model.DataBaseArray.BufferWithExcelConstructor;
import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
/**
 * Created by Damian on 2015-08-22.
 */
public class DataExcel {

    /*
    *   fields for getDataExcel method
    * */
    static XSSFRow row;
    private static Integer iteration = 1;
    public ArrayData ownArrayData;   // create a static method by my test
    public BarcodeCreator barcodeCreator;  // create a static method by my test

    private String numberCard ;
    private String name;
    private String surname;
    private BufferedImage image;


    public ArrayData getDataExcel(String path) throws IOException, Exception {

        barcodeCreator = new BarcodeCreator();
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {

            fis = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(fis);


        ownArrayData = new ArrayData();
        ownArrayData.remove();
        XSSFSheet getSheet = workbook.getSheetAt(0);

        Iterator < Row > rowIterator = getSheet.iterator();
        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:

                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        numberCard = cell.toString();
                        image = barcodeCreator.getImage(numberCard);
                        break;

                    case Cell.CELL_TYPE_STRING:
                        if (cell.getColumnIndex() == 0) {
                            name = cell.getStringCellValue();
                        } else if (cell.getColumnIndex() == 1) {
                            surname = cell.getStringCellValue();
                        } else {
                            numberCard = cell.getStringCellValue();
                            image = barcodeCreator.getImage(numberCard);
                        }
                        break;
                }
            }

            ownArrayData.getNewBufforExToEx(new BufferWithExcelConstructor(iteration, name, surname, numberCard, image));
            iteration++;

        }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidFormatException ex){

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex){
                throw new Exception();
            }

        try{
            fis.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return ownArrayData;
    }
}