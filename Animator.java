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
    protected int x = 100; // my x coordinate
    protected int y = 100; // my y coordinate
    protected int diameter = 50; //my diameter

    public Animator(int initX, int initY)
    {
        nfa= new NFA();
        currentState = -1;
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);
        this.x = initX;
        this.y = initY;
    }

    public void animateNFA()
    {

    }

    public void paint (Graphics canvas, int numStates)
    {
        canvas.setColor(Color.WHITE);
        int node=numStates;
        int i=0;
        String nodeNames="q0 q1 q2 q3 q4 q5 q6 q7 q8 q9";
        int a= nodeNames.length();
        char name=nodeNames.charAt(10);
        while (i < node)
        {
            canvas.fillOval(i*x,y, diameter, diameter);
            //canvas.drawString(i,i*(x/2),y/2);
            i++;
            
        }
        
        /**int node=numStates;
        * int i=0;
        while (i < node)
        {
        canvas.fillOval(i*x,y, diameter, diameter);
        i++;
        }

         */
    }

    public void printInputString()
    {

    }
}
