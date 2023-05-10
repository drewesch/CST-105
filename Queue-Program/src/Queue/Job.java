package Queue;

public class Job {
	
	// Imagine this as a generic object that is one of many substitutes
	// for the generic QueueObject class
	
	public Job(String title, int id) {
		super();
		this.title = title;
		this.id = id;
	}
	
	// Two encapsulated variables
	private String title;
	private int id;
	
	// Job Getters & Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// Requires toString() to print out each job in order
	@Override
	public String toString() {
		return "Job [title=" + title + ", id=" + id + "]";
	}
}
