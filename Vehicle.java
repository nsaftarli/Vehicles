//Superclass of TrainEngine and Railcar. 

import java.awt.Color;
import java.awt.Graphics2D;



abstract public class Vehicle
{
	private boolean selected; //checks to see if it is selected
	private int xPos; //holds x coordinate
	private int yPos; //holds y coordinate
	protected Color color; //holds current color
	public int number; // holds the number assigned to the vehicle[ only for railcars]  
	public Vehicle trailer; //holds the trailer reference
	public boolean isTrailer; //checks to see if this vehicle is a trailer
	protected boolean loaded; //checks to see if there is a Box object associated with this vehicle
	protected Box box; //holds teh reference to the Box object

	/**
	Constructor: Creates a vehicle with an x and y position, and a number assigned to it. 
	For railcars.
	@param x X position of the railcar
	@param y Y position of the railcar
	@param n Number drawn on the rialcar
	**/
	public Vehicle(int x, int y, int n)
	{
		xPos = x;
		yPos = y; 
		number = n;
		isTrailer = false;
		loaded = false;
	}
	/**
	Constructor: Creates a vehicle with an x and y position.
	For the train engine.
	@param x X position of the engine
	@param y Y position of the engine
	**/
	public Vehicle(int x, int y)
	{
		xPos = x;
		yPos = y;
		isTrailer = false;
		loaded = false;
	}
	/**
	Returns X position of the vehicle
	@return x position of the vehicle
	**/
	public int getX()
	{
		return xPos;
	}
	/**
	Returns Y position of the vehicle
	@return Y position of the vehicle
	**/
	public int getY()
	{
		return yPos;
	}
	/**
	Sets the location of the vehicle
	@param x sets the x coordinate of the vehicle to parameter
	@param y sets the y coordinate of the vehicle to parameter
	**/
	public void setLoc(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	/**
	Checks to see if this vehicle has a trailer
	@return true if a trailer exists, false otherwise
	**/
	public boolean hasTrailer()
	{
		if(trailer != null)
		{
			return true;
		}
		return false;
	}
	/**
	Returns a reference to the vehicle that is the trailer of this one
	@return the trailer of this vehicle
	**/
	public Vehicle getTrailer()
	{
		return trailer;
	}
	/**
	Sets the trailer of this vehicle 
	@param vehicle sets the trailer of this vehicle to the parameter
	**/
	public void setTrailer(Vehicle vehicle)
	{
		trailer = vehicle;
		trailer.isTrailer = true;
	}
	/**
	Sets the color of this vehicle
	@param color the color to draw this vehicle with.
	**/
	public void setColor(Color color)
	{
		this.color = color;
	}

	abstract void moveTo(int x, int y);
	abstract void draw(Graphics2D g2);
	abstract boolean isClicked(int x, int y);
	abstract int getNumber();
	abstract void setNumber(int num);
	abstract void setLoad(Box box);
	abstract void offLoad(int x, int y);
	abstract boolean hasLoad();
	abstract Box getLoad();



	
}
