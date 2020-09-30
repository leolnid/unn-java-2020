package ru.leolnid.app;

/**
 * @author Leonid Dyukov <leonid.dyukov@gmail.com>
 * @version 0.0.1
 *
 */
public class App {
    public enum Triangle {
        ACUTE, OBTUSE, STRAIGHT, INVALID
    }

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                Float a = Float.parseFloat(args[0]);
                Float b = Float.parseFloat(args[1]);
                Float c = Float.parseFloat(args[2]);

                Triangle cType = checkTriangle(a, b, c);
                switch (cType) {
                    case ACUTE:
                        System.out.println("Acute");
                        break;
                    case OBTUSE:
                        System.out.println("Obtuse");
                        break;
                    case STRAIGHT:
                        System.out.println("Straight");
                        break;
                    case INVALID:
                    default:
                        System.out.println("Invalid");
                }

            } catch (Exception e) {
                System.out.println("Excaption: " + e);
            }
        } else {
            System.out.println("Excaption: Invalid number of arguments");
        }

        System.out.println("Hello World!");
    }

    private interface Operation {
        boolean execute(float a, float b, float c);
    }

    private static boolean check(float a, float b, float c, Operation op) {
        return op.execute(a, b, c) || op.execute(a, c, b) || op.execute(b, c, a);
    }

    public static Triangle checkTriangle(float a, float b, float c) {
        if (check(a, b, c, (x, y, z) -> x + y < z))
            return Triangle.INVALID;

        a = a * a;
        b = b * b;
        c = c * c;

        if (check(a, b, c, (x, y, z) -> x + y == z))
            return Triangle.STRAIGHT;

        if (check(a, b, c, (x, y, z) -> x + y < z))
            return Triangle.OBTUSE;

        return Triangle.ACUTE;
    }
}
