import java.util.*;

class EjercicioEstructuras {
    public static void main(String[] args) {
        Registry registry = new Registry();
        ShapeI shapeI = (ShapeI) registry.createShape("I");
        shapeI.applyColor();
        shapeI.setName("I tetrimino");
        System.out.println(shapeI.getName());
        ShapeO shapeO = (ShapeO) registry.createShape("O");
        shapeO.applyColor();
        shapeO.setName("O tetrimino");
        System.out.println(shapeO.getName());
        ShapeJ shapeJ = (ShapeJ) registry.createShape("J");
        shapeJ.applyColor();
        shapeJ.setName("J tetrimino");
        System.out.println(shapeJ.getName());
        ShapeZ shapeZ = (ShapeZ) registry.createShape("Z");
        shapeZ.applyColor();
        shapeZ.setName("Z tetrimino");
        System.out.println(shapeZ.getName());
    }
}

// Prototype + bridge
class Registry {

    private Map<String, Shape> shapes = new HashMap<String, Shape>();

    public Registry() {
        loadShapes();
    }

    public Shape createShape(String type) {
        Shape shape = null;

        try {
            shape = (Shape) (shapes.get(type)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return shape;
    }

    private void loadShapes() {
        ShapeI shapeI = new ShapeI();
        shapes.put("I", shapeI);
        ShapeO shapeO = new ShapeO();
        shapes.put("O", shapeO);
        ShapeT shapeT = new ShapeT();
        shapes.put("T", shapeT);
        ShapeL shapeL = new ShapeL();
        shapes.put("L", shapeL);
        ShapeJ shapeJ = new ShapeJ();
        shapes.put("J", shapeJ);
        ShapeS shapeS = new ShapeS();
        shapes.put("S", shapeS);
        ShapeZ shapeZ = new ShapeZ();
        shapes.put("Z", shapeZ);
    }
}

abstract class Shape implements Cloneable {

    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shape() {

    }

    public Shape(String name) {
        this.name = name;
    }

    abstract public void applyColor();

}

class ShapeI extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying light blue color");
    }

}

class ShapeO extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying yellow color");
    }

}

class ShapeT extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying pink color");
    }

}

class ShapeL extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying orange color");
    }

}

class ShapeJ extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying blue color");
    }

}

class ShapeS extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying green color");
    }

}

class ShapeZ extends Shape {

    @Override
    public void applyColor() {
        System.out.println("Applying red color");
    }

}
