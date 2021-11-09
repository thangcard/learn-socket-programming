package thread.multi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Client(2) connecting to server...");
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println("Connected to server...");

        System.out.println("Enter product name: ");
        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine();

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        System.out.println("Sending product information...");
        out.write(product.getBytes());

        byte[] response = new byte[1024];
        int byteRead = in.read(response);

        System.out.println("Obtain response is: " + new String(response).trim());

        socket.close();
    }

}
