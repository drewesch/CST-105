
public class TestDriver {

	public static void main(String[] args) {
		// This works because Circle IS-A Printable
		// Remember: An interface is used to declare an object, but it can never
		// to create an object
		// Object creation requires a Concrete class (what we're used to)
		// A Concrete class has implementation for all methods

		// Call to printable using a new interface executable
		// Shape <- IS-A <- Circle
		// Create a circle with radius of 2, implementing from the abstract class "Shape"
		// This is polymorphism at it's fundamental level
		// It behaves as the "Shape" abstract class, but calls the object class "Circle" with parameters
		Shape shape = new Circle(2);
		shape.PrintArea();
		// Call method doPrint & doDraw from Object
		doPrint(shape);
		doDraw(shape);
		
		// Redefine Shape to a Rectangle with width of 3 and length of 2
		shape = new Rectangle(3, 2);
		shape.PrintArea();
		// Call method doPrint & doDraw from Object
		doPrint(shape);
		doDraw(shape);
		
		// Redefine Shape to a Triangle with a base of 10 and a height of 5
		shape = new Triangle(10, 5);
		shape.PrintArea();
		// Call method doPrint & doDraw from Object
		doPrint(shape);
		doDraw(shape);
	}
	
	// Method calls Printable interface using "p" variable from an object input
	static void doPrint(Printable p) {
		// Does the method "print" in Printable interface using object input
		p.print();
	}
	
	// Repeat for Drawable
	static void doDraw(Drawable d) {
		d.draw();
	}

}
