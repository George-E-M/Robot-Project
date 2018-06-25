package robot;

import java.util.ArrayList;
import java.util.List;

public class CarrierRobot extends Robot {
	private List<Robot> _childRobots;
	
	/**
	 * Creates a Carrier object with default values for state.
	 */
	public CarrierRobot() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a CarrierRobot object with specified location values, default values for other state items.
	 */
	public CarrierRobot(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a CarrierRobot with specified values for location, velocity and direction. Non-specified state
	 * items take default values.
	 */
	public CarrierRobot(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a CarrierRobot with specified values for location, velocity, direction, width and height.
	 */
	public CarrierRobot(int x, int y, int deltaX, int deltaY, int width, int height) {
		this(x,y,deltaX,deltaY,width,height,null);
	}
	
	public CarrierRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
		_childRobots = new ArrayList<Robot>();
	}
	
	/**
	 * Moves a CarrierRobot object (including its children) within the bounds specified by arguments width
	 * and height.
	 */
	public void move(int width, int height) {
		//Calls the abstract Robot class move method on itself and all its children.
		super.move(width,height);
		for(Robot robot : _childRobots) {
			robot.move(_width,_height);
		}
	}
	
	/**
	 * Paints a CarrierRobot object by drawing a rectangle around the edge of its bounding box. The
	 * CarrierRobot object's children are then painted.
	 */
	public void doPaint(Painter painter) {
		painter.drawRect(_x, _y, _width, _height);
		
		for(Robot robot : _childRobots) {
			painter.translate(_x, _y);
			robot.paint(painter);
			//Changes the coordinate system back when it is done
			painter.translate(-_x, -_y);
		}
	}
	
	/**
	 * Attempts to add a Robot to a CarrierRobot object. If successful, a two-way link is established
	 * between the CarrierRobot and the newly added Robot. Note that this method has package visibility -
	 * for reasons that will become apparent in Robot 3.
	 * @param robot the robot to be added.
	 * @throws IllegalArgumentException if an attempt is made to add a Robot to a CarrierRobot instance
	 * where the Robot argument is already a child within a CarrierRobot instance. An IllegalArgumentException
	 * is also thrown when an attempt is made to add a Robot that will not fit within the bounds of the 
	 * proposed CarrierRobot object.
	 */
	void add(Robot robot) throws IllegalArgumentException {
		//Makes sure the robot doesn't already have a parent already and that it fits in
		//the  parent.
		if (robot._x + robot._width > _width || robot._y + robot._height > _height
				|| robot._parentRobot != null) {
			throw new IllegalArgumentException();
		} else {
			_childRobots.add(robot);
			robot._parentRobot = this;
		}
	}
	
	/**
	 * Removes a particular Robot from a CarrierRobot instance. Once removed, the two-way link between
	 * the CarrierRobot and its former child is destroyed. This method has no effect if the Robot specified
	 * to remove is not a child of the CarrierRobot. Note that this method has package visibility - for
	 * reasons that will become apparent in robot 3.
	 * @param robot the robot to be removed.
	 */
	void remove(Robot robot) {
		_childRobots.remove(robot);
		robot._parentRobot = null;
	}
	
	/**
	 * Returns the Robot at a specified position within a CarrierRobot. If the position specified is less
	 * than zero or greater than the number of children stored in the CarrierRobot less one this method
	 * throws an IndexOutOfBoundsException.
	 * @param index the specified index position
	 */
	public Robot robotAt(int index) throws IndexOutOfBoundsException {
		if(index < this.robotCount()) {	
			return _childRobots.get(index);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Returns the number of children contained within a CarrierRobot object. Note this method is not
	 * recursive - it simply returns the number of children at the top level within the callee
	 * CarrierRobot object.
	 */
	public int robotCount() {
		return _childRobots.size();
	}
	
	/**
	 * Returns the index of a specified child within a CarrierRobot object. If the Robot specified is no
	 * actually a child of the CarrierRobot this method returns -1; otherwise the value returned is in the
	 * range 0 ... robotCount() - 1.
	 * @param the robot whose index position within the CarrierRobot is requested.
	 */
	public int indexOf(Robot robot) {
		return _childRobots.indexOf(robot);
	}
	
	/**
	 * Returns true if the Robot argument is a child of the CarrierRobot object on which this method is
	 * called, false otherwise.
	 */
	public boolean contains(Robot robot) {
		return _childRobots.contains(robot);
	}
}
