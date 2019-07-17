package crosswordpuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.jpl7.Query;

public class CrosswordPuzzle extends JFrame {

    int charLimit = 7;

    Font font = new Font("Century Gothic", Font.PLAIN, 14);

    JLabel lblTitle = new JLabel("Crossword Puzzle Solver");
    JLabel lbl = new JLabel("Please Insert 6 Words That Made Up Of 7 Characters:");
    JLabel lbl1 = new JLabel("Word 1:");
    JLabel lbl2 = new JLabel("Word 2:");
    JLabel lbl3 = new JLabel("Word 3:");
    JLabel lbl4 = new JLabel("Word 4:");
    JLabel lbl5 = new JLabel("Word 5:");
    JLabel lbl6 = new JLabel("Word 6:");

    JTextField tf1 = new JTextField(10);
    JTextField tf2 = new JTextField(10);
    JTextField tf3 = new JTextField(10);
    JTextField tf4 = new JTextField(10);
    JTextField tf5 = new JTextField(10);
    JTextField tf6 = new JTextField(10);

    JButton btnSubmit = new JButton("Sumbit");
    JButton btnReset = new JButton("Reset");
    static CrosswordPuzzle frame;

    CrosswordPuzzle() {
        JPanel titlePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        titlePanel.add(lblTitle);
        titlePanel.setBackground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        lblTitle.setForeground(Color.YELLOW);

        JPanel headPanel = new JPanel();
        JPanel bodyPanel = new JPanel();

        headPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headPanel.add(lbl);
        lbl.setFont(font);
        lbl.setForeground(Color.WHITE);

        bodyPanel.setLayout(new GridLayout(6, 2, 5, 0));
        bodyPanel.add(lbl1);
        bodyPanel.add(lbl2);
        bodyPanel.add(tf1);
        bodyPanel.add(tf2);
        bodyPanel.add(lbl3);
        bodyPanel.add(lbl4);
        bodyPanel.add(tf3);
        bodyPanel.add(tf4);
        bodyPanel.add(lbl5);
        bodyPanel.add(lbl6);
        bodyPanel.add(tf5);
        bodyPanel.add(tf6);
        lbl1.setFont(font);
        lbl1.setForeground(Color.WHITE);
        lbl2.setFont(font);
        lbl2.setForeground(Color.WHITE);
        lbl3.setFont(font);
        lbl3.setForeground(Color.WHITE);
        lbl4.setFont(font);
        lbl4.setForeground(Color.WHITE);
        lbl5.setFont(font);
        lbl5.setForeground(Color.WHITE);
        lbl6.setFont(font);
        lbl6.setForeground(Color.WHITE);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headPanel, BorderLayout.NORTH);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));  //create margin around the panel
        headPanel.setBackground(Color.DARK_GRAY);
        bodyPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBackground(Color.DARK_GRAY);
        
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnReset);
        buttonPanel.add(btnSubmit);
        btnReset.setFont(font);
        btnSubmit.setFont(font);
        buttonPanel.setBackground(Color.DARK_GRAY);

        tf1.setDocument(new JTextFieldLimit(charLimit));
        tf2.setDocument(new JTextFieldLimit(charLimit));
        tf3.setDocument(new JTextFieldLimit(charLimit));
        tf4.setDocument(new JTextFieldLimit(charLimit));
        tf5.setDocument(new JTextFieldLimit(charLimit));
        tf6.setDocument(new JTextFieldLimit(charLimit));

        setLayout(new BorderLayout());

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSubmit.addActionListener(new sumbit());
        btnReset.addActionListener(new reset());
    }

    public static void main(String[] args) {

        frame = new CrosswordPuzzle();
        frame.setVisible(true);
        frame.setTitle("Crossword Puzzle");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    class JTextFieldLimit extends PlainDocument {

        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    class sumbit implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSubmit) {
                if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()
                        || tf4.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty()) {  //make sure every blank field is filled
                    JOptionPane.showMessageDialog(null, "Please fill in all the blank field");
                } else if (tf1.getText().length() > charLimit || tf2.getText().length() > charLimit || tf3.getText().length() > charLimit
                        || tf4.getText().length() > charLimit || tf5.getText().length() > charLimit || tf6.getText().length() > charLimit) { //make sure every words entered by user is not more than 7 characters
                    JOptionPane.showMessageDialog(null, "Character Length is More Than 7");
                } else if (tf1.getText().length() < charLimit || tf2.getText().length() < charLimit || tf3.getText().length() < charLimit
                        || tf4.getText().length() < charLimit || tf5.getText().length() < charLimit || tf6.getText().length() < charLimit) { //make sure every words entered by user is not less than 7 characters
                    JOptionPane.showMessageDialog(null, "Character Length is Less Than 7");
                } else {

                    /*StringTokenizer token1 = new StringTokenizer(tf1.getText(),"");
                     String [] strPrint = new String [token1.countTokens()];
                     StringTokenizer token2 = new StringTokenizer(tf2.getText());
                     StringTokenizer token3 = new StringTokenizer(tf3.getText());
                     StringTokenizer token4 = new StringTokenizer(tf4.getText());
                     StringTokenizer token5 = new StringTokenizer(tf5.getText());
                     StringTokenizer token6 = new StringTokenizer(tf6.getText());
               
                
                     int numOfTokens1 = token1.countTokens();
                     int numOfTokens2 = token2.countTokens();
                     int numOfTokens3 = token3.countTokens();
                     int numOfTokens4 = token4.countTokens();
                     int numOfTokens5 = token5.countTokens();
                     int numOfTokens6 = token6.countTokens();
                
                     for(int i=0; i<numOfTokens1; i++){
                     String element = token1.nextToken();
                     System.out.print(element);
                     }*/
                     /*String word1[] = new String[7];
                     String word2[] = new String[7];
                     String word3[] = new String[7];
                     String word4[] = new String[7];
                     String word5[] = new String[7];
                     String word6[] = new String[7];
                
                     for (int i = 0;i < 6; i++){
                     word1[i]=tf1.getText().charAt(i)+",";
                     word2[i]=tf2.getText().charAt(i)+",";
                     word3[i]=tf3.getText().charAt(i)+",";
                     word4[i]=tf4.getText().charAt(i)+",";
                     word5[i]=tf5.getText().charAt(i)+",";
                     word6[i]=tf6.getText().charAt(i)+",";
                     }
                
               
                     word1[6]=tf1.getText().charAt(6)+"";
                     word2[6]=tf2.getText().charAt(6)+"";
                     word3[6]=tf3.getText().charAt(6)+"";
                     word4[6]=tf4.getText().charAt(6)+"";
                     word5[6]=tf5.getText().charAt(6)+"";
                     word6[6]=tf6.getText().charAt(6)+"";*/
                    String[] words = {tf1.getText().toLowerCase(), tf2.getText().toLowerCase(), tf3.getText().toLowerCase(), tf4.getText().toLowerCase(), tf5.getText().toLowerCase(), tf6.getText().toLowerCase()}; //save inputs from user in an array

                    String[] facts = new String[6];

                    boolean englishWord = true;

                    String wrongWord = null;

                    try {
                        for (String word : words) {
                            Scanner scanner = new Scanner(new File("wordlist.txt"));

                            while (scanner.hasNextLine()) { //scan word list of english word
                                String dictionaryWord = scanner.nextLine();
                                if (dictionaryWord.equals(word)) {
                                    englishWord = true;
                                    break;
                                } else {
                                    englishWord = false;
                                }
                            }
                            if (!englishWord) {
                                wrongWord = word;
                                break;
                            }
                        };

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (englishWord) {
                        File file = new File("word.pl");

                        for (int i = 0; i < facts.length; i++) {
                            String fact = "word(" + words[i];
                            char[] wordChar = words[i].toCharArray();
                            for (int j = 0; j < wordChar.length; j++) {
                                fact = fact + ", " + wordChar[j];
                            }
                            fact = fact + ").";
                            facts[i] = fact;
                        };

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                            for (int i = 0; i < facts.length; i++) {
                                bw.write(facts[i] + "\n");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        String connect = "consult('crossword.pl')";
                        Query q1 = new Query(connect);

                        System.out.println("File Connected " + (q1.hasSolution() ? "succeeded" : "failed"));

                        String t1 = "crossword(V1,V2,V3,H1,H2,H3).";
                        Query q2 = new Query(t1);
                        if (q2.hasSolution()) {

                            String v1 = q2.oneSolution().get("V1").toString();
                            String v2 = q2.oneSolution().get("V2").toString();
                            String v3 = q2.oneSolution().get("V3").toString();
                            String h1 = q2.oneSolution().get("H1").toString();
                            String h2 = q2.oneSolution().get("H2").toString();
                            String h3 = q2.oneSolution().get("H3").toString();

                            crosswordpuzzle.DisplayResult rects = new crosswordpuzzle.DisplayResult();
                            rects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            rects.setSize(380, 360);
                            rects.setLocationRelativeTo(null);
                            rects.setVisible(true);
                            rects.setTitle("Result");
                            rects.setResizable(false);
                            frame.setVisible(false);

                            rects.v1 = v1;
                            rects.v2 = v2;
                            rects.v3 = v3;
                            rects.h1 = h1;
                            rects.h2 = h2;
                            rects.h3 = h3;

                        } else {
                            JOptionPane.showMessageDialog(null, "Words do not satisfy the puzzle!", "Error", JOptionPane.WARNING_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "\"" + wrongWord + "\" is not an English word.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    class reset implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnReset) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");

            }
        }
    }
}
