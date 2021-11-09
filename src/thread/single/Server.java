package thread.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class QuoteService {
    Map<String, String> productInfo = new HashMap<>();

    public QuoteService(){
        productInfo.put("a", "100");
        productInfo.put("b", "200");
    }

    public String getQuote(String product) {
        return productInfo.get(product);
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        QuoteService quoteService = new QuoteService();
        ServerSocket serverSocket =  new ServerSocket(9999);

        System.out.println("Start listening to 9999");

        while (true){
            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            System.out.println("Waiting for product information from the client.");
            byte[] buffer = new byte[100];
            int byteRead = in.read(buffer);
            String product = new String(buffer).trim();
            System.out.println("Receive product name: " + product);

            String price = quoteService.getQuote(product);
            if (price == null) {
                price = "Invalid product";
            }

            System.out.println("Response send...");
            out.write(price.getBytes());
            socket.close();
        }

    }
}
