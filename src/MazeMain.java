
public class MazeMain 
{
	public static void main (String args[])
	{
		Maze m = new Maze(4,4);
		String s = "111111111100010001111010101100010101101110101100000101111011101100000101111111111";
		m.load(s);
		m.display();
		
	  System.out.println();
	  System.out.println(m.solve(1, 1, 7, 7));
	  m.trace(1, 1, 2, 7);
	  
	  System.out.println();
	  m.redesign();
	  m.display();	
	  m.trace(1, 1, 7, 7);
	}
}
