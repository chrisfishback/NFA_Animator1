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

    public Animator(int string)
    {
        nfa= new NFA();
        currentState = -1;
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);
    }

    public void animateNFA()
    {

    }

    public void paint (Graphics canvas, int numStates)
    {
        int node=numStates;
        int i=1;
        String nodeNames="q0 q1 q2 q3 q4 q5 q6 q7 q8 q9";
        int nameEachNode= nodeNames.length();
        char name=nodeNames.charAt(10);
        this.startNode(canvas, x, y);
        while (i < node)
        {
            this.node(canvas,x*i,y);
            i++;
        }
    }

    public void printInputString()
    {

    }

    public void node(Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x,y, diameter, diameter);
    }

    public void onNode(Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.RED);
        canvas.fillOval(x,y, diameter, diameter);
    }

    public void startNode (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x,y, diameter, diameter);//node
        canvas.drawLine(x-50,y,x-75,y-20);//arrowhead
        canvas.drawLine(x-50,y,x-75,y+20);
    }

    public void acceptNode (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.fillOval(x,y, diameter+12, diameter+12);
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x,y, diameter+8, diameter+8);
        canvas.setColor(Color.BLACK);
        canvas.fillOval(x,y, diameter+4, diameter+4);
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x,y, diameter, diameter);//node

    }

    public void lineToSameNode(Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawArc (x, y, 20, 25,0,360);
        canvas.drawLine(x,y-50,x-20,y-75);//arrowhead
        canvas.drawLine(x,y-50,x+20,y-75);
    }
}
