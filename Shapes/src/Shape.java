
// This is the example of abstraction
// We implement these two interfaces without knowing it's general use
public abstract class Shape implements Drawable, Printable {
	// We know that Shape has an Area
	// But we don't know how to calculate that Area
	
	// We don't know how this is used, but we can separate this by Object to define this further
	// This method gets called on and calculated based on Object class
	public abstract double getArea();
	
	// Public method that gets the classname to a .toString and returns from getArea()
	public void PrintArea() {
		System.out.println("I am a " + super.getClass().toString() + " of area: " + getArea());
	}
}
