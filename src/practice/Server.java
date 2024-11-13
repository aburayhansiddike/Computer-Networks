package practice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Server {
    static String getDate(){
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    static String getTime(){
        LocalTime time = LocalTime.now();
        return time.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }
    public static void main(String[] args) {
        try {
            System.out.println("Waiting for connection....");
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept();
            System.out.println("Connection accepted");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String clientRequest = bufferedReader.readLine();
                String reply = "";
                if(clientRequest.equals("date")){
                    reply = getDate();
                }else if(clientRequest.equals("time")){
                    reply = getTime();
                }else{
                    reply = clientRequest;
                }
                printWriter.println("Server : " + reply);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
