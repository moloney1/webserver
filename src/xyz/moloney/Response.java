package xyz.moloney;

import java.io.*;
import java.util.ArrayList;

public class Response {

    private static final String ROOT = "./static";
    private static final String DEFAULT = "/index.html";

    private String statusLine;
    private ArrayList<String> headers;
    private String body;

    public BufferedReader requested;

    public Response(int status) {
        this.statusLine = String.format("HTTP/1.1 %d %s", status, "OK");
        this.headers = new ArrayList<>();
    }

    public static void main(String[] args) {
        Response r = new Response(200);
        r.setBody();
        System.out.println(r.statusLine);

    }

    public void addHeader(String s) {
        headers.add(s);
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(this.statusLine).append("\n");
        for (String s : headers) {
            b.append(s).append("\n");
        }

        b.append("\n");
        b.append(this.body);

        return b.toString();
    }

    public void setBody() {
        this.body = Response.sample();
    }

    // TODO allow this to take instance or Route instead
    public void setBody(String filepath) {

        try {
            this.requested = new BufferedReader(new FileReader(ROOT + filepath));
            // System.out.println(requested.readLine());
            if (requested.ready()) {
                String line = this.requested.readLine();
                StringBuilder req = new StringBuilder();
                while (this.requested.ready()) {
                    req.append(line).append("\n");
                    line = this.requested.readLine();
                }
                this.body = req.toString();
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        System.out.printf("Interpreted path: %s\n", ROOT + filepath);
    }

    public static String sample() {
        return //"\n" +
                "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>It works!</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>My time machine works!</h2>\n" +
                "        Nice.\n" +
                "</body>\n" +
                "</html>";
    }

    public String getBody() {
        return this.body;
    }

}

