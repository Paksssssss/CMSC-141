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
    
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> ansList = new ArrayList();
        DeterministicFiniteAutomata dfa = new DeterministicFiniteAutomata();
        ansList = dfa.readFile("/home/paks/NetBeansProjects/CMSC_141/src/mp2/mp2.in");
        dfa.solveDFA(ansList);
    }
    
    ArrayList<String> readFile(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename)); 
        ArrayList<String> inputList = new ArrayList();
        while(input.hasNext()){
            inputList.add(input.nextLine());
        }
        return inputList;
    }
    
    void solveDFA(ArrayList<String> ansList){
        ArrayList<Character> here = new ArrayList();
        initializeHere(here);
        ArrayList<Character> across = new ArrayList();
        String currentSet; 
        for(int i = 0; i< ansList.size(); i++){
            currentSet = ansList.get(i);
            for(int j = 0; j< currentSet.length(); j++){
                if(currentSet.charAt(j)!='N'){
                    if(j%2 == 0){
                        here.remove('B');
                        across.add('B');
                        here.remove(currentSet.charAt(j));
                        across.add(currentSet.charAt(j));
                    }
                    else{
                        across.remove('B');
                        here.add('B');
                        across.remove(currentSet.charAt(j));
                        here.add(currentSet.charAt(j));
                    }
                }
                if(checkneighbor(here) || checkneighbor(across)){
                    
                }
            }
        }
    }
    
    ArrayList<Character> initializeHere(ArrayList<Character> here){
        here.add('L');
        here.add('R');
        here.add('C');
        here.add('B');
        return here;
    }
    
    boolean checkneighbor(ArrayList<Character> riverbank){
        if(!riverbank.contains('B')){
            if(riverbank.contains('L')&&riverbank.contains('R')){
                return true;
            }
            else if(riverbank.contains('R')&&riverbank.contains('C')){
                return true;
            }
        }
    }
}


