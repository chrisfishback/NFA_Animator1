import java.util.Scanner;
import java.util.List;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/**
 * Main class
 *
 * @author Chris Fishback
 * @author Alli Jacobs
 * 
 * @version 1.0
 */

public class Main extends JPanel

{
    public static NFA nfa;
    public static ArrayList<Node> shortPath;
    public static boolean breakRec;

    /////////////////////////////////////////////////

    protected int x = 100; // my x coordinate
    protected int y = 100; // my y coordinate
    protected int diameter = 50; //my diameter
    protected String e = "e";
    protected String zero = "0";
    protected String one = "1";
    protected String qZero = "q0";
    protected String qOne = "q1";
    protected String qTwo = "q2";
    protected String qThree = "q3";
    protected String qFour = "q4";
    protected String qFive = "q5";  

    /**
     * Constructor for objects of class Main
     */

    public static void Main()

    {
        breakRec = false;
        nfa = new NFA();
        shortPath = new ArrayList<Node>();

        //input for filename 
        Scanner scan = new Scanner( System.in );
        System.out.print("Enter the filename w/ the extension (ie. input.txt): "); //testCase1.txt
        String testCase = scan.nextLine();

        //create the NFA
        nfa.createNFA(testCase);

        //input for string to run through nfa
        System.out.print("Enter a string of 1's and 0's: "); //testCase1.txt
        String inputString = scan.nextLine();

        String[] sString = inputString.split("(?!^)");        

        Node startNode = new Node(0, null);      
        shortestPath(sString, 0, startNode);

        //if there is no accepting string
        if(shortPath.isEmpty())
        {
            System.out.println("String is not accepted");
            System.exit(0);
        }

        //create an arraylist of integers that is the short path
        List<Integer> shortestIntegerPath = new ArrayList<Integer>();
        for (Node node : shortPath) { 
            shortestIntegerPath.add(node.state);
        } 

        //reverse the arraylist path of integers so it starts at 0 and output
        Collections.reverse(shortestIntegerPath);
        for (Integer node : shortestIntegerPath) { 
            System.out.println(node);
        } 

        System.out.println("end of program");

        /////////////////////////////////////////////////////////////////////////
        //Animation Code:

        JFrame myFrame = new JFrame();
        Main animate = new Main();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);
        myFrame.add(animate);
        myFrame.setVisible(true);
    }

    public void paint (Graphics canvas){
        canvas.setColor(Color.BLACK);
        this.lineE(canvas,x,y);
        this.lineZero(canvas,x,y);
        this.lineOne(canvas,x,y);
        this.startNode(canvas, x, y);
        this.acceptNode(canvas,2*x,y);
        this.node(canvas,3*x,y);
        this.acceptNode(canvas,x,2*y);
        this.node(canvas,2*x,2*y);
        this.node(canvas,x,3*y);
        this.qNames(canvas,x,y);
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
        canvas.drawLine(x,y+25,x-25,y+25);//arrowhead
        canvas.drawLine(x,y+25,x-25,y);
        canvas.drawLine(x,y+25,x-25,y+50);
    }

    public void acceptNode (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.fillOval(x-6,y-6, diameter+12, diameter+12);
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x-4,y-4, diameter+8, diameter+8);
        canvas.setColor(Color.BLACK);
        canvas.fillOval(x-2,y-2, diameter+4, diameter+4);
        canvas.setColor(Color.WHITE);
        canvas.fillOval(x,y, diameter, diameter);//node
    }

    public void lineE (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawString(e,(x+60),(y+10));
        canvas.drawLine(x+25,y+25,(2*x),y+25);
        canvas.drawLine((2*x),y+25,(2*x)-30,y+35);
        canvas.drawLine((2*x),y+25,(2*x)-30,y+15);
        canvas.drawString(e,(x+30),(y+75));
        canvas.drawLine(x+25,y+50,x+25,(2*y));
        canvas.drawLine(x+25,(2*y),x+35,(2*y)-25);
        canvas.drawLine(x+25,(2*y),x+15,(2*y)-25);
    }

    public void lineZero (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawString(zero,(2*x)+75,y+10);
        canvas.drawLine((2*x)+50,y+25,(3*x),y+25);
        canvas.drawLine((2*x)+56,y+25,(2*x)+70,y+15);
        canvas.drawLine((2*x)+56,y+25,(2*x)+70,y+35);
        canvas.drawLine((3*x),y+25,(3*x)-15,y+15);
        canvas.drawLine((3*x),y+25,(3*x)-15,y+35);
    }

    public void lineOne (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLACK);
        canvas.drawLine(x+25,(2*y)+50,x+25,(3*y));//line pointing down
        canvas.drawLine(x+25,(2*y)+50,x+15,(3*y)-30);
        canvas.drawLine(x+25,(2*y)+50,x+35,(3*y)-30);
        canvas.drawString(one, x+65,(2*y)+10);
        canvas.drawLine(x+25,(2*y)+25,(2*x),(2*y)+25);//line pointing across
        canvas.drawLine((2*x),(2*y)+25,(2*x)-15,(2*y)+15);
        canvas.drawLine((2*x),(2*y)+25,(2*x)-15,(2*y)+35);
        canvas.drawString(one,(2*x)-30,(3*y)-15);
        canvas.drawLine((2*x),(2*y)+25,x+25,(y*3));//line making triangle
        canvas.drawLine((x)+25,(y*3),(x+30),(y*3)-15);
        canvas.drawLine(x+25,(y*3),x+40,(y*3)-5);
        canvas.drawString(one,x,(3*y)-10);
    }

    public void qNames (Graphics canvas, int x, int y)
    {
        canvas.setColor(Color.BLUE);
        canvas.drawString(qZero,x+12,y+25);
        canvas.drawString(qOne,(x*2)+12,y+25);
        canvas.drawString(qTwo,(3*x)+12,y+25);
        canvas.drawString(qThree,x+12,(2*y)+25);
        canvas.drawString(qFour,(2*x)+12,(2*y)+25);
        canvas.drawString(qFive,x+12,(3*y)+25);

    }

    public static void shortestPath(String[] input, int currInputIndex, Node node) {

        // Base case: Check if the string is empty.
        if(currInputIndex == input.length) //base case
        {
            if(nfa.listOfStates.get(node.state).acceptState)
            {
                if(node!=null)
                {
                    while(node.parent!=null)
                    {
                        shortPath.add(node);
                        node = node.parent;
                    }
                    shortPath.add(node);
                    breakRec = true;
                }
            }

            return;
        }

        // 1. Save the current string character to a variable
        String currInputChar = input[currInputIndex];

        // 2. Put all of the states that can be reached from the current state using the current character in a list.
        State state = nfa.listOfStates.get(node.state);
        List<Integer> stateList;
        if(currInputChar.equals("0"))
            stateList = state.zeroList;
        else
            stateList = state.oneList;

        List<Node> nodeList = new ArrayList<Node>();
        // 3. Convert the state list into a list of nodes.
        int l = 0;
        while(l < stateList.size())
        {
            nodeList.add(new Node(stateList.get(l), node));
            l++;
        }

        //add the empty list onto the statelist as well
        if(!state.emptyStringList.isEmpty())
        {
            int j = 0;
            while( j < state.emptyStringList.size())
            {
                nodeList.add(new Node(state.emptyStringList.get(j), node));
                nodeList.get(l).emptyString = true;
                j++;
                l++;
            }
        }

        // 4. Set each node in the list to have a parent of the current node.
        // already done above ^^^

        // 5. For each node in the list, recursively call this function on that node with the updated string.
        for(int i = 0; i < nodeList.size(); i++)
        {
            if(!breakRec)
            {
                if(nodeList.get(i).emptyString)
                    shortestPath(input, currInputIndex, nodeList.get(i));
                else
                    shortestPath(input, currInputIndex+1, nodeList.get(i));
            }
        }
    }
}

//used to keep track of path through the NFA
class Node
{
    public int state;
    public Node parent;
    public boolean emptyString;

    public Node(int s, Node par)
    {
        emptyString = false;
        state = s;
        parent = par;
    }
}
