package View;

import Model.DataBaseArray.ArrayData;
import Model.Excel.DataExcel;
import Model.PDF.PdfCreator;
import com.itextpdf.text.DocumentException;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Damian on 2015-08-23.
 */
public class BarcodeView extends JFrame {

    private JButton searchButton;
    private JTextField getEnterprise;
    private JRadioButton BsRadioButton;
    private JRadioButton FsRadioButton;
    private JLabel version;
    private ButtonGroup buttonGroup;
    private JMenuBar jMenuBar;
    private JMenu application;
    private JMenuItem jMenuItem;


    public BarcodeView(){

        /**
         *  Create a new look of the frame
         */

        try{
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals((info.getName()))){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 200, 400, 380);
        this.setTitle("Barcode Generator v0.5");
        setLayout(null);


        /**
         * Create a menu bar
         */
        jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);

        application = new JMenu("Application");
        jMenuBar.add(application);

        jMenuItem = new JMenuItem("Author");
        application.add(jMenuItem);


        /**
         * Create a components in panel
         */

        JLabel titleLabel = new JLabel("Barcode Generator");
        titleLabel.setBounds(130, 30, 200, 15);
        this.add(titleLabel);


        JLabel enterpriseLabel = new JLabel("Nazwa i siedziba firmy:");
        enterpriseLabel.setBounds(120, 80, 150, 15);
        this.add(enterpriseLabel);


        getEnterprise = new JTextField(20);
        getEnterprise.setBounds(20, 110, 330, 30);
        this.add(getEnterprise);

        version = new JLabel("Umowa:");
        version.setBounds(110, 170, 50, 30);
        this.add(version);



       /**
        *   Create the radio buttons
        *
        * */
        BsRadioButton = new JRadioButton("BS");
        BsRadioButton.setBounds(190, 155, 60, 60);
        BsRadioButton.setSelected(true);
        this.add(BsRadioButton);


        FsRadioButton = new JRadioButton("FS");
        FsRadioButton.setBounds(250, 155, 60, 60);
        this.add(FsRadioButton);


        /**
         *  Group the radio buttons
         * */
        buttonGroup = new ButtonGroup();
        buttonGroup.add(BsRadioButton);
        buttonGroup.add(FsRadioButton);


        /**
         * Add button to JPanel
         * */
        searchButton = new JButton("Przeglądaj");
        searchButton.setBounds(135, 220, 100, 35);
        this.add(searchButton);

        this.add(panel);

    }


    public String getEnterpriseName(){
        return getEnterprise.getText();
    }

    public String getSelectAgreement(){
        String agreement;
        if (BsRadioButton.isSelected()){
            agreement = "BS";
        } else {
            agreement = "FS";
        }
        return agreement;
    }

    public void clearEnterpriseFileTextield(String text) {

        getEnterprise.setText(text);
    }

    public void addSearchExcel(ActionListener listenSearchExcel){
        searchButton.addActionListener(listenSearchExcel);
    }

    public void showAutor (ActionListener actionListener) {
        jMenuItem.addActionListener(actionListener);
    }
}




