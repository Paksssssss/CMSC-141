/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author paks
 */
public class DeterministicFiniteAutomata {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
    
    void writeFile(String string) throws IOException{
        Files.write(Paths.get("/home/paks/NetBeansProjects/CMSC_141/src/output/mp2.out"), string.getBytes());
    }
    
    void solveDFA(ArrayList<String> ansList) throws IOException{
        String currentSet; 
        String output = "";
        for(int i = 0; i< ansList.size(); i++){
            ArrayList<String> here = new ArrayList();
            initializeHere(here);
            ArrayList<String> across = new ArrayList();
            boolean isOkay = true;
            currentSet = ansList.get(i);
            for(int j = 0; j< currentSet.length(); j++){
                if(j%2 == 0){
                    if(!here.contains(Character.toString(currentSet.charAt(j))) && 
                            Character.toString(currentSet.charAt(j)).compareTo("N")!=0){
                        isOkay = false;
                        break;
                    }
                    here.remove("B");
                    across.add("B");
                    here.remove(Character.toString(currentSet.charAt(j)));
                    across.add(Character.toString(currentSet.charAt(j)));
                }
                else{
                    if(!across.contains(Character.toString(currentSet.charAt(j))) && 
                            Character.toString(currentSet.charAt(j)).compareTo("N")!=0){
                        isOkay = false;
                        break;
                    }
                    across.remove("B");
                    here.add("B");
                    across.remove(Character.toString(currentSet.charAt(j)));
                    here.add(Character.toString(currentSet.charAt(j)));
                }
                if(checkneighbor(here) || checkneighbor(across)){
                    isOkay = false;
                    break;
                }
                
            }
            here.removeAll(Collections.singleton("N"));
            if(isOkay && here.isEmpty() ){
                System.out.println("OK");
                output += "OK" + '\n';
            }
            else{
                System.out.println("NG");
                output += "NG"+'\n';
            }
        }
        writeFile(output);
    }
    
    ArrayList<String> initializeHere(ArrayList<String> here){
        here.add("L");
        here.add("R");
        here.add("C");
        here.add("B");
        here.add("N");
        return here;
    }
    
    boolean checkneighbor(ArrayList<String> riverbank){
        if(!riverbank.contains("B")){
            if(riverbank.contains("L")&&riverbank.contains("R")){
                return true;
            }
            else if(riverbank.contains("R")&&riverbank.contains("C")){
                return true;
            }
        }
        return false;
    }
}


