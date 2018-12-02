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
    private int zero;
    private int one;
    
    /**
     * Constructor for objects of class state
     */
    public State(int z, int o, int accept)
    {
        if(accept == 0)
            acceptState = true;
        else
            acceptState = false;
        
        zero = z;
        one = o;
    }

    public boolean checkAccept()
    {
        return acceptState;
    }
    
}
