import java.util.ArrayList;
import java.io.*;

/**
 * NFA class
 *
 * @author Chris Fishback
 * @author Alli Jacobs
 * 
 * @version 1.0
 */
public class NFA
{
    public ArrayList<State> listOfStates;
    int startState;
    int numStates;
    
    /**
     * Constructor for objects of class NFA
     */
    public NFA()
    {
        listOfStates = new ArrayList<State>();
        numStates = 0;
    }
    
    public void createNFA(String fileName)
    {
        //for seeing the file
        //String fileName = "testCase1.txt";
        String line = null;
        
        //big poo
        //info for states
        int lineNum = 1; //for line in text file
        int accState = -1;
        
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            ArrayList<Integer> zeroTrans = new ArrayList<Integer>(); //temp 
            ArrayList<Integer> oneTrans = new ArrayList<Integer>(); //temp
            ArrayList<Integer> emptyTrans = new ArrayList<Integer>(); //temp
           
            // Always wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int index = 0; //index if you couldnt tell by the name, once it is 3 skip the next node name
            
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                
                if(line.equals("n/a"))
                {
                    index++;
                    continue;
                }
                
                //gets the start state
                if(lineNum == 1)
                    startState = Integer.parseInt(line);
                    
                //gets the state acceptance, and transitions for each state
                if(lineNum > 1)
                {
                    //skip name of state
                    if(index == 0)
                    {
                        index++;
                        continue;
                    }
                    
                    //get all zero transitions
                    if(index == 1) 
                    {
                        String[] tmp = line.split(" ");    //Split at the spaces
                        for(String s: tmp)
                            zeroTrans.add(Integer.parseInt(s));
                    }
                        
                    //get all one transitions
                    if(index == 2) 
                    {
                        String[] tmp = line.split(" ");    //Split at the spaces
                        for(String s: tmp)
                            oneTrans.add(Integer.parseInt(s));
                    }
                    
                    //get all empty string transitions
                    if(index == 3) 
                    {
                        String[] tmp = line.split(" ");    //Split at the spaces
                        for(String s: tmp)
                            emptyTrans.add(Integer.parseInt(s));
                    }
                    
                    //get the acceptance
                    if(index == 4) 
                    {
                        accState = Integer.parseInt(line);
                        
                        listOfStates.add(new State(zeroTrans, oneTrans, emptyTrans, accState));
                        index = 0;
                    
                        //reset temp trans array lists
                        zeroTrans.clear();
                        oneTrans.clear();
                        emptyTrans.clear();
                        
                        //increase number of states
                        numStates++;
                        
                        //continue onto the next part of the loop
                        continue;
                    }
                
                    index++;
                }
                
                lineNum++;
            }

            //close file
            bufferedReader.close();
        } 
        catch(IOException ex) {
            //output error message
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

}
