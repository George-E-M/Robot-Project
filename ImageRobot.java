package robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageRobot extends Robot {
	private BufferedImage _image;
	
	/**
	 * Creates an ImageRobot instance whose instance Variables are set
	 * to default values.
	 * @param imagePath the image to print
	 */
	public ImageRobot(String imagePath) {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, imagePath);
	}
	
	/**
	 * Creates an ImageRobot instance with specified values for instance
	 * variables
	 * @param x x position
	 * @param y y position
	 * @param imagepath the image to print
	 */
	public ImageRobot(int x, int y, String imagePath) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, imagePath);
	}
	
	/**
	 * Creates an ImageRobot instance with specified values for instance 
	 * variables.
	 * @param x x position
	 * @param y y position
	 * @param deltaX speed and direction for horizontal axis
	 * @param deltaY speed and direction for vertical axis
	 * @param imagePath image to print
	 */
	public ImageRobot(int x, int y, int deltaX, int deltaY, String imagePath) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, imagePath);
	}
	
	/**
	 * Creates an ImageRobot instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @oaram imagePath image to print
	 */
	public ImageRobot(int x, int y, int deltaX, int deltaY, int width, int height, String imagePath) {
		super(x,y,deltaX,deltaY,width,height);
		try {
			_image = ImageIO.read(new File(imagePath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Paints this ImageRobot object.
	 */
	public void doPaint(Painter painter) {
		painter.drawImage(_image, _x, _y, _width, _height);
	}
	
	
}
