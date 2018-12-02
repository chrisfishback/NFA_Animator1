
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
        nfa.createNFA();
        
        System.out.println("end of program");
    }
}
