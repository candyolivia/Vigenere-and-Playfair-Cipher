/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author User
 */
public class MainView extends JFrame {
    public MainView() {
	setTitle ("Vigenere and Playful Cipher");
        setSize (800,590);
        setLocation (270,60);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Window Listeners
        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to exit the program?", "Exit Program",
                JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
              dispose(); 
            }
          }
        });

        
        //Add View Panel
        Container contentPane = getContentPane();
        View view = new View();
        contentPane.add(view);
        
        /*
        About about = new About();
        Help help = new Help();
        Start start = new Start();
        Classify classify = new Classify();
        Determine detclass = new Determine();
        Hypothesis hypo = new Hypothesis();

        //OnClick About Button
        home.getAboutbtn().addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            // ... called when button clicked
                  contentPane.remove(home);
                  contentPane.add(about);
        contentPane.revalidate();
        contentPane.repaint();
        pack();
        setSize (800,590);
        setResizable(false);			  
          }
        }
        */
    }
    
    public static void main(String[] args) {
        MainView mv = new MainView();
        mv.show();
    }
}
