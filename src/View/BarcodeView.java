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

    private JButton button;
    private File file;
    private File namePah;
    private int checker;
    private JFileChooser chooser;
    private static JTextField getEnterprise;
    private ArrayData arrayData;
    private JRadioButton BsRadioButton;
    private JRadioButton FsRadioButton;
    private JLabel version;
    private ButtonGroup buttonGroup;
    private String choose = "BS";



    public BarcodeView(){

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
        this.setTitle("Barcode Generator v0.3");
        setLayout(null);




        JMenuBar jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);

        JMenu application = new JMenu("Application");
        jMenuBar.add(application);

        JMenuItem jMenuItem = new JMenuItem("Author");
        application.add(jMenuItem);
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Author: Damian Skórka");
            }
        });


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


        arrayData = new ArrayData();


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
         * Register a listener for the radio buttons
         * */
        RadioButtonActionListener actionListener = new RadioButtonActionListener();
        BsRadioButton.addActionListener(actionListener);
        FsRadioButton.addActionListener(actionListener);

        /**
         * Add button to JPanel. With choose button, user see display message
         * */
        button = new JButton("Przeglądaj");
        button.setBounds(135, 220, 100, 35);
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                file = new File("C:");
                chooser.setCurrentDirectory(file);
                checker = chooser.showOpenDialog(null);

                if(checker == JFileChooser.APPROVE_OPTION){

                    namePah = chooser.getSelectedFile();
                    try {

                        initialize();
                        JOptionPane.showMessageDialog(null, "Plik został przetworzony");
                        setGetEnterprise("");
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
        });

        this.add(panel);
    }

    public static String getEnterpriseName(){
        return getEnterprise.getText();
    }

    public String getNamePah(){
        String filePah = namePah.toString();
        return filePah;
    }


    public static void setGetEnterprise(String text) {
        getEnterprise.setText(text);
    }


    private void initialize() throws IOException, DocumentException, Exception {
        arrayData = new DataExcel().getDataExcel(this.getNamePah());
        PdfCreator pdfCreator = new PdfCreator(arrayData,choose);
    }

    public class RadioButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object zrodlo = event.getSource();

            if (zrodlo == BsRadioButton) {

                choose = new String("BS");

            } else {

                choose = new String("FS");

            }
        }

    }

    public String getChooose() {
        return choose;
    }
}


