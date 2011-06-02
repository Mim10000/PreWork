import java.util.LinkedList;
import java.util.Random;


public class Maze 
{
	private int n;
	private int m;
	private int dx;
	private int dy;
	private String s;
	private char mazeR[][];
	private LinkedList <MPoint>tracer;
	//Creates a maze with dimensions n & m
	public Maze(int n, int m)
	{
		dx = n*2+1;
		dy = m*2+1;
		this.n = n;
		this.m = m;
	}
	//method that initializes the maze using a string of ones and zeros
	public void load(String s)
	{
		 this.s = s;
	}
	public void display()
	{
		create();
		//represents a matrix and hence the maze
		for(int i=0; i< dy; i++)
		{	
			for(int j=0; j< dx;j++)
			{
				if (mazeR[i][j] == '1')
				{
					if((j%2==0 || j==0) && i%2==0)
					{
					System.out.print("+");
					}
					else if(i%2==0)
					{
						System.out.print("-");
					}
					else
					{
						System.out.print("|");
					}
				}
				//if its 0 its represented by a black space
				else 
				{
					System.out.print(" ");
				}
			}
		System.out.println();
		}
	}
	public boolean solve(int begX, int begY,int endX,int endY)
	{	
		//Creates MPoints for the beginning and ending points
		 MPoint beg = new MPoint (begY, begX, new Maze(n,m),s);	 
		 MPoint end = new MPoint (endY, endX, new Maze(n,m),s);
		
		tracer = new LinkedList <MPoint>();

		 tracer.add(beg);
		 tracer.addAll(beg.getNeighbors());
		 int index = 1;
		 
		 LinkedList <MPoint> proxy; 

		 //Deals with exception if the starting or ending point is a wall
		if(beg.getC()=='1' || end.getC()=='1')
		{
			System.out.println("One of these points was a wall");
			return false;
		}
		
		else
		{
			//while the tracer list does not contain the end point continue looking or it has gone through the entire list
		    while(!tracer.contains(end) && index < tracer.size())
			{
				//represents all of the neighbors the tracer has at index
				proxy = new LinkedList<MPoint>(); 
				proxy.addAll(tracer.get(index).getNeighbors()); 
				
				for(int i=0; i < proxy.size(); i++)
				{
					//sees if the tracer has the new point already
					if(!tracer.contains(proxy.get(i)))
					{
						tracer.add(proxy.get(i));
						
					}
					//Checks if the list has the point 
					if (tracer.contains(end))
					{
						return true;
					}
				}
				index++;
			}
			 return false;
		}
	}
	public void trace(int begX, int begY,int endX,int endY)
	{	
		 MPoint end = new MPoint (endY, endX, new Maze(n,m),s);
		
		if(solve( begX,begY,endX,endY)==true)
		{
			LinkedList <MPoint> neighbors = new LinkedList <MPoint>(); 
			neighbors.addAll(end.getNeighbors());
			
			//Initial size of the tracer list
			int size =tracer.size();
			for(int i = size-2; i > 0; i--)
			{
				if(neighbors.contains(tracer.get(i)))
				{
					neighbors.clear();
					neighbors.addAll(tracer.get(i).getNeighbors());
				}
				else
				{
					tracer.remove(i);
				}
			}
			//Prints out the new list
			for(int j=0; j<tracer.size(); j++)
			{
				System.out.print(tracer.get(j).getX()+ "," + tracer.get(j).getY() + " ");
			}
		}
		else
		{
			System.out.println("Unfortunately from this point in the maze you cannot reach the end point");
		}
	}
	public void redesign() 
	{	
		String s = "";
		
		for(int i=0; i< dy; i++)
		{	
			for(int j=0; j< dx;j++)
			{
				//all the places that are the basic maze
				if(i == 0 || i == dy-1 || j==0 || j==dx-1 || (j%2==0 && i%2==0))
				{
					s = concat(s, "1");
				}
				// randomly designed walls
				else if((i%2==1 && j%2==0) || (i%2==0 && j%2==1))
				{
					Random r = new Random();
					int q = r.nextInt(2);
					if (q==0)
					{
						s = concat(s, "0");
					}
					else if (q==1)
					{
						s = concat(s, "1");
					}
				}
				//empty spaces
				else
				{
					s = concat(s, "0");
				}
			}
		}
		this.s = s;
		//if the randomly designed maze is not solvable try again
		if(solve(1, 1, (dx-2), (dy-2)) == false)
		{
			redesign();
		}
	}
	public void create()
	{
		mazeR = new char[dy][dx]; 
		int index = 0;
		
		for(int i=0; i< dy; i++)
		{
			for(int j=0; j< dx; j++)
			{
				mazeR[i][j] = s.charAt(index);
				index++;
			}
		}		
	}
	public char[][] getMaze()
	{
		create();
		return mazeR;
	}
	public int getdx()
	{
		return dx;
	}
	public int getdy()
	{
		return dy;
	}
	//helper method to concatinate strings
	public String concat(String a, String b)
	{
		String s = a+b;
		return s;
	}
}
