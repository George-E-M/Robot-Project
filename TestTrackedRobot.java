package robot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestTrackedRobot {
	private MockPainter _painter;
	
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}
	
	//Simple tests to ensure the tracked robot paints itself correctly when small and regular
	@Test
	public void testRegularPaint() {
		TrackedRobot robot = new TrackedRobot(100, 20, 12, 15, 50, 60);
		robot.paint(_painter);
		assertEquals("(line 100,50,120,20)(line 120,20,130,20)"
				+ "(line 130,20,150,50)(line 150,50,130,80)"
				+ "(line 130,80,120,80)(line 120,80,100,50)", _painter.toString());
	}
	
	@Test
	public void testSmallPaint() {
		//Default width and height are 25 and 35 respectively so will give a small tracked robot
		TrackedRobot robot = new TrackedRobot(70, 50, 12, 15);
		robot.paint(_painter);
		assertEquals("(line 70,67,82,50)(line 82,50,95,67)"
				+ "(line 95,67,82,85)(line 82,85,70,67)", _painter.toString());
	}
}
