/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import vigenere.*;
import playfair.*;

/**
 *
 * @author User
 */
public class View extends JPanel {
    private JButton encriptBtn;
    private JButton decriptBtn;
    private JButton filebtn;
    private JButton saveBtn;
    private JTextField fileChosen;
    private JTextField resultFile;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JLabel textLabel;
    private JLabel resultLabel;    
    private JTextArea result;
    private ButtonGroup cipherGroup;
    private JRadioButton vigenere = new JRadioButton("Vigenere Cipher");
    private JRadioButton vigenereExt = new JRadioButton("Vigenere Extended Cipher");
    private JRadioButton playfair = new JRadioButton("Playfair Cipher");
    private ButtonGroup resultGroup= new ButtonGroup();
    private JRadioButton defaultres = new JRadioButton("Default Result");
    private JRadioButton nospace = new JRadioButton("No Space Result");
    private JRadioButton groupFive = new JRadioButton("Group in Five Result");
    private JLabel cipherLabel;
    private JLabel resStyleLabel;
    private JLabel keyLabel;
    private JTextField keyField;
    private int cipher = 1;
    private int resStyle = 1;
    
    private String text = "";
    private String resultText = "";
    private String key = "DEFAULT";
    
    public View() {
        encriptBtn = new JButton("Encript");
        setLayout(null);
        encriptBtn.setLocation(350, 270);
        encriptBtn.setSize(120, 30);
        encriptBtn.setFocusPainted(false);
        encriptBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        decriptBtn = new JButton("Decript");
        setLayout(null);
        decriptBtn.setLocation(500, 270);
        decriptBtn.setSize(120, 30);
        decriptBtn.setFocusPainted(false);
        decriptBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        saveBtn = new JButton("Save");
        setLayout(null);
        saveBtn.setLocation(600, 520);
        saveBtn.setSize(120, 30);
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        filebtn = new JButton("Choose File");
        setLayout(null);
        filebtn.setLocation(390, 30);
        filebtn.setSize(100, 40);
        filebtn.setFocusPainted(false);
        filebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        fileChosen = new JTextField();
        fileChosen.setEditable(false);
        fileChosen.setBackground(new Color (238,238,224));
        fileChosen.setText("");
        setLayout(null);
        fileChosen.setLocation(30, 30);
        fileChosen.setSize(350, 40);
        
        resultFile = new JTextField();
        resultFile.setBackground(new Color (238,238,224));
        resultFile.setText("Result File");
        setLayout(null);
        resultFile.setLocation(500, 30);
        resultFile.setSize(250, 40);
        
        textLabel = new JLabel("Input Text");
        setLayout(null);
        textLabel.setLocation(200, 75);
        textLabel.setSize(100, 15);
        
        result = new JTextArea();
        result.setBackground(new Color (255,255,255));
        result.setFont(new Font("Arial", Font.BOLD, 12));
        setLayout(null);
        Border borderres = BorderFactory.createLineBorder(Color.BLACK);
        result.setBorder(BorderFactory.createCompoundBorder(borderres,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        result.setLocation(200, 325);
        result.setSize(550, 185);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        JScrollPane scrollres = new JScrollPane (result,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        resultLabel = new JLabel("Result");
        setLayout(null);
        resultLabel.setLocation(200, 305);
        resultLabel.setSize(100, 15);
        
        inputTextArea = new JTextArea();
        inputTextArea.setBackground(new Color (255,255,255));
        inputTextArea.setFont(new Font("Arial", Font.BOLD, 12));
        setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        inputTextArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        inputTextArea.setLocation(200, 95);
        inputTextArea.setSize(550, 165);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane (inputTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //Group of Radio Button
        //CIPHER GROUP
        cipherLabel = new JLabel("CIPHER");
        cipherLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cipherLabel.setLocation(30,200);
        cipherLabel.setSize(100,15);
        cipherGroup= new ButtonGroup();
        cipherGroup.add(vigenere);
        cipherGroup.add(vigenereExt);
        cipherGroup.add(playfair);
        setLayout(null);
        vigenere.setLocation(10,230);
        vigenere.setSize(180, 15);
        vigenereExt.setLocation(10,255);
        vigenereExt.setSize(180, 15);
        playfair.setLocation(10,280);
        playfair.setSize(180, 15);
        vigenere.setSelected(true);
        
        //RESULT STYLE GROUP
        resStyleLabel = new JLabel("CIPHERTEXT STYLE");
        resStyleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resStyleLabel.setLocation(20,400);
        resStyleLabel.setSize(180,15);
        resultGroup= new ButtonGroup();
        resultGroup.add(defaultres);
        resultGroup.add(nospace);
        resultGroup.add(groupFive);
        setLayout(null);
        defaultres.setLocation(10,430);
        defaultres.setSize(180, 15);
        nospace.setLocation(10,455);
        nospace.setSize(180, 15);
        groupFive.setLocation(10,480);
        groupFive.setSize(180, 15);
        defaultres.setSelected(true);
        
        //Key Field
        keyLabel = new JLabel("KEY");
        keyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        keyLabel.setLocation(20,100);
        keyLabel.setSize(180,15);
        
        keyField = new JTextField();
        keyField.setText(key);
        setLayout(null);
        keyField.setLocation(20, 120);
        keyField.setSize(150, 30);
        
        add(encriptBtn);
        add(decriptBtn);
        add(saveBtn);
        add(filebtn);
        add(fileChosen);
        add(inputTextArea);
        add(resultFile);
        add(textLabel);
        add(resultLabel);
        add(result);
        add(cipherLabel);
        add(vigenere);
        add(vigenereExt);
        add(playfair);
        add(resStyleLabel);
        add(defaultres);
        add(nospace);
        add(groupFive);
        add(keyField);
        add(keyLabel);

        filebtn.addActionListener (new ActionListener(){
             public void actionPerformed(ActionEvent evt) {
                     //Create a file chooser
                   JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text Files", "txt");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                       System.out.println("You choose to open this file: " +
                            chooser.getSelectedFile().getAbsolutePath());
                       fileChosen.setText(chooser.getSelectedFile().getAbsolutePath());
                       //filename = chooser.getSelectedFile().getAbsolutePath();
                       inputTextArea.setEnabled(false);
                       result.setEnabled(false);
                       
                       BufferedReader br;
                       try {
                           br = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
                           String line;
                            while ((line = br.readLine()) != null) {
                                text += line;
                            }
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (IOException ex) {
                           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                       
                        System.out.println(text);
                       
                    }
                }
           });
        
        vigenere.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            cipher = 1;
         }           
      });

        vigenereExt.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            cipher = 2;
         }           
      });
        
        playfair.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            cipher = 3;
         }           
      });
        
        defaultres.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            resStyle = 1;
         }           
      });
        
        nospace.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            resStyle = 2;
         }           
      });
        
        groupFive.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            resStyle = 3;
         }           
      });
        
        
        //OnClick Encript Button
        encriptBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            // ... called when button clicked
                if (inputTextArea.isEnabled()) {
                    text = inputTextArea.getText();
                } else if (!text.equals("")) {
                    inputTextArea.setEnabled(true);
                    result.setEnabled(true);
                    
                } else {
                    System.out.println("No input text");
                }
                
                key = keyField.getText();
                
                System.out.println(cipher + " " + resStyle);
                
                if (cipher == 1) {
                    VigenereCipher vc = new VigenereCipher();
                    vc.createVigenereTable();
                    vc.setKey(key);
                    if (resStyle == 1) {
                        resultText = vc.EncriptPlainTextSpace(text);
                    } else if (resStyle == 2) {
                        resultText = vc.EncryptPlainText(text);
                    } else if (resStyle == 3) {
                        resultText = vc.EncryptPlainTextGroup(text, 5);
                    }
                    
                } else if (cipher == 2) {
                    VigenereCipherExtended vce = new VigenereCipherExtended();
                    vce.setKey(key);
                    vce.createVigenereTable();
                    if (resStyle == 3) {
                        resultText = vce.EncryptPlainTextGroup(text, 5);
                    } else {
                        resultText = vce.EncryptPlainText(text);
                    }
                    
                } else if (cipher == 3) {
                    PlayfairCipher pc = new PlayfairCipher();
                    pc.createPlayfairTable(keyField.getText());
                    if (resStyle == 1) {
                        resultText = pc.encriptPlainTextSpace(text);
                    } else if (resStyle == 2) {
                        resultText = pc.encriptPlainText(text);
                    } else if (resStyle == 3) {
                        resultText = pc.encriptPlainText(text);
                        resultText = pc.groupCipherText(5, resultText);
                    }
                    
                }
                
                System.out.println(resultText);
                result.setText(resultText);
                
          }
        });
        
        //OnClick Decript Button
        decriptBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            // ... called when button clicked
                if (inputTextArea.isEnabled()) {
                    text = inputTextArea.getText();
                } else if (!inputTextArea.getText().equals("")) {
                    inputTextArea.setEnabled(true);
                    result.setEnabled(true);
                } else {
                    System.out.println("No input text");
                }
                
                VigenereCipher vc = new VigenereCipher();
                VigenereCipherExtended vce = new VigenereCipherExtended();
                PlayfairCipher pc = new PlayfairCipher();
                
                System.out.println(cipher + " " + resStyle);
                
                if (cipher == 1) {
                    vc.setKey(key);
                    vc.createVigenereTable();
                    if (resStyle == 3) {
                        text = text.replace(" ", "");
                    }
                    resultText = vc.DecryptCipherText(text);
                    
                } else if (cipher == 2) {
                    vce.setKey(key);
                    vce.createVigenereTable();
                    resultText = vce.DecryptCipherText(text);
                    
                } else if (cipher == 3) {
                    pc.createPlayfairTable(keyField.getText());
                    resultText = pc.decriptCipherText(text);
                            
                    
                }
                
                System.out.println(resultText);
                result.setText(resultText);
                
          }
        });
        
        //OnClick Save Button
        saveBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            // ... called when button clicked
                PrintWriter out;
              try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("cipherText.txt")));
                out.println(result.getText());
                out.close();
                resultFile.setText(System.getProperty("user.dir")+"\\cipherText.txt");
                System.out.println(resultFile.getText());
              } catch (IOException ex) {
                  Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
              }
        
          }
        });
    }
}
