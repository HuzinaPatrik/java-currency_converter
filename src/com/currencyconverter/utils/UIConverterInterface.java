package com.currencyconverter.utils;

import com.currencyconverter.core.Main;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;

public class UIConverterInterface extends JFrame {
    private double exchangeRate;
    private UIConverterInterface myFrame;
    private JLabel bottomTextLabel;
    private String valute = "";
    public UIConverterInterface(double exchangeRate, String valute) throws HeadlessException {
        super();
        this.myFrame = this;

        this.exchangeRate = exchangeRate;
        this.valute = valute;

        this.setTitle("Currency converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(500, 255);

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
        container.setBounds(0, 100, 500, 75);
        container.setLayout(null);

        /*--CONTAINER 1 INPUTBOX TEXT */

        JPanel inputBoxText = new JPanel();
        inputBoxText.setBackground(new Color(0, 0, 0));
        inputBoxText.setBounds(0, 0, 500, 25);
        inputBoxText.setLayout(new BorderLayout());

        JLabel text = new JLabel("<html><font color='white'>Gépeld be <font color='red'>mennyit</font> szeretnél váltani: </font></html>");
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

        OnlyNumberTextField inputBoxField = new OnlyNumberTextField();
        inputBoxField.setPreferredSize(new Dimension(250, 25));
        inputBox.add(inputBoxField);

        inputBoxBG.add(inputBox);
        container.add(inputBoxBG);

        /*---CONTAINER 1 INPUTBOX*/

        this.add(container);

        /*-CONTAINER*/

        /*--BOTTOM CONTAINER*/

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.black);
        bottom.setBounds(0, 175, 500, 70);
        bottom.setLayout(null);

        /*---BUTTON---*/

        JButton myButton = new JButton("Művelet végrehajtása!");
        myButton.setBounds(250 - 250/2, 0, 250, 30);
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
                String value = inputBoxField.getText().toUpperCase();

                myFrame.setSize(500, 280);

                bottomTextLabel.setText("<html><font color='white'>Eredmény: " + (Math.round((Double.parseDouble(value) * myFrame.exchangeRate) * Math.pow(10, 2)) / Math.pow(10, 2)) + " <font color='red'>" + (myFrame.valute) + "</font> </font></html>");
            }
        });

        bottom.add(myButton);

        /*--BOTTOM TEXT */

        JPanel bottomText = new JPanel();
        bottomText.setBackground(new Color(0, 0, 0));
        bottomText.setBounds(0, 35, 500, 25);
        bottomText.setLayout(new BorderLayout());

        /*Alapból legyen kicsi és üres a szöveg*/
        JLabel bottomT = new JLabel("");
        this.bottomTextLabel = bottomT;

        bottomT.setVerticalAlignment(JLabel.CENTER);
        bottomT.setHorizontalAlignment(JLabel.CENTER);
        bottomText.add(bottomT);
        bottom.add(bottomText);

        /*--BOTTOM TEXT */

        /*---BUTTON---*/

        this.add(bottom);

        /*--BOTTOM CONTAINER*/

        this.setVisible(true);
    }
}