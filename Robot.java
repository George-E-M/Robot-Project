package robot;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Robot. This class
 * defines state common to all special kinds of Robot instances and implements
 * a common movement algorithm. Robot subclasses must override method paint()
 * to handle robot-specific painting.
 * 
 * @author Craig Sutherland
 * 
 */
public abstract class Robot {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;
	
	// ===
	
	// === Parent Carrier Robot.
	protected CarrierRobot _parentRobot;
	// ===
	
	//=== Input text
	protected String _text;
	

	/**
	 * Creates a Robot object with default values for instance variables.
	 */
	public Robot() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Robot object with a specified x and y position.
	 */
	public Robot(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Robot instance with specified x, y, deltaX and deltaY values.
	 * The Robot object is created with a default width and height.
	 */
	public Robot(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Robot instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Robot(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}
	
	/**
	 * Creates a robot instance with specified values
	 */
	public Robot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		this(x, y, deltaX, deltaY, width, height);
		_text = text;
	}
	
	/**
	 * Moves this Robot object within the specified bounds. On hitting a 
	 * boundary the Robot instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width width of two-dimensional world.
	 * @param height height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;
		
		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}
		
		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;
	}
	
	/**
	 * Returns the CarrierRobot that contains the Robot that method Parent
	 * is called on. If the callee object is not a child within a 
	 * CarrierRobot instance this method returns null.
	 */
	public CarrierRobot parent() {
		return _parentRobot;
	}
	
	/**
	 * Returns an ordered list of Robot objects. The first item within the
	 * list is the root CarrierRobot of the containment hierarchy.
	 * The last item within the list is the callee object (hence this 
	 * method always returns a list with at least one item). Any intermediate
	 * items are CarrierRobots that connect the root CarrierRobot to the 
	 * callee Robot. E.g., given:
	 * 
	 * 		CarrierRobot root = new CarrierRobot();
	 * 		CarrierRobot intermediate = new CarrierRobot();
	 * 		Robot flying = new FlyingRobot();
	 * 		root.add(intermediate);
	 * 		intermediate.add(flying);
	 * 
	 * a call to flying.path() yields: [root, intermediate, flying]
	 */
	public List<Robot> path() {
		List<Robot> robotPath = new ArrayList<Robot>();
		robotPath.add(this);
		CarrierRobot parent = _parentRobot;
		
		//While
		while(parent != null) {
			robotPath.add(0, parent);
			parent = parent._parentRobot;
		}
		return robotPath;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	protected abstract void doPaint(Painter painter);
	
	/**
	 * Template method which calls individual robot sub-classes paintRobot method
	 * to paint the robot and any potential text.
	 * @param painter the Painter object used for drawing
	 */
	public void paint(Painter painter) {
		doPaint(painter);
		painter.drawCentredText(_text, _x, _y, _width, _height);
	}

	/**
	 * Returns this Robot object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Robot object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Robot object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Robot object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Robot's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Robot's height.
	 */
	public int height() {
		return _height;
	}
	
	/**
	 * Sets the Robots text
	 */
	public void setText(String text) {
		_text = text;
	}
	
	public String text() {
		return _text;
	}
	
	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a WheeledRobot instance, this method 
	 * will return "robot.WheeledRobot".
	 */
	public String toString() {
		return getClass().getName();
	}
}
