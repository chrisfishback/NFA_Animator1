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

    

    /**

     * Constructor for objects of class Main

     */

    public static void Main()

    {

        NFA nfa = new NFA();

        

        //input for filename 

        Scanner scan = new Scanner( System.in );

        System.out.print("Enter the filename w/ the extension (ie. input.txt): "); //testCase1.txt

        String testCase = scan.nextLine();

        

        //create the NFA

        nfa.createNFA(testCase);

        

        //input for string to run through nfa

        System.out.print("Enter a string of 1's and 0's: "); //testCase1.txt

        String inputString = scan.nextLine();
        
        int animateString = Integer.parseInt(inputString);
        
        Animator animate = new Animator(animateString);

        String[] sString = inputString.split("(?!^)");
        
        Node startNode = new Node(0, null);
        
        shortestPath(sString, 0, startNode);
        
        //if there is no accepting string
        if(shortPath.isEmpty())
        {
            System.out.println("String is not accepted");
            System.exit(0);
        }
        
        //make the list of accepting nodes
        Node currNode = shortPath.get(0);
        
        while(currNode.parent!=null)
        {
            shortPath.add(0, currNode.parent);
            currNode = currNode.parent;
        }
        
        //create an arraylist of integers that is the short path
        int i = 0;
        List<Integer> shortestIntegerPath = new ArrayList<Integer>();
        for (Node node : shortPath) { 
            shortestIntegerPath.add(node.state);
            System.out.println(node.state);
            if(i >= sString.length)
                break;
            i++;
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
                    shortPath.add(node);
            }
                
            return;
        }
            
        // 1. Save the current string character to a variable
        int currInputChar = Integer.parseInt(input[currInputIndex]);
        
        // 2. Put all of the states that can be reached from the current state using the current character in a list.
        State state = nfa.listOfStates.get(node.state);
        List<Integer> stateList;
        if(currInputChar == 0)
            stateList = state.zeroList;
        else   
            stateList = state.oneList;
        
        List<Node> nodeList = new ArrayList<Node>();
        // 3. Convert the state list into a list of nodes.
        for(int i = 0; i < stateList.size(); i++)
        {
            nodeList.add(new Node(stateList.get(i), node));
        }
        
        // 4. Set each node in the list to have a parent of the current node.
        // already done above ^^^
        
        // 5. For each node in the list, recursively call this function on that node with the updated string.
        for(int i = 0; i < nodeList.size(); i++)
        {
            shortestPath(input, currInputIndex+1, nodeList.get(i));
        }
    }
}


//used to keep track of path through the NFA
class Node
{
    public int state;
    public Node parent;
    
    public Node(int s, Node par)
    {
        state = s;
        parent = par;
    }
}
