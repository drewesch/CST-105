
// You can implement as many interfaces as needed
public class Rectangle extends Shape {
	
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	// This is the implementation of 'Printable'
	@Override
	public void print() {
		System.out.println("print a rectangle");
	}
	
	// This is the implementation of 'Drawable'
	@Override
	public void draw() {
		System.out.println("draw a rectangle");
	}
	
	@Override
	public double getArea() {
		return (width*height);
	}
	
	private double width;
	private double height;
}
