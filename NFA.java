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
    private ArrayList<State> listOfStates;
    private boolean isNFA;
    int startState;
    int numStates;
    
    /**
     * Constructor for objects of class NFA
     */
    public NFA()
    {
        listOfStates = new ArrayList<State>();
        isNFA = false;
    }

    public void createNFA(String fileName)
    {
        //for seeing the file
        //String fileName = "testCase1.txt";
        String line = null;
        
        //big poo
        //info for states
        int lineNum = 1; //for line in text file
        int zeroTrans = 0;
        int oneTrans = 0;
        int accState = -1;
        
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                
                if(lineNum == 1)
                    startState = Integer.parseInt(line);
                
                if(lineNum > 1)
                {
                    String[] tmp = line.split(" ");    //Split at the spaces
                    
                    zeroTrans = Integer.parseInt(tmp[1]);
                    oneTrans = Integer.parseInt(tmp[2]);
                    accState = Integer.parseInt(tmp[3]);
                    
                    listOfStates.add(new State(zeroTrans, oneTrans, accState));
                
                }
                
                lineNum++;
            }

            
            numStates = (lineNum-2);
            //close file
            bufferedReader.close();
        } 
        catch(IOException ex) {
            //output error message
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

}
