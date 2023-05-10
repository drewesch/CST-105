package Queue;

import java.util.LinkedList;

public class QueueObject<T> {
	
	// This object serves to create a generic "queue"
	// The letter "T" is a placeholder for Type
	// This entails that any data type (from primitive to complex objects)
	// will be automatically substituted and allow Java to work with it
	private LinkedList<T> queue = new LinkedList<T>();
	
	// Add new items to the back of the line
	// the .add(input) method automatically adds it to the back
	// It has an optional parameter, which is the index number
	// For example, .add(0, t), it would add it at index 0
	// However, that is not necessary for this assignment
	public void Enqueue(T t) {
		queue.add(t);
	}
	
	// Same thing, just remove the object in front
	public void Dequeue() {
		queue.remove();
	}
	
	// Get the object at the front of the line, but do not alter it
	public T Peek() {
		return queue.get(0);
	}
}
