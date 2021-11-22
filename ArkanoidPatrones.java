import java.util.*;

public class ArkanoidPatrones {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        Bola bola = new Bola();
        LadrilloRojo ladrilloRojo = (LadrilloRojo) canvas.crearLadrillo("Rojo");
        ladrilloRojo.colorear();
        bola.colision(ladrilloRojo);
    }
}

class Canvas {
    private Map<String, Ladrillo> ladrillos = new HashMap<String, Ladrillo>();

    public Canvas() {
        this.cargarLadrillos();
    }

    public Ladrillo crearLadrillo(String type) {
        Ladrillo ladrillo = null;

        try {
            ladrillo = (Ladrillo) (ladrillos.get(type)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ladrillo;
    }

    private void cargarLadrillos() {
        LadrilloRojo ladrilloRojo = new LadrilloRojo();
        ladrillos.put("Rojo", LadrilloRojo);
        LadrilloVerde ladrilloVerde = new LadrilloVerde();
        ladrillos.put("Verde", ladrilloVerde);
    }
}

class Nave {
    public Nave() {

    }
}

class Bola {
    private int[][] posicion;

    public void storeAndExecute(Command command) {
        command.execute();
    }

    private void calcularPosicion() {
        System.out.println("Calculando siguiente posicion.");
    }

    public void colision(Object obj) {
        if (obj.getClass().equals(Ladrillo.class)) {
            Ladrillo ladrillo = (Ladrillo) obj;
            Command destruirLadrilloCommand = new DestruirLadrilloCommand(ladrillo);
            this.storeAndExecute(destruirLadrilloCommand);
            this.calcularPosicion();
        } else {
            this.calcularPosicion();
        }
    }

    public Bola() {
        System.out.println("Posición inicial");
    }
}

class Poder {
    public Poder() {

    }
}

abstract class Ladrillo implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Ladrillo() {

    }

    public void destruir() {
        System.out.println("Brick Destroyed!");
    }

    abstract public void applyColor();

}

class LadrilloRojo extends Ladrillo {

    LadrilloRojo() {

    }

    @Override
    public void colorear() {
        this.System.out.println("Applying Red color");
    }

}

class LadrilloVerde extends Ladrillo {

    LadrilloVerde() {

    }

    @Override
    public void colorear() {
        this.System.out.println("Applying Green color");
    }

}

interface Command {

    public void execute();

}

class DestruirLadrilloCommand implements Command {

    private Ladrillo ladrillo;

    public GenerateShapeCommand(Ladrillo ladrillo) {
            this.ladrillo = ladrillo;
        }

    @Override
    public void execute() {
        ladrillo.destruir();
    }

}
