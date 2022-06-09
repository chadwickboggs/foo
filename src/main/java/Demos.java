import java.util.Arrays;
import java.util.HashMap;


/**
 * @see {@linktourl <a href="http://dzone.com/articles/jdk-8-versus-jdk-10-ternaryunboxing-difference" target="_top">http://dzone.com/articles/jdk-8-versus-jdk-10-ternaryunboxing-difference</a>
 */
public class Demos {

    public static final String HELP = "\n\t$ java Demos [one|two|three]*\n";


    public static void main(String... args) {

        if (args == null || args.length == 0) {
            args = new String[] {"one", "two", "three"};
        }

        Arrays.stream(args).forEach(arg -> {
            switch (arg) {
                case "one":
                    example1();
                    break;

                case "two":
                    example2();
                    break;

                case "three":
                    example3();
                    break;

                default:
                    System.out.println(HELP);
                    break;
            }
        });

    }


    public static void example1() {

        try {
            final Double doubleValue = false ? 1.0 : new HashMap<String, Double>().get("1");
            System.out.println("Double Value: " + doubleValue);
        }
        catch (Exception exception) {
            System.out.println("ERROR in 'demoSerCeExample': " + exception);
        }

    }


    public static void example2() {

        try {
            final Double doubleValue = false ? Double.valueOf(1.0) : new HashMap<String, Double>().get("1");
            System.out.println("Double Value: " + doubleValue);
        }
        catch (Exception exception) {
            System.out.println("ERROR in 'demoSerCeExample': " + exception);
        }

    }


    public static void example3() {

        try {
            final double doubleValue = false ? 1.0 : new HashMap<String, Double>().get("1");
            System.out.println("Double Value: " + doubleValue);
        }
        catch (Exception exception) {
            System.out.println("ERROR in 'demoSerCeExample': " + exception);
        }

    }

}
