/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc_141.mp_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author paks
 */
public class URM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        URM urm = new URM();
        String filename = "/home/paks/NetBeansProjects/CMSC_141-MP_1/src/cmsc_141/mp_1/mp_1.in";
        ArrayList urmLines = new ArrayList();
        urmLines = urm.readFile(filename);
        urm.urmSolver(urmLines);
    }
    
    ArrayList<String> readFile(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename)); 
        ArrayList<String> inputList = new ArrayList();
        while(input.hasNext()){
            inputList.add(input.nextLine());
        }
        return inputList;
    }
    
    void urmSolver(ArrayList<String> urmCommands){
        String inputString = urmCommands.get(0).replaceAll("\\s","");
        char[] urmArrayChar = inputString.toCharArray();
        int[] urmArrayInt = convertToInt(urmArrayChar);
        for(int i = 1; i<urmCommands.size(); ){
           // System.out.println(i + " im i");
            int[] command = cleanCommand(urmCommands.get(i));
            if(urmCommands.get(i).startsWith("S")){
                urmArrayInt = S(urmArrayInt, command);
                i++;
            }
            else if (urmCommands.get(i).startsWith("Z")) {
                urmArrayInt = Z(urmArrayInt, command);
                i++;
            }
            else if (urmCommands.get(i).startsWith("J")) { 
                if (J(urmArrayInt, command))
                    i = command[2];
                else
                    i++;
            }
            else if (urmCommands.get(i).startsWith("C")) {
                urmArrayInt = C(urmArrayInt, command);
                i++;
            }
            for(int j = 0 ; j<urmArrayInt.length;j++){
                System.out.print(urmArrayInt[j]+ " ");
            }
            System.out.println("");
        }
    }
    
    int[] cleanCommand(String cleanThis){
        String[] string = cleanThis.split(" ");
        int [] newCommand = new int[string.length-1];
        for(int i = 0; i<newCommand.length;i++){
            newCommand[i] = Integer.parseInt(string[i+1]);
        }
        return newCommand;
    }
    
    int[] convertToInt(char[] convertThis){
        int[] convertedArray = new int[convertThis.length];
        for(int i =0 ; i<convertThis.length; i++){
            convertedArray[i] = Character.getNumericValue(convertThis[i]);
        }
        return convertedArray;
    }
    
    int[] S(int[] urmArray, int[] command){
        urmArray[command[0]]++;
        return urmArray;
    }
    int[] Z(int[] urmArray, int[] command){
        urmArray[command[0]]=0;
        return urmArray;
    }
    boolean J(int[] urmArray, int[] command){
        int first = urmArray[command[0]];
        int second = urmArray[command[1]];
//        System.out.println(first);
//        System.out.println(second);
        if(first == second)
            return true;
        else
            return false;
    }
    int[] C(int[] urmArray, int[] command){
        int pos1 = command[0];
        int pos2 = command[1];
        urmArray[pos2] = urmArray[pos1];
        return urmArray;
    }
    
}
