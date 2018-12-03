import java.util.*;
import java.io.*;

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
    public boolean acceptState;
    public List<Integer> zeroList;
    public List<Integer> oneList;
    
    /**
     * Constructor for objects of class state
     */
    public State(List<Integer> z, List<Integer> o, int accept)
    {
        if(accept == 1)
            acceptState = true;
        else if(accept == 0)
            acceptState = false;
        
        zeroList = new ArrayList(z);   
        oneList = new ArrayList(o);
    }

    public boolean checkAccept()
    {
        return acceptState;
    }
    
    
}
