package crosswordpuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayResult extends JFrame implements ActionListener{
    
    public String v1;
    public String v2;
    public String v3;
    public String h1;
    public String h2;
    public String h3;
    
    public int boxSize = 30;
    public int startingX = 50;
    public int startingY = 0;
    
    JButton btnBack = new JButton("Back");
    
    DisplayResult(){
        JPanel titlePanel = new JPanel();
        Result result = new Result();
        JPanel buttonPanel = new JPanel();
        
        JLabel titleLabel = new JLabel("Crossword Puzzle");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.DARK_GRAY);
        
        result.setBackground(Color.DARK_GRAY);
        
        buttonPanel.add(btnBack);
        btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.DARK_GRAY);
        
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(result, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            CrosswordPuzzle frame = new CrosswordPuzzle();
            frame.setVisible(true);
            frame.setTitle("Crossword Puzzle");
            frame.setSize(450, 350);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(false);
        }
    }
    
    public class Result extends JPanel{
        
        public void paintComponent(Graphics g) {   
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            Font font = new Font("Century Gothic", Font.PLAIN, 20);

            g.setFont(font); 
            
            FontMetrics metrics = g.getFontMetrics(font);
            
            g.setColor(Color.WHITE);

            g2d.drawString("V1", startingX + 2*boxSize + (boxSize - metrics.stringWidth("V1")) / 2, startingY + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            g2d.drawString("V2", startingX + 4*boxSize + (boxSize - metrics.stringWidth("V2")) / 2, startingY + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            g2d.drawString("V3", startingX + 6*boxSize + (boxSize - metrics.stringWidth("V3")) / 2, startingY + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());

            g2d.drawString("H1", startingX + (boxSize - metrics.stringWidth("H1")) / 2, startingY + 2*boxSize + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            g2d.drawString("H2", startingX + (boxSize - metrics.stringWidth("H2")) / 2, startingY + 4*boxSize + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            g2d.drawString("H3", startingX + (boxSize - metrics.stringWidth("H3")) / 2, startingY + 6*boxSize + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            
            for (int i=0; i<7; i++){
                for (int j=0; j<7; j++){
                    
                    if (i%2 == 0){
                        if (j%2 == 0){
                            continue;
                        }
                        else{
                            g.setColor(Color.YELLOW);
                            g2d.fillRect(startingX + boxSize + i*boxSize, startingY + boxSize + j*boxSize, boxSize, boxSize);
                            g.setColor(Color.black);
                            g2d.drawRect(startingX + boxSize + i*boxSize, startingY + boxSize + j*boxSize, boxSize, boxSize);
                        }
                    }
                    else{
                        g.setColor(Color.YELLOW);
                        g2d.fillRect(startingX + boxSize + i*boxSize, startingY + boxSize + j*boxSize, boxSize, boxSize);
                        g.setColor(Color.BLACK);
                        g2d.drawRect(startingX + boxSize + i*boxSize, startingY + boxSize + j*boxSize, boxSize, boxSize);
                    }
                }
            }
            
            drawHorizontal(g, metrics, h1, 0);
            drawHorizontal(g, metrics, h2, 1);
            drawHorizontal(g, metrics, h3, 2);
            drawVertical(g, metrics, v1, 0);
            drawVertical(g, metrics, v2, 1);
            drawVertical(g, metrics, v3, 2);
        }
        
        public void drawHorizontal(Graphics g, FontMetrics metrics, String word, int row){
            char[] wordChar = word.toCharArray();
            for (int i=0; i<wordChar.length; i++){
                g.drawString(wordChar[i]+"", startingX + boxSize + boxSize*i + (boxSize - metrics.stringWidth(wordChar[i]+"")) / 2, startingY + 2*boxSize + 2*boxSize*row + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
            }
        }
        
        public void drawVertical(Graphics g, FontMetrics metrics, String word, int col){
            char[] wordChar = word.toCharArray();
            for (int i=0; i<wordChar.length; i++){
                if(i%2 == 1){
                    continue;
                }
                else{
                    g.drawString(wordChar[i]+"", startingX + 2*boxSize + 2*boxSize*col + (boxSize - metrics.stringWidth(wordChar[i]+"")) / 2, startingY + boxSize + boxSize*i + ((boxSize - metrics.getHeight()) / 2) + metrics.getAscent());
                }
            }
        }
    }
}