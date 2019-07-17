
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class WordlistConvert {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("wordlist.txt"));
        
        File file = new File("wordList.pl");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (word.contains("\'")){
                    continue;
                }
                else{
                    bw.write("word(\'" + word.toLowerCase() + "\')." +  "\n");
                }    
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        System.out.println("DONE");
    }
}
