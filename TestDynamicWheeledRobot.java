package robot;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TestDynamicWheeledRobot {
	private MockPainter _painter;
	
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}
	
	/**
	 * Test to perform a bounce of the left boundary and to ensure that
	 * the robots position and color is correct and that the rectangle is filled.
	 */
	@Test
	public void testBounceOffLeft() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(20, 40, -23, 8);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 20,40,25,35)"
				+ "(java.awt.Color[r=0,g=0,b=0])(filledrectangle 0,48,25,35)"
				+ "(java.awt.Color[r=0,g=0,b=0])(filledrectangle 23,56,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the right boundary and to ensure that
	 * the robots position and color is correct and that the rectangle is filled.
	 */
	@Test
	public void testBounceOffRight() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(100, 20, 12, 15, 25, 35, Color.red);
		robot.paint(_painter);
		robot.move(135, 10000);
		robot.paint(_painter);
		robot.move(135, 10000);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 100,20,25,35)"
				+ "(java.awt.Color[r=255,g=0,b=0])(filledrectangle 110,35,25,35)"
				+ "(java.awt.Color[r=255,g=0,b=0])(filledrectangle 98,50,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the top boundary and to ensure that the
	 * robots position and color is correct and that the rectangle is not filled
	 */
	@Test
	public void testBounceOffTop() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(100, 20, 12, -23, 12, 45);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 100,20,12,45)"
				+ "(java.awt.Color[r=0,g=0,b=0])(rectangle 112,0,12,45)"
				+ "(java.awt.Color[r=0,g=0,b=0])(rectangle 124,23,12,45)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the bottom boundary and to ensure that the
	 * robots position and color is correct and that the rectangle is not filled
	 */
	@Test
	public void testBounceOffBottom() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(50, 80, 10, 20, 10, 10, Color.green);
		robot.paint(_painter);
		robot.move(10000, 100);
		robot.paint(_painter);
		robot.move(10000, 100);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 50,80,10,10)"
				+ "(java.awt.Color[r=0,g=0,b=0])(rectangle 60,90,10,10)"
				+ "(java.awt.Color[r=0,g=0,b=0])(rectangle 70,70,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the top left corner and to ensure the position and color
	 * is correct and that the rectangle is filled
	 */
	@Test
	public void testBounceOffTopRight() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(80, 10, 15, -15, 10, 20, Color.green);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 80,10,10,20)"
				+ "(java.awt.Color[r=0,g=255,b=0])(filledrectangle 90,0,10,20)"
				+ "(java.awt.Color[r=0,g=255,b=0])(filledrectangle 75,15,10,20)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the top right corner and to ensure the position and color
	 * is correct and that the rectangle is filled
	 */
	@Test
	public void testBounceOffTopLeft() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(20, 14, -24, -18, 34, 26, Color.blue);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		robot.move(10000, 10000);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 20,14,34,26)"
				+ "(java.awt.Color[r=0,g=0,b=255])(filledrectangle 0,0,34,26)"
				+ "(java.awt.Color[r=0,g=0,b=255])(filledrectangle 24,18,34,26)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the bottom left corner and to ensure the position and color
	 * is correct and that the rectangle is filled
	 */
	@Test
	public void testBounceOffBottomRight() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(73, 84, 12, 14, 22, 10, Color.red);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 73,84,22,10)"
				+ "(java.awt.Color[r=255,g=0,b=0])(filledrectangle 78,90,22,10)"
				+ "(java.awt.Color[r=255,g=0,b=0])(filledrectangle 66,76,22,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce off the bottom right corner and to ensure the position and color
	 * is correct and that the rectangle is filled
	 */
	@Test
	public void testBounceOffBottomLeft() {
		DynamicWheeledRobot robot = new DynamicWheeledRobot(13, 74, -16, 20, 19, 10, Color.blue);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		robot.move(100, 100);
		robot.paint(_painter);
		assertEquals("(java.awt.Color[r=0,g=0,b=0])(rectangle 13,74,19,10)"
				+ "(java.awt.Color[r=0,g=0,b=255])(filledrectangle 0,90,19,10)"
				+ "(java.awt.Color[r=0,g=0,b=255])(filledrectangle 16,70,19,10)", _painter.toString());
	}
}
