
import org.jpl7.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Test {
    public static void main(String[] args){
        String connect = "consult('wordList.pl')";
        Query q1 = new Query(connect);
        System.out.println("File Connected " + (q1.hasSolution() ? "succeeded" : "failed"));
        
        String word = "word(sequence).";
        Query q2 = new Query(word);
        
        if (q2.hasSolution()){
            System.out.println("DONE");
        }
    }
}
