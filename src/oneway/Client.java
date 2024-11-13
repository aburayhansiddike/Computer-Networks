import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client started");
            Socket socket = new Socket("localhost", 9806);
            while(true){
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Client : ");
                String string = userInput.readLine();
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                output.println(string);

                BufferedReader serverInputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String serverOutput = serverInputBuffer.readLine();
                System.out.println(serverOutput);
                if(string.equals("bye")){
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
