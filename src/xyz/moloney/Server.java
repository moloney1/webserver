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
    private Request request;

    public static void main(String[] args) {
        Server s = new Server(4000);

        while (true) {
            try {
                s.client = s.socket.accept();
                s.in = new BufferedReader(new InputStreamReader(s.client.getInputStream()));
                s.getInput();
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

    private void getInput() throws IOException {
        if (this.in.ready()) {
            String line = this.in.readLine();
            StringBuilder req = new StringBuilder();
            while (this.in.ready()) {
                req.append(line).append("\n");
                line = this.in.readLine();
            }
            this.request = new Request(req.toString());
            respond();
        }

    }

    private void respond() throws IOException {

        Response r = new Response();
        r.setStatus(Status.OK);
        r.addHeader("Content-Type: text/html; charset=UTF-8");

        // r.setBody();

        // r.setBody("/sample.html");

        r.setBody(request.getRoute());

        System.out.println(r.getBody());

        byte[] out = r.toString().getBytes();

        output = new BufferedOutputStream(client.getOutputStream());

        output.write(out,0, out.length);
        output.flush();
        output.close();
    }



}
