import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class AssignComp extends JComponent
{
	//create an array list of vehicles, same as the last one.
	protected ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	//Create train engine variable, because there's only one
	protected TrainEngine engine;
	//selected variable will hold reference to one clicked vehicle
	protected Vehicle selected;
	//make a variable for number of RailCars, to make sure there can only be 5
	private int numberOfRailcars;
	//Vehicle iterator;
	protected BoxQueue queue;

	public AssignComp()
	{
		//initializes numberOfRailcars variable, which will ensure there are only 5 railcars at a time
		numberOfRailcars = 1;
		class MouseClickListener implements MouseListener
		{
			public void mousePressed(MouseEvent event)
			{
				//if the engine variable is null, makes a new engine at button click. Adds engine to vehicles
				if(engine == null)
				{
					engine = new TrainEngine(event.getX(), event.getY());
					vehicles.add(engine);
					repaint();
				}
				//otherwise, if there are fewer than 5 railcars, draw railcars, and increment variable that keeps track of them
				else if(numberOfRailcars < 6)
				{
					vehicles.add(new RailCar(event.getX(), event.getY(), numberOfRailcars++));
					repaint();
				}
				//after engine and 5 railcars are drawn, draw a Queue of boxes at event.
				else if(queue == null)
				{
					queue = new BoxQueue(event.getX(), event.getY());
					repaint();
				}
				/*
				Once all objects have been created:
				1. Program loops through vehicles and deselects everything
				2. if a specific vehicle is clicked but the vehicle is not a trailer, it runs the select method on only that vehicle
				*/
				else
				{
					for(int i = 0; i < vehicles.size(); i++)
					{
						deselect();
						if(vehicles.get(i).isClicked(event.getX(), event.getY()) && !vehicles.get(i).isTrailer)
						{
							select(vehicles.get(i));
							break;
						}
					}	
				}
			}
			public void mouseReleased(MouseEvent event)
			{
				/*
				If selected points to an object that is not the train engine, and the selected object contains the mouse:
				Loop through vehicles, and if a non-selected vehicle also contains the mouse, but doesn't have a trailer, set its trailer to selected
				*/
				if(selected != null && !selected.equals(engine) && selected.isClicked(event.getX(), event.getY()))
				{
					for(int i = 0; i < vehicles.size(); i++)
					{
						if(!vehicles.get(i).equals(selected) && vehicles.get(i).isClicked(event.getX(), event.getY()) && !vehicles.get(i).hasTrailer())
						{
							vehicles.get(i).setTrailer(selected);
							deselect();
							repaint();
						}
					}
				}
			}
			public void mouseClicked(MouseEvent event)
			{}
			public void mouseEntered(MouseEvent event)
			{}
			public void mouseExited(MouseEvent event)
			{}
		}
		MouseListener clickListener = new MouseClickListener();
		this.addMouseListener(clickListener);

		class MouseMoveListener implements MouseMotionListener
		{
			public void mouseDragged(MouseEvent event)
			{
				/*
				If the selected vehicle is not a trailer:
				Continuously move the selected vehicle to the mouse
				*/
					if(!selected.isTrailer)
					selected.moveTo(event.getX(), event.getY());
					repaint();
			}
			public void mouseMoved(MouseEvent event)
			{}
		}
		MouseMotionListener moveListener = new MouseMoveListener();
		this.addMouseMotionListener(moveListener);
	}

	public void paintComponent(Graphics g)
	{
		/*
		Loops through vehicles, and draws vehicles if and only if they are not trailers
		*/
		for(int i = 0; i < vehicles.size(); i++)
		{
			Graphics2D g2 = (Graphics2D) g;
			if(!vehicles.get(i).isTrailer)
			{
				vehicles.get(i).draw(g2);
			}
		}
		/*
		If the queue is null, draws the queue object
		*/
		if(queue != null)
		{
			Graphics2D g2 = (Graphics2D) g;
			queue.draw(g2);
		}
		
	}

	/**
		Method for selecting a vehicle
		@param vehicle the vehicle type object to be selected
	**/
	public void select(Vehicle vehicle)
	{
		/*
		Vehicle to be selected is passed as a parameter. 
		Selected points to parameter. 
		Loops through vehicles, and if any of them are not the selected vehicle, it makes their colour black
		*/
		selected = vehicle;
		selected.setColor(Color.RED);
		for(int i = 0; i < vehicles.size(); i++)
		{
			if(!vehicles.get(i).equals(selected))
			{
				vehicles.get(i).setColor(Color.BLACK);
			}
		}
		//If the selected vehicle has a trailer, loop through the trailers and set them all to red
		if(vehicle.hasTrailer())
		while(selected.hasTrailer())
		{
			selected.getTrailer().setColor(Color.RED);
			selected = selected.getTrailer();
		}
		//Makes the selected variable point to the parameter object again
		selected = vehicle;
		repaint();
	}

	/**
		Method for deselecting everything
	**/
	public void deselect()
	{
		/*
		Loops through the vehicles, and paints all of them black
		*/
		for(int i = 0; i < vehicles.size(); i++)
		{
			vehicles.get(i).setColor(Color.BLACK);
		}
		engine.clicked = false;
		selected = null;
		repaint();
	}
	/**
	Method for adding selected railcar(s) to the end of the train
	**/
	public void addLast()
	{
		Vehicle iterator = engine;
		/*
		If there is a selected object and it is not the engine:
		1: If the engine does not have a trailer, make the selected object its trailer
		2: otherwise, loop through until a link without a trailer is found, and set the selected vehicle to be the trailer of that link
		*/
		if(selected != null && !selected.equals(engine))
		{
			if(!engine.hasTrailer())
			{
				engine.setTrailer(selected);
			}
			else
			{
				while(iterator.hasTrailer())
				{
					iterator = iterator.getTrailer();
				}
				iterator.setTrailer(selected);
			}
			deselect();
		}
	}
	/**
	Method for adding selected railcar(s) to the front of the train, moving any railcars that may be connected already
	**/
	public void addFirst()
	{
		/*
		If the selected variable points to an object that is not the engine:
		1: Create a variable that acts as an iterator
		2: if the engine does not have a trailer, set the selected variable to be that trailer
		3: Otherwise, iterate through trailers until the last one is reached, and set its trailer to be the one the engine currently points to
		4: Set the engine's trailer to the first link in the selected chain
		*/
		if(selected != null && !selected.equals(engine))
		{
			Vehicle iterator = selected;
			if(!engine.hasTrailer())
			{
				engine.setTrailer(selected);
			}
			else
			{
				while(iterator.trailer != null)
				{
					iterator = iterator.trailer;
				}
				iterator.setTrailer(engine.getTrailer());
				engine.setTrailer(selected);
			}
			deselect();
		}
	}
	/**
	Method for removing the very first railcar behind the engine
	**/
	public void removeFirst()
	{
		//If the engine has a trailer, run a method to remove it.
		if(engine.hasTrailer())
		{
			engine.removeFirstTrailer();
			deselect();
			repaint();
		}
	}
	/**
	Method for removing the last railcar connected to the engine
	**/
	public void removeLast()
	{
		/*
		1. Create an iterator that points to the engine. 
		2. Loops through the list until it hits an element that does not have a trailer
		3. Runs the method to remove the last trailer within the engine
		*/
		Vehicle iterator = engine;
		while(iterator.getTrailer().hasTrailer())
		{
			iterator = iterator.getTrailer();
		}
		engine.removeLastTrailer(iterator);
		deselect();
		repaint();
	}
	/**
	Method for creating a new instance of the program
	**/
	public void reset()
	{
		//sets all variables back to their initial values
		vehicles = new ArrayList<Vehicle>();
		selected = null;
		engine = null;
		queue = null;
		numberOfRailcars = 1;
		repaint();
	}
	/**
	Method for removing a box off the queue of boxes, and placing it on a selected railcar
	**/
	public void removeBox()
	{
		Vehicle iterator = selected;
		//if the selected variable points at an object and does not already carry a box, run the object's setLoad method, with the input being the output of the queue's removeBox method.
		if(selected != null && !selected.hasLoad())
		{
			selected.setLoad(queue.removeBox());
			repaint();
		}
		/*otherwise, if the selected variable points at an object, but the selected object does have a load:
		1: Iterate through links until one without a load is found
		2: Set the load to the box returned by running the queue's remove method.
		*/
		else if(selected != null && selected.hasLoad())
		{
			while(iterator.hasLoad())
			{
				iterator = iterator.trailer;
			}
			iterator.setLoad(queue.removeBox());
			repaint();
		}
	}
	/**
	Method for adding a box to the queue from a railcar
	**/
	public void addBox()
	{
		Vehicle iterator = selected;
		Box tempBox;
		//If the iterator is at the position of the engine and if the engine has a trailer, move the iterator to reference the trailer
		if(iterator.equals(engine))
		{
			if(iterator.trailer != null)
			{
				iterator = iterator.trailer;
			}
		}
		/*
		If the iterator points to an object or doesn't have a load:
		Iterate through trailers until one with a load is found. Use the addBox method of the queue
		*/
		if(iterator != null || !iterator.hasLoad())
		{
			while(!iterator.hasLoad())
			{
				iterator = iterator.trailer;
			}
			tempBox = iterator.getLoad();
			queue.addBox(tempBox);

		}
		repaint();

	}
}

