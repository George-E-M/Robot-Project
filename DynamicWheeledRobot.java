package robot;

import java.awt.Color;

public class DynamicWheeledRobot extends Robot {
	private static final Color DEFAULT_COLOR = Color.black;
	private Color _color;
	private int _currentdeltaX;
	private int _currentdeltaY;
	private Boolean _fillRect = false;
	
	
	/**
	 * Default constructor that creates a Dynamic wheeled robot instance whose 
	 * instance variables are set to default values.
	 */
	public DynamicWheeledRobot() {
		super();
		_color = DEFAULT_COLOR;
		_currentdeltaX = DEFAULT_DELTA_X;
		_currentdeltaY = DEFAULT_DELTA_Y;
	}
	
	/**
	 * Creates a Dynamic wheeled robot instance with specified values for instance
	 * variables
	 * @param x x position
	 * @param y y position
	 */
	public DynamicWheeledRobot(int x, int y) {
		super(x,y);
		_color = DEFAULT_COLOR;
		_currentdeltaX = DEFAULT_DELTA_X;
		_currentdeltaY = DEFAULT_DELTA_Y;
	}
	
	/**
	 * Creates a Dynamic wheeled robot instance with specified values for instance
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed and direction for horizontal axis
	 * @param deltaY speed and direction for vertical axis
	 */
	public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
		_color = DEFAULT_COLOR;
		_currentdeltaX = deltaX;
		_currentdeltaY = deltaY;
	}
	
	/**
	 * Creates a Dynamic wheeled robot instance with specified values for instance 
+
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_color = DEFAULT_COLOR;
		_currentdeltaX = deltaX;
		_currentdeltaY = deltaY;
	}
	
	public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
		_currentdeltaX = deltaX;
		_currentdeltaY = deltaY;
	}
	
	/**
	 * Creates a Dynamic wheeled robot instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color color of robot
	 */
	public DynamicWheeledRobot(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height,text);
		_color = color;
		_currentdeltaX = deltaX;
		_currentdeltaY = deltaY;
	}
	
	
	
	/**
	 * Paints this DynamicWheeledRobot object
	 */
	public void doPaint(Painter painter){
		//Check if the robot is hitting a corner
		if (_currentdeltaX != _deltaX && _currentdeltaY != _deltaY) {
			//If it hits a corner both speeds are changes and the rectangle is filled
			_fillRect = true;
			_currentdeltaX = _deltaX;
			_currentdeltaY = _deltaY;
		} else if (_currentdeltaX != _deltaX) {
			//If the robot hits a vertical wall the rectangle is filled and X speed changes
			_fillRect = true;
			_currentdeltaX = _deltaX;
		} else if (_currentdeltaY != _deltaY) {
			//If the robot hits a horizontal wall the rectangle isn't filled and Y speeed changes
			_fillRect = false;
			_currentdeltaY = _deltaY;
		}
		
		if (_fillRect){
			//If the rectangle is filled
			painter.setColor(_color);
			painter.fillRect(_x, _y, _width, _height);
			painter.setColor(DEFAULT_COLOR);
		} else {
			//If the rectangle is not filled
			painter.setColor(DEFAULT_COLOR);
			painter.drawRect(_x,_y,_width,_height);
		}
	}
}
