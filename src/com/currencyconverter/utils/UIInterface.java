package com.currencyconverter.utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.currencyconverter.core.Main;
import org.json.JSONException;

public class UIInterface extends JFrame {

    private UIInterface myFrame;

    public UIInterface() throws HeadlessException {
        super();
        myFrame = this;
        this.setTitle("Currency converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(500, 345);

        /*-HEADER*/

        /*--HEADER LABEL*/

        ImageIcon logo = new ImageIcon("logo-white.png");
        ImageIcon smallLogo = new ImageIcon(logo.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));

        JLabel headerLabel = new JLabel();
        headerLabel.setText("Currency Converter V1.0");
        headerLabel.setForeground(Color.white);
        headerLabel.setIcon(smallLogo);
        headerLabel.setVerticalAlignment(JLabel.CENTER);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        /*--HEADER LABEL*/

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 0, 0));
        header.setBounds(0, 0, 500, 100);
        header.setLayout(new BorderLayout());

        header.add(headerLabel);
        this.add(header);

        /*-HEADER*/

        /*-CONTAINER*/

        JPanel container = new JPanel();
        container.setBackground(Color.black);
        container.setBounds(0, 100, 500, 150);
        container.setLayout(null);

        /*--CONTAINER 1 INPUTBOX TEXT */

        JPanel inputBoxText = new JPanel();
        inputBoxText.setBackground(new Color(0, 0, 0));
        inputBoxText.setBounds(0, 0, 500, 25);
        inputBoxText.setLayout(new BorderLayout());

        JLabel text = new JLabel("<html><font color='white'>Gépeld be <font color='red'>milyen</font> valutáról szeretnél váltani: </font></html>");
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        inputBoxText.add(text);
        container.add(inputBoxText);

        /*--CONTAINER 1 INPUTBOX TEXT */

        /*---CONTAINER 1 INPUTBOX*/

        JPanel inputBoxBG = new JPanel();
        inputBoxBG.setBackground(new Color(10, 10, 10));
        inputBoxBG.setBounds(0, 25, 500, 50);
        inputBoxBG.setLayout(null);

        JPanel inputBox = new JPanel();
        inputBox.setBackground(new Color(0, 0, 0));
        inputBox.setBounds(15, 10, 450, 30);
        inputBox.setLayout(new BorderLayout());

        ThreeCharTextField inputBoxField = new ThreeCharTextField();
        inputBoxField.setPreferredSize(new Dimension(250, 25));
        inputBox.add(inputBoxField);

        inputBoxBG.add(inputBox);
        container.add(inputBoxBG);

        /*---CONTAINER 1 INPUTBOX*/

        /*--CONTAINER 2 INPUTBOX TEXT */

        JPanel inputBoxText2 = new JPanel();
        inputBoxText2.setBackground(new Color(0, 0, 0));
        inputBoxText2.setBounds(0, 75, 500, 25);
        inputBoxText2.setLayout(new BorderLayout());

        JLabel text2 = new JLabel("<html><font color='white'>Gépeld be <font color='red'>milyen</font> valutára szeretnél váltani: </font></html>");
        text2.setVerticalAlignment(JLabel.CENTER);
        text2.setHorizontalAlignment(JLabel.CENTER);
        inputBoxText2.add(text2);
        container.add(inputBoxText2);

        /*--CONTAINER 2 INPUTBOX TEXT */

        /*---CONTAINER 2 INPUTBOX*/

        JPanel inputBoxBG2 = new JPanel();
        inputBoxBG2.setBackground(new Color(10, 10, 10));
        inputBoxBG2.setBounds(0, 100, 500, 50);
        inputBoxBG2.setLayout(null);

        JPanel inputBox2 = new JPanel();
        inputBox2.setBackground(new Color(0, 0, 0));
        inputBox2.setBounds(15, 10, 450, 30);
        inputBox2.setLayout(new BorderLayout());

        ThreeCharTextField inputBoxField2 = new ThreeCharTextField();
        inputBoxField2.setPreferredSize(new Dimension(250, 25));
        inputBox2.add(inputBoxField2);

        inputBoxBG2.add(inputBox2);
        container.add(inputBoxBG2);

        /*---CONTAINER 2 INPUTBOX*/

        this.add(container);

        /*-CONTAINER*/

        /*--BOTTOM CONTAINER*/

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.black);
        bottom.setBounds(0, 250, 500, 60);

        /*---BUTTON---*/

        JButton myButton = new JButton("Művelet végrehajtása!");
        myButton.setSize(250, 40);
        myButton.setPreferredSize(new Dimension(250, 30));

        // Remove the button border
        myButton.setBorder(null);

        // Set the font to something simpler
        myButton.setFont(new Font("Roboto", Font.PLAIN, 16));

        // Store the button's original color
        Color buttonColor = myButton.getBackground();

        // Add a MouseListener to the button
        myButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // Set the opacity of the button's content area to 0.0
                myButton.setContentAreaFilled(false);
                myButton.setOpaque(true);
                myButton.setBackground(new Color(220, 220, 220, 0));
                // Fade in the button
                for (int i = 0; i <= 10; i++) {
                    float opacity = (float) i / 10;
                    myButton.setBackground(new Color(220, 220, 220, (int) (opacity * 255)));
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            public void mouseExited(MouseEvent e) {
                // Set the opacity of the button's content area to 1.0
                myButton.setContentAreaFilled(true);
                myButton.setOpaque(false);
                // Fade out the button
                for (int i = 10; i >= 0; i--) {
                    float opacity = (float) i / 10;
                    myButton.setBackground(new Color(220, 220, 220, (int) (opacity * 255)));
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                // Set the background color back to the original color
                myButton.setBackground(buttonColor);
            }
        });

        // Add an ActionListener to the button
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valuteFromChange = inputBoxField.getText().toUpperCase();
                String valuteToChange = inputBoxField2.getText().toUpperCase();

                if (valuteFromChange.equals(valuteToChange)) {
                    JOptionPane.showMessageDialog(null, "Ugyanarra a valutára nem tudsz váltani!", "Error", JOptionPane.ERROR_MESSAGE, null);

                    return;
                }

                try {
                    Main.applyChange(valuteFromChange, valuteToChange, myFrame);
                } catch (JSONException | InterruptedException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bottom.add(myButton);

        /*---BUTTON---*/

        this.add(bottom);

        this.setVisible(true);

        /*--BOTTOM CONTAINER*/
    }
}
