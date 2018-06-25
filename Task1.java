package robot.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import robot.CarrierRobot;
import robot.Robot;
import robot.RobotModel;

public class Task1 implements TreeModel {
	private CarrierRobot _root;
	protected List<TreeModelListener> _listeners;
	
	public Task1(RobotModel model) {
		_root = model.root();
		_listeners = new ArrayList<TreeModelListener>();
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		_listeners.add(listener);
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof CarrierRobot) {
			CarrierRobot parentCarrier = (CarrierRobot)parent;
			try {
				Robot child = parentCarrier.robotAt(index);
				return child;
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		int result = 0;
		if (parent instanceof CarrierRobot) {
			CarrierRobot parentCarrier = (CarrierRobot)parent;
			result = parentCarrier.robotCount();
		}
		return result;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		int indexOfChild = -1;
		
		if(parent instanceof CarrierRobot && child instanceof Robot) {
			CarrierRobot parentCarrier = (CarrierRobot)parent;
			Robot childRobot = (Robot)child;
			indexOfChild = parentCarrier.indexOf(childRobot);
		}
		return indexOfChild;
	}

	@Override
	public Object getRoot() {
		return _root;
	}

	@Override
	public boolean isLeaf(Object node) {
		return !(node instanceof CarrierRobot);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		_listeners.remove(listener);
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
	}
}
	
