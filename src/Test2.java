
import java.io.File;
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
public class Test2 {
    public static void main(String[] args){
        boolean englishWord = true;

        try {
            Scanner scanner = new Scanner(new File("wordlist.txt"));

            while (scanner.hasNextLine()) { //scan word list of english word
                String dictionaryWord = scanner.nextLine();
                if (dictionaryWord.equals("sequence")) {
                    englishWord = true;
                    break;
                } else {
                    englishWord = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (englishWord) {
            System.out.println("DONE");
        }
    }
}
