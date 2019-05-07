package xyz.moloney;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private ServerSocket socket;
    private BufferedReader in;
    private Socket client;
    private BufferedOutputStream output;
    private Route route;

    public static void main(String[] args) {
        Server s = new Server(4000);

        while (true) {
            try {
                s.client = s.socket.accept();
                s.in = new BufferedReader(new InputStreamReader(s.client.getInputStream()));
                System.out.println(s.getInput());
            } catch (IOException e) {
                System.err.println(e.toString());
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        }
    }

    public Server(int port) {
        this.port = port;
        try {
            initSocket();
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    private void initSocket() throws IOException {
        this.socket = new ServerSocket(this.port);
    }

    private String getInput() throws IOException {
        while (this.in.ready()) {
            String input = this.in.readLine();

            if (input != null) {
                this.route = new Route(input);
                System.out.println(route.getFilename());
                respond();
                return input;
            }
        }
        return null;
    }

    private void respond() throws IOException {
        output = new BufferedOutputStream(client.getOutputStream());
        //byte[] out = "200 (OK)\n".getBytes();
        StringBuilder b = new StringBuilder();
        b.append("HTTP/1.1 200 OK").append('\n');

       // byte[] out = "HTTP/1.1 200 OK".getBytes();
        byte[] out = "HTTP/1.1 200 OK".getBytes();
        output.write(out,0,out.length);
        output.flush();
    }



}
