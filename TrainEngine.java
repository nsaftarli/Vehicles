//This file was given to us.

import java.awt.geom.* ;
import java.awt.* ;

/**
   Train Engine is a vehicle that can pull a chain of railcars
 */
public class TrainEngine extends Vehicle
{
    /**
       Constants
     */
    private static final double WIDTH = 35 ;
    private static final double UNIT = WIDTH / 5 ;
    private static final double LENGTH_FACTOR = 14 ; // length is 14U
    private static final double HEIGHT_FACTOR = 5 ; // height is 5U
    private static final double U_3 = 0.3 * UNIT ; 
    private static final double U2_5 = 2.5 * UNIT ; 
    private static final double U3 = 3 * UNIT ; 
    private static final double U4 = 4 * UNIT ; 
    private static final double U5 = 5 * UNIT ; 
    private static final double U10 = 10 * UNIT ; 
    private static final double U10_7 = 10.7 * UNIT ; 
    private static final double U12 = 12 * UNIT ; 
    private static final double U13 = 13 * UNIT ; 
    private static final double U14 = 14 * UNIT ; 
    public int widthOffset = (int)(U10 + U3 + UNIT);
    public int heightOffset = (int)(U4);
    public boolean clicked;
    
    /**
    Constructor: 
    @param x X position of railcar
    @param y Y position of railcar
    **/
    public TrainEngine(int x, int y)
    {
    	super(x, y);
    }
    /**
    Method isn't used for the train engine
    @return method is unnecessary, so it just returns a 0
    **/
    public int getNumber()
    {
    	return 0;
    }
    /**
    Method isn't used for the train engine
    **/
    public void setNumber(int num)
    {}
    /**
    Checks to see if this engine is clicked, by checking to see if the values of an event
    are contained within the dimensions of this engine.
    @param x X value of event
    @param y Y value of event
    **/
    public boolean isClicked(int x, int y)
    {
    	if((getX() <= x) && (getY() <= y))
    	{
    		if((getX() + (U10 + U3) >= x) && (getY() + U4 >= y))
    		{
                clicked = true;
    			return true;
    		}
    	}
        clicked = false;
    	return false;
    }
    /**
     Moves this engine and its trailers to the given destination
    @param x X coordinate to move the engine and its trailers to
    @param y Y coordinate to move the engine and its trailers to
    **/
    public void moveTo(int x, int y)
    {
    	setLoc(x, y);
        if(hasTrailer())
        {
            trailer.moveTo(getX() + widthOffset, getY());
        }
    }
    /**
    Sets the trailer to a given variable, attaching it at the end of the engine
    @param vehicle the Vehicle object to be attached to this one
    **/
    public void setTrailer(Vehicle vehicle)
    {
        trailer = vehicle;
        vehicle = null;
        trailer.isTrailer = true;
        trailer.moveTo(getX() + widthOffset, getY());
    }
    /**
    Removes the first trailer attached to this engine. If the first trailer of this
    object has other trailers, sets this object's trailer variable to point to other
    trailers. Sets this object's old trailer's trailer variable to null.
    **/
    public void removeFirstTrailer()
    {
        Vehicle temp = trailer;
        if(trailer.hasTrailer())
        {
            setTrailer(trailer.trailer);
        }
        temp.trailer = null;
        temp.moveTo(50, 50);
        temp.isTrailer = false;
        temp = null;
        if(!trailer.hasTrailer())
        {
            trailer = null;
        }
    }
    /**
    Removes the last trailer at the end of the list. Iterates through the trailers until
    one is reached with a null trailer variable. Moves that trailer and sets the reference
    to null.
    @param vehicle the vehicle to remove from the list
    **/
    public void removeLastTrailer(Vehicle vehicle)
    {
        Vehicle temp = vehicle.trailer;
        vehicle.trailer = null;
        temp.isTrailer = false;
        temp.moveTo(75, 40);  
    }
    /**
    Not used in the train engine, but must be overriden
    **/    
    public void setLoad(Box box)
    {}
    /**
    Not used in the train engine, but must be overriden
    **/ 
    public void offLoad(int x, int y)
    {}
    /**
    Not used in the train engine, but must be overriden
    **/ 
    public Box getLoad()
    {
        throw new NullPointerException();
    }
    /**
    Not used in the train engine, but must be overriden
    **/ 
    public boolean hasLoad()
    {
        return true;
    }
    /**
       Draws the train engine
       @param g2 the graphics context
     */
    /**
    Draws the train engine. If the engine has any trailers attached to it, it also calls
    the draw methods of those trailer variables.
    @param g2 the object to be drawn.
    **/
    public void draw(Graphics2D g2)
    {
        if(hasTrailer())
        {
            trailer.draw(g2);
        }
	// decide whether to implement getX() and getY() in this
     // class or in superclass
	int x1 = getX() ;
	int y1 = getY() ;
	Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
							 U3, U3 ) ;
	g2.setColor(Color.blue) ;
	g2.fill(hood) ;

	Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
							 y1,
							 U10, U4) ;
	g2.setColor(Color.blue) ;
	g2.fill(body) ;

	Line2D.Double hitch = new Line2D.Double(x1 + U13,
						y1 + U2_5,
						x1 + U14, 
						y1 + U2_5) ;
	g2.setColor(Color.black) ;
	g2.draw(hitch) ;

	Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
						       y1 + U4, 
							 UNIT, UNIT) ;
    if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
	{
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel1) ;

	Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
    {
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel2) ;

	Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT, 
						       y1 + 4 * UNIT, 
							 UNIT, UNIT) ;
	if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
    {
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel3) ;

	Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
    {
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel4) ;

	Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
						       y1 + U4, 
							 UNIT, UNIT) ;
	if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
    {
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel5) ;
	
	Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT, 
		       y1 + U4, 
			 UNIT, UNIT) ;
	if(clicked)
    {
        g2.setColor(Color.RED);
    }
    else
    {
        g2.setColor(Color.black) ;
    }
	g2.fill(wheel6) ;
	
	
    }
}
