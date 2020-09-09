package example.ioexample;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Evgeny Kozinov <evgeny.kozinov@itmm.unn.ru>
 */
public class HelloName {
    public static void main(String[] args) throws IOException {
        String name; 
        DataInputStream dis = new DataInputStream(System.in); 
        name = dis.readLine();
        System.out.println("Hello, " + name + "!");
        // или 
        DataOutputStream dos = new DataOutputStream(System.out);
        dos.writeUTF("Hello, " + name + "!");
    }
}
