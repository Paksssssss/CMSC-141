/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author paks
 */
public class DeterministicFiniteAutomata {
    
    public static void main(String[] args) {
        DeterministicFiniteAutomata dfa = new DeterministicFiniteAutomata();
    
    }
    
    ArrayList<String> readFile(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename)); 
        ArrayList<String> inputList = new ArrayList();
        while(input.hasNext()){
            inputList.add(input.nextLine());
        }
        return inputList;
    }
}

public class thing{
    private static char whatiam;
    private static char[] whoimwith;
    public thing(char what){
        whatiam = what;
    }
    
    boolean checkneighbor(){
        
    }
}