/**
 * state class
 *
 * @author Chris Fishback
 * @author Alli Jacobs
 * 
 * @version 1.0
 */
public class State
{
    private boolean acceptState;
    private boolean startState;
    private int zero;
    private int one;
    
    /**
     * Constructor for objects of class state
     */
    public State()
    {
        acceptState = false;
        startState = false;
        zero = -1;
        one = -1;
    }

    public boolean checkAccept()
    {
        return true;
    }
    
}
