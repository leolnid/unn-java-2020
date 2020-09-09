package example.ioexample;

import java.util.Scanner;

/**
 *
 * @author Evgeny Kozinov <evgeny.kozinov@itmm.unn.ru>
 */
public class Sum_scaner {
    public static void main(String[] args) {
        int a, b, c;
        Scanner scan = new Scanner(System.in);
        boolean f_input = true;
        if(f_input &= scan.hasNextInt())
            a = scan.nextInt();
        else a = -1; // без инициализации код не скомпилируеться! 
        if(f_input &= scan.hasNextInt())
            b = scan.nextInt();
        else b = -1;
        if(!f_input)
        {
            System.out.println("ошибка ввода");
            return;
        }
        c = a + b; // без инициализации a и b ошибка компиляции
        System.out.println(a + " + " + b + " = " + c);
    }   
}
