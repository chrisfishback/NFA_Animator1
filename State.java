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
    private boolean acceptState;
    public List<Integer> zeroList;
    public List<Integer> oneList;
    private int zero;
    private int one;
    
    /**
     * Constructor for objects of class state
     */
    public State(List<Integer> z, List<Integer> o, int accept)
    {
        if(accept == 0)
            acceptState = true;
        else
            acceptState = false;
        
        zeroList = z;
        oneList = o;
    }

    public boolean checkAccept()
    {
        return acceptState;
    }
    
    
}
