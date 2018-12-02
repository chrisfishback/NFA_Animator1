/**
 * Animator class
 *
 * @author Chris Fishback
 * @author Alli Jacobs
 * 
 * @version 1.0
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Animator
{
    private NFA nfa;
    private int currentState;
    
    /**
     * Constructor for objects of class Animator
     */
    protected int x; // my x coordinate
    protected int y; // my y coordinate
    protected int diameter = 50; //my diameter
    public Animator(int initX, int initY)
    {
        nfa = new NFA();
        currentState = -1;
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);
        this.x = initX;
        this.y = initY;
        this.diameter = 20;
    }

    public void animateNFA()
    {
        
    }
    
    public void paint (Graphics canvas)
    {
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x, y, diameter, diameter);
    }
    public void printInputString()
    {
        
    }
}
