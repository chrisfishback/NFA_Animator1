import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * Main class
 *
 * @author Chris Fishback
 * @author Alli Jacobs
 * 
 * @version 1.0
 */

public class Main

{
    public static NFA nfa;
    public static ArrayList<Node> shortPath;
    public static boolean breakRec;

    /**
     * Constructor for objects of class Main
     */

    public static void Main()

    {
        breakRec = false;
        nfa = new NFA();
        shortPath = new ArrayList<Node>();

        //input for filename 
        Scanner scan = new Scanner( System.in );
        System.out.print("Enter the filename w/ the extension (ie. input.txt): "); //testCase1.txt
        String testCase = scan.nextLine();

        //create the NFA
        nfa.createNFA(testCase);

        //input for string to run through nfa
        System.out.print("Enter a string of 1's and 0's: "); //testCase1.txt
        String inputString = scan.nextLine();

        String[] sString = inputString.split("(?!^)");        
       
        Node startNode = new Node(0, null);      
        shortestPath(sString, 0, startNode);
        
        //if there is no accepting string
        if(shortPath.isEmpty())
        {
            System.out.println("String is not accepted");
            System.exit(0);
        }
        
        //create an arraylist of integers that is the short path
        List<Integer> shortestIntegerPath = new ArrayList<Integer>();
        for (Node node : shortPath) { 
            shortestIntegerPath.add(node.state);
        } 
        
        //reverse the arraylist path of integers so it starts at 0 and output
        Collections.reverse(shortestIntegerPath);
        for (Integer node : shortestIntegerPath) { 
            System.out.println(node);
        } 
        
        System.out.println("end of program");
    }
    
    public static void shortestPath(String[] input, int currInputIndex, Node node) {
        
        // Base case: Check if the string is empty.
        if(currInputIndex == input.length) //base case
        {
            if(nfa.listOfStates.get(node.state).acceptState)
            {
                if(node!=null)
                {
                    while(node.parent!=null)
                    {
                        shortPath.add(node);
                        node = node.parent;
                    }
                    shortPath.add(node);
                    breakRec = true;
                }
            }
                
            return;
        }
            
        // 1. Save the current string character to a variable
        String currInputChar = input[currInputIndex];
        
        // 2. Put all of the states that can be reached from the current state using the current character in a list.
        State state = nfa.listOfStates.get(node.state);
        List<Integer> stateList;
        if(currInputChar.equals("0"))
            stateList = state.zeroList;
        else
            stateList = state.oneList;
        
        List<Node> nodeList = new ArrayList<Node>();
        // 3. Convert the state list into a list of nodes.
        int l = 0;
        while(l < stateList.size())
        {
            nodeList.add(new Node(stateList.get(l), node));
            l++;
        }
        
        //add the empty list onto the statelist as well
        if(!state.emptyStringList.isEmpty())
        {
            int j = 0;
            while( j < state.emptyStringList.size())
            {
                nodeList.add(new Node(state.emptyStringList.get(j), node));
                nodeList.get(l).emptyString = true;
                j++;
                l++;
            }
        }
        
        // 4. Set each node in the list to have a parent of the current node.
        // already done above ^^^
        
        // 5. For each node in the list, recursively call this function on that node with the updated string.
        for(int i = 0; i < nodeList.size(); i++)
        {
            if(!breakRec)
            {
                if(nodeList.get(i).emptyString)
                    shortestPath(input, currInputIndex, nodeList.get(i));
                else
                    shortestPath(input, currInputIndex+1, nodeList.get(i));
            }
        }
    }
}


//used to keep track of path through the NFA
class Node
{
    public int state;
    public Node parent;
    public boolean emptyString;
    
    public Node(int s, Node par)
    {
        emptyString = false;
        state = s;
        parent = par;
    }
}
