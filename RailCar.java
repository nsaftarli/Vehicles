//This class was given to us

import java.awt.* ;
import java.awt.geom.* ;

/**
   This class describes a vehicle that looks like a flatbed 
   railcar.  The railcar should be assigned a unique number 
   displayed on its body. The railcar should have variable and 
   methods to allow it to be linked to another vehicle (consider
   whether this variable and associated methods should be
   inherited). This railcar should also have variables and
   methods so that a storage container can be loaded and unloaded
   Add other variables and methods you think are necessary.
 */
public class RailCar extends Vehicle
{
    public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;
    
    public int offset = BODY_WIDTH + 5;
    /**
    Constructor: 
    @param x X position of railcar
    @param y Y position of railcar
    @param n number of railcar
    **/
    public RailCar(int x, int y, int n)
    {
    	super(x, y, n);
    	number = n;
    }
    /**
    @return the number of this railcar
    **/
    public int getNumber()
    {
    	return number;
    }
    /**
    Sets the number for this railcar
    @param num the number to set the number of this railcar to
    **/
    public void setNumber(int num)
    {
    	number = num;
    }
    /**
    Checks to see if this railcar is clicked, by checking to see if the values of an event
    are contained within the dimensions of this railcar.
    @param x X value of event
    @param y Y value of event
    **/
    public boolean isClicked(int x, int y)
    {
    	if((getX() <= x) && (getY() <= y))
    	{
    		if((getX() + (2 * BODY_WIDTH) >= x) && (getY() + BODY_HEIGHT + UNIT >= y))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    /**
    Moves this railcar to the given destination
    @param x X coordinate to move the railcar and its trailers to
    @param y Y coordinate to move the railcar and its trailers to
    **/
    public void moveTo(int x, int y)
    {
    	setLoc(x, y);
        if(hasTrailer())
        {
            trailer.moveTo(getX() + 65, getY());
        }
        if(hasLoad())
        {
            box.moveTo(getX() + 20, getY() - 10);
        }
    }
    /**
    Sets the trailer to a given variable, attaching it at the end of a list of railcars
    @param vehicle the Vehicle object to be attached to this one

    **/
    public void setTrailer(Vehicle vehicle)
    {
        trailer = vehicle;
        if(vehicle.hasTrailer())
        {
            vehicle.setTrailer(vehicle.trailer);
        }
        vehicle = null;
        trailer.isTrailer = true;
        trailer.moveTo(getX() + 65, getY());
    }
    /**
    Sets the box variable fo this railcar to the one passed in
    @param aBox the Box object to point this vehicle's box variable to.
    **/
    public void setLoad(Box aBox)
    {
        box = aBox;
        box.moveTo(getX() + 20, getY() - 10);
    }
    /**
    Takes the box off the railcar, and sets its box variable to null
    @param x the X coordinate to move the box to
    @param y the Y coordinate to move the box to
    **/
    public void offLoad(int x, int y)
    {
        box.moveTo(x, y);
        box = null;
    }
    /**
    Checks to see if the box variable points to an object
    @return returns true if the box variable points to an object, false otherwise 
    **/
    public boolean hasLoad()
    {
        if (box != null)
        {
            return true;
        }
        return false;
    }
    /**
    Returns the box object that is on this railcar, and sets the reference to null
    @return the box object that is on this railcar
    **/
    public Box getLoad()
    {
        Box temp = box;
        box = null;
        return temp;
    }
    /**
       Draw the rail car
       @param g2 the graphics context
     */
    /**
    Draws the railcar. If the railcar has a trailer, this railcar calls the draw method
    of its trailer. If it has a box loaded on it, it also calls the draw method of the box
    @param g2 the graphics object to be drawn.
    **/
    public void draw(Graphics2D g2)
    {
        if(hasTrailer())
        {
            trailer.draw(g2);
        }
        if(hasLoad())
        {
            box.draw(g2);
        }
    g2.setColor(color);
	// think about whether getX() and getY() should be inherited
     // or defined in this class
	int xLeft = getX() ;
	int yTop = getY() ;
	
	Rectangle2D.Double body 
	    = new Rectangle2D.Double(getX(), yTop + UNIT, U6, UNIT);      
	Ellipse2D.Double frontTire 
	    = new Ellipse2D.Double(getX() + UNIT, yTop + U2, UNIT, UNIT);
	Ellipse2D.Double rearTire
	    = new Ellipse2D.Double(getX() + U4, yTop + U2, UNIT, UNIT);
	
	// the bottom of the front windshield
	Point2D.Double r1 
	    = new Point2D.Double(getX() + UNIT, yTop + UNIT);
	// the front of the roof
	Point2D.Double r2 
	    = new Point2D.Double(getX() + U2, yTop);
	// the rear of the roof
	Point2D.Double r3 
	    = new Point2D.Double(getX() + U4, yTop);
	// the bottom of the rear windshield
	Point2D.Double r4 
	    = new Point2D.Double(getX() + U5, yTop + UNIT);

	// the right end of the hitch
	Point2D.Double r5 
	    = new Point2D.Double(getX() + U6, yTop + U15);
	// the left end of the hitch
	Point2D.Double r6 
	    = new Point2D.Double(getX() + U6 + U05, yTop + U15);
	
	Line2D.Double frontWindshield 
	    = new Line2D.Double(r1, r2);
	Line2D.Double roofTop 
	    = new Line2D.Double(r2, r3);
	Line2D.Double rearWindshield
	    = new Line2D.Double(r3, r4);
	Line2D.Double hitch
	    = new Line2D.Double(r5, r6);

	g2.draw(body);
	g2.draw(hitch);
	g2.draw(frontTire);
	g2.draw(rearTire);
	g2.draw(body) ;
	// think about whether getNumber() should be inherited or
	// defined in this class
	g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;
    }
}
    
    
    
    
