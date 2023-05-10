
// The example of inheritance comes from "extend" (going towards abstract class) and "Shape"
public class Triangle extends Shape {
	
	// Encapsulation occurs here
	// The data is private, and the methods above are how we access what we want from this data
	private double base;
	private double height;
	
	public Triangle (double base, double height) {
		this.base = base;
		this.height = height;
	}
	
	@Override
	public void print() {
		System.out.println("print a triangle");
	}
	
	@Override
	public void draw() {
		// Sample of 
		System.out.println("draw a triangle");
	}
	
	@Override
	public double getArea() {
		return ((0.5*base)*height);
	}
	

}
