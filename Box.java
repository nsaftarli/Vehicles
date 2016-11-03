import java.awt.*;
import java.awt.geom.*;

public class Box
{
	private final int BOX_DIM = 20;	//Dimensions of the box
	private int xPos;	//X position of individual box
	private int yPos;	//Y position of individual box
	private char letter;	//The letter that goes on an individual box
	public boolean offQueue; //checks if the box is out of the queue
	private Rectangle rect;

	/**
	Constructor:
	@param x the x position of the top left corner
	@param y the y position of the top left corner
	@param letter the letter to be displayed on the box
	**/
	public Box(int x, int y, char letter)
	{
		xPos = x;
		yPos = y;
		rect = new Rectangle(x, y, BOX_DIM, BOX_DIM);
		this.letter = letter;
		offQueue = false;
	}
	/**
	A method that returns the X position of a box
	@return the X position of the box
	**/
	public int getX()
	{
		return xPos;
	}
	/**
	A method that returns the Y positon of a box
	@return the Y position of the box
	**/
	public int getY()
	{
		return yPos;
	}
	/**
	A method that moves the box to the given coordinates.
	@param x the x position to which to move the box
	@param y the y position to which to move the box
	**/
	public void moveTo(int x, int y)
	{
		rect.setLocation(x, y);
		xPos = x;
		yPos = y;
	}
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.GREEN);
		g2.draw(rect);
		g2.setColor(Color.BLACK);
		g2.drawString("" + letter, xPos + 10, yPos + 10);
		
	}
}
