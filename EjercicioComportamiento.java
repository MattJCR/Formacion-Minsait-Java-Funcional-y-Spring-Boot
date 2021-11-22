import java.util.zip.CheckedInputStream;

public class EjercicioComportamiento {
    public static void main(String[] args) {
        Shape shape = new Shape();
        ShapeContainer shapeContainer = new ShapeContainer();
        Board board = new Board();

        Command generateShapeCommand = new GenerateShapeCommand(shape);
        Command addContainerCommand = new AddContainerCommand(shapeContainer, shape);
        Command checkLineCommand = new checkLineCommand(shapeContainer);
        board.storeAndExecute(generateShapeCommand);
        board.storeAndExecute(addContainerCommand);
        board.storeAndExecute(checkLineCommand);
    }
}

class Shape {

    private String name;

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

    public generate(){
        this.name = "ShapeI";
        System.out.println("Pieza generada: " +  this.name);
    }
}

class Board {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}

class ShapeContainer {
    private int[][] container;

    public ShapeContainer() {

    }

    public int[][] getContainer() {
        return container;
    }

    public void setContainer(int[][] container) {
        this.container = container;
    }

    public void addShape(Shape shape) {
        System.out.println("Pieza añadida: " + shape.getName());
    }

    public void checkLine() {
        System.out.println("linea comprobada");
    }
}

class Score {
    int totalScore;
    int lines;
    int level;

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return lines;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}

interface Command {

    public void execute();

}

class GenerateShapeCommand implements Command {

    private Shape shape;

    public GenerateShapeCommand(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.generate();
    }

}

class AddContainerCommand implements Command {

    private ShapeContainer container;
    private Shape shape;

    public AddContainerCommand(ShapeContainer container, Shape shape) {
        this.container = container;
        this.shape = shape;
    }

    @Override
    public void execute() {
        container.addShape(shape);
    }
}

class checkLineCommand implements Command {

    private ShapeContainer container;

    public AddContainerCommand(ShapeContainer container) {
        this.container = container;
    }

    @Override
    public void execute() {
        container.checkLine();
    }
}