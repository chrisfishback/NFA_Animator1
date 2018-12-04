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
    protected String e = "e";
    protected String zero = "0";
    protected String one = "1";

    public Animator(int string)
    {
        nfa= new NFA();
        currentState = -1;
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);
    }

    public void paint (Graphics canvas, int numStates)
    {
        this.startNode(canvas, x, y);
        this.acceptNode(canvas,2*x,y);
        this.node(canvas,3*x,y);
        this.acceptNode(canvas,x,2*y);
        this.node(canvas,2*x,2*y);
        this.node(canvas,x,3*y);
        this.lineE(canvas,x,y);
        this.lineZero(canvas,x,y);
        this.lineOne(canvas,x,y);
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
        canvas.setColor(Color.BLACK);
        canvas.drawLine((x/2),y,x-75,y-20);//arrowhead
        canvas.drawLine((x/2),y,x-75,y+20);
        canvas.drawLine((x/2),y,x-75,y);
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

    public void lineE (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawString(e,(x+50),(y-10));
        canvas.drawLine(x+25,y,(2*x)-25,y);
        canvas.drawLine((2*x)-25,y,(2*x)-30,y-10);
        canvas.drawLine((2*x)-25,y,(2*x)-30,y+10);
        canvas.drawString(e,(x+10),(y+50));
        canvas.drawLine(x,y+25,x,(2*y)-25);
        canvas.drawLine(x,(2*y)-25,x-10,(2*y)-30);
        canvas.drawLine(x,(2*y)-25,x+10,(2*y)-30);
    }

    public void lineZero (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawString(zero,(2*x)+50,y-10);
        canvas.drawLine((2*x)+25,y,(3*x)-25,y);
        canvas.drawLine((2*x)+25,y,(2*x)+30,y-10);
        canvas.drawLine((2*x)+25,y,(2*x)+30,y+10);
        canvas.drawLine((3*x)-25,y,(3*x)-25,y);
        canvas.drawLine((3*x)-25,y,(3*x)-25,y);
    }

    public void lineOne (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawLine(x,(2*y)+25,x,(3*y)-25);
        canvas.drawLine(x,(2*y)+25,x+10,(3*y)-30);
        canvas.drawLine(x,(2*y)+25,x+10,(3*y)+30);
        canvas.drawString(one, x+10,(2*y)+50);
        canvas.drawLine(x+25,(2*y),(2*x)-25,(2*y));
        canvas.drawLine((2*x)-25,(2*y),(2*x)-30,(2*y)-10);
        canvas.drawLine((2*x)-25,(2*y),(2*x)-30,(2*y)+10);
        canvas.drawString(one,(2*x)-50,(2*y)-10);
        canvas.drawLine((2*x),(2*y)+25,x+25,(y*2));
        canvas.drawLine(x+25,(y*2),x+30,(y*2)-10);
        canvas.drawLine(x+25,(y*2),x+30,(y*2)+10);
        canvas.drawString(one,x+25,(2*y)+50);
    }

    public void animateNFA(){}

}
