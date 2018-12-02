import java.util.Scanner;

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
        
        
        System.out.println("end of program");
    }
}
