package example.ioexample;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Evgeny Kozinov <evgeny.kozinov@itmm.unn.ru>
 */
public class Sum_IS {
    public static void main(String[] args) throws IOException {
        String s;
        int a, b, c;
        DataInputStream dis = new DataInputStream(System.in); 
        s = dis.readLine();
        a = Integer.parseInt(s);
        s = dis.readLine();
        b = Integer.parseInt(s);
        c = a + b;
        System.out.println(a + " + " + b + " = " + c);
    }
    
}
