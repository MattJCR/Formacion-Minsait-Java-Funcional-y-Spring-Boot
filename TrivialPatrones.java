public class TrivialPatrones {

    interface Casilla {
        public void leerPregunta();

        public void responderPregunta(String respuesta);
    }

    class CasillaPregunta implements Casilla {
        protected String pregunta;
        protected String respuesta;
        protected String tipo;

        public CasillaPregunta(String pregunta, String respuesta, String tipo) {
            this.pregunta = pregunta;
            this.respuesta = respuesta;
            this.tipo = tipo;
        }

        @Override
        public void leerPregunta() {
            System.out.println(pregunta);
        }

        @Override
        public void responderPregunta(String respuesta) {
            if (this.respuesta.equals(respuesta)) {
                System.out.println("Pregunta correcta.");
            } else {
                System.out.println("Pregunta incorrecta.");
            }
        }
    }

    class CasillaDecoratorBase implements Casilla {
        private Casilla casilla;

        CasillaDecoratorBase(Casilla casilla) {
            this.casilla = casilla;
        }

        @Override
        public void leerPregunta() {
            casilla.leerPregunta();
        }

        @Override
        public void responderPregunta(String respuesta) {
            casilla.responderPregunta(respuesta);
        }
    }

    class CasillaQuesitoDecorator extends CasillaDecoratorBase {

        public CasillaQuesitoDecorator(Casilla casilla) {
            super(casilla);
        }

        @Override
        public void leerPregunta() {
            super.leerPregunta();
        }

        @Override
        public void responderPregunta(String respuesta) {
            if (this.respuesta.equals(this.respuesta)) {
                System.out.println("Pregunta correcta.");
                System.out.println("¡Quesito " + this.tipo + " conseguido!");
            } else {
                System.out.println("Pregunta incorrecta.");
            }
        }
    }

    public abstract class Ficha implements Cloneable {
        public String color;

        public Ficha() {
        }

        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public class Sombrero extends Ficha {
    
        public Sombrero() {

        }
    
    
        @Override
        public Sombrero clone() {
            return new Sombrero(this);
        }
    
    }

    class Tablero {
        private static Singleton instance;
        public List<Casilla> casillas;

        private Tablero() {
            this.casillas = new Arraylist<Casilla>();
        }

        public static Tablero getInstance(String value) {
            if (instance == null) {
                instance = new Singleton(value);
            }
            return instance;
        }
    }

    public static void main(String[] args) {

    }
}
