import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Sample {
    public static void main(String[] args) {
        String requestDate = "2021-12-25";
        LocalDate hoy = LocalDate.parse(requestDate);
        LocalDate navidad = LocalDate.now();
        System.out.println(Math.abs(Duration.between(hoy.atStartOfDay(), navidad.atStartOfDay()).toDays()));
        int array[] = { 1, 2, 3, 4 };
        masGrande(array, 3);

        contarVocales("Cadena de caracteres.");
        invertirCadena("qwerty");
        comprobarCadenas("aei", "auo");
        comprobarCadenas("aei", "ou");
        encontrarPalabraMasLarga("Esto es una cadena");
    }

    public static void masGrande(int array[], int pos) {
        Arrays.sort(array);
        System.out.println(array[array.length - pos]);
    }

    public static void contarVocales(String cadena) {
        Long count = cadena.toLowerCase().chars().mapToObj(c -> (char) c)
                .filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u').count();
        System.out.println(count);
    }

    public static void contarCaracter(String cadena, char caracter) {
        Long count = cadena.toLowerCase().chars().filter(c -> c == caracter).count();
        System.out.println(count);
    }

    public static void invertirCadena(String cadena) {
        String reverse = new StringBuilder(cadena).reverse().toString();
        cadena.chars().mapToObj(e -> String.valueOf((char) e)).reduce((a, e) -> e.concat(a))
                .ifPresent(System.out::println);
        System.out.println(reverse);
    }

    public static void comprobarCadenas(String cadena, String cadena2) {
        Long count = cadena.chars().filter(c -> cadena2.indexOf(c) != -1).count();
        if (count > 0) {
            System.out.println("Contiene caracteres iguales");
        } else {
            System.out.println("No contiene caracteres iguales");
        }

    }

    public static void encontrarPalabraMasLarga(String cadena) {
        Optional<String> masLarga = Arrays.stream(cadena.split(" ")).max(Comparator.comparing(String::length));
        System.out.println("la palabra mas larga es: " + masLarga.orElse(""));
    }
}