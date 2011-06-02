import java.util.LinkedList;

public class MPoint 
{
	private int x;
	private int y;
	private LinkedList <MPoint> neighbors;
	private Maze m; 
	private String s;
	private char c;
	
	public MPoint(int y, int x, Maze m, String s)
	{
		this.y = y;
		this.x = x;
		this.s = s;
		this.m = m;
		m.load(s);
		c = this.m.getMaze()[y][x];
			
	}
	public LinkedList <MPoint> getNeighbors()
	{
		neighbors = new LinkedList <MPoint>();
		//all the possibilities for neighbors
		if (x != m.getdx())
		{
			if (this.m.getMaze()[y][x+1]== '0')
			{
				neighbors.add(new MPoint(y,x+1,m,s));
			}
		}
		if(x!=0)
		{
			if (this.m.getMaze()[y][x-1]== '0')
			{
				neighbors.add(new MPoint(y,x-1,m,s));
			}
		}
		if (y!= m.getdy())
		{
			if (this.m.getMaze()[y+1][x]== '0')
			{
				neighbors.add(new MPoint(y+1,x,m,s));
			}
		}
		if(y!=0)
		{
			if (this.m.getMaze()[y-1][x]== '0')
			{
				neighbors.add(new MPoint(y-1,x,m,s));
			}
		}
		return neighbors;
	}
	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	public char getC()
	{
		return c;
	}
	public boolean equals(Object o) {
        if (o instanceof MPoint) {
            MPoint other = (MPoint) o;
            return x == other.x && y == other.y;
        } else {  // not the MPoint object
            return false;
        }
    }
}
