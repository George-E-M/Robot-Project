package robot.views;

import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import robot.Robot;
import robot.RobotModel;
import robot.RobotModelEvent;
import robot.RobotModelEvent.EventType;
import robot.RobotModelListener;

public class Task2 extends Task1 implements RobotModelListener {
	
	public Task2(RobotModel model){
		super(model);
	}

	@Override
	public void update(RobotModelEvent event) {
		
		Object source = event.source();
		Object[] arrayPath = null;
		if (event.parent() != null){
			List<Robot> listPath = event.parent().path();
			arrayPath = listPath.toArray();
		}
		int[] childIndices = {event.index()};
		Object[] children = {(Object)event.operand()};
		
		if(event.eventType().equals(EventType.RobotAdded)){
			for (TreeModelListener listener : _listeners)
				listener.treeNodesInserted(new TreeModelEvent(source,arrayPath,childIndices,children));
		} else if(event.eventType().equals(EventType.RobotRemoved)) {
			for (TreeModelListener listener : _listeners){
				listener.treeNodesRemoved(new TreeModelEvent(source,arrayPath,childIndices,children));
			}
		}
	}
}
