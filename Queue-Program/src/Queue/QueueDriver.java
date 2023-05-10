package Queue;

public class QueueDriver {
	
	public static void main(String[] args) {
		// Create Sample "Job" Objects
		// Input a title and an id
		Job j1 = new Job("Mechanic", 123);
		Job j2 = new Job("Actor", 123109);
		Job j3 = new Job("CEO", 1);
		Job j4 = new Job("Software Engineer", 987654321);
		
		// Create a QueueObject with a LinkedList<T>
		// This is a generic object, meaning any data type can work as the input
		// It will automatically adjust to whatever we want
		QueueObject<Job> jobList = new QueueObject<Job>();
		
		// The output is the first object in line
		System.out.println("First Queue Jobs:");
		
		// Enqueue (add) each job to the list in order
		jobList.Enqueue(j1);
		jobList.Enqueue(j2);
		jobList.Enqueue(j3);
		jobList.Enqueue(j4);
		
		// This should print the first job in line
		System.out.println(jobList.Peek());
		
		// This for loop serves as an example of a First-In-First-Out container (FIFO)
		// As each job is dequeued, the list will move the next object in line to the front
		// The LinkedList<T> will automatically update and print out 
		// the "Peeked" (obtained but unaltered) output at the front of the line
		for (int i = 0; i < 4; i++)  {
			System.out.println(jobList.Peek());
			jobList.Dequeue();
		}
		
	}
	
}
