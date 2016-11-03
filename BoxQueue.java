import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class BoxQueue
{
	private final int BASE_WIDTH = 30; 
	private final int BASE_HEIGHT = 10;
	private final int BOX_DIM = 20;
	private int xPos;
	private int yPos;
	private int numOnQueue; //number of boxes on the queue
	private ArrayList<Box> boxes = new ArrayList<Box>();
	private char letter;
	private Rectangle rect; //rectangle that is represents the base of the queue
	private int next; 
/**
Constructor:

@param x the x coordinate of the BoxQueue
@param y the y coordinate of the BoxQueue
**/
	public BoxQueue(int x, int y)
	{
		letter = 'A';
		xPos = x;
		yPos = y;
		rect = new Rectangle(x, y, 40, 20);
		//Creates 5 boxes at consecutive places in the queue with appropriate offsets. Letter and numOnQueue are incremented each time
		for(int i = 0; i < 5; i++)
		{
			boxes.add(i, new Box(getX() + 5, getY() - 20 - (i * 20), letter));
			letter++;
			numOnQueue++;
			next++;
		}
	}
	/**
	Gets x position of the queue
	@return returns x position of queue
	**/
	public int getX()
	{
		return xPos;
	}
	/**
	Gets y position of the queue
	@return returns y position of queue
	**/
	public int getY()
	{
		return yPos;
	}
	/**
	Removes and returns the first box from the queue
	@return the first box in the queue is removed from the queue and returned
	**/
	public Box removeBox()
	{
		Box temp = boxes.get(0);
		boxes.remove(boxes.get(0));
		temp.offQueue = true;
		numOnQueue--;
		for(int i = 0; i < boxes.size(); i++)
		{
			if(boxes.get(i).offQueue == false)
			{
				boxes.get(i).moveTo(boxes.get(i).getX(), boxes.get(i).getY() + 20);
			}
		}
		return temp;
	}
	/**
	Adds a box to the queue
	@param aBox adds the parameter box to the queue.
	**/
	public void addBox(Box aBox)
	{
		aBox.offQueue = false;
		boxes.add(aBox);
		aBox.moveTo(getX() + 5, getY() - 20 - ( 20 * numOnQueue++));
	}
	/**
	Draws a base rectangle, as well as the box objects that are still currently on the queue
	**/
	public void draw(Graphics2D g2)
	{
		for(int i = 0; i < boxes.size(); i++)
		{
			if(boxes.get(i) != null)
			boxes.get(i).draw(g2);
		}
		g2.setColor(Color.BLACK);
		g2.fill(rect);
	}
}
