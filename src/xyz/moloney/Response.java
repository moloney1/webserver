package xyz.moloney;

import java.util.ArrayList;

public class Response {

    private String statusLine;
    private ArrayList<String> headers;
    private String body;

    public Response(int status) {
        this.statusLine = String.format("HTTP/1.1 %d %s", status, "OK");
        this.headers = new ArrayList<>();
    }

    public static void main(String[] args) {
        Response r = new Response(200);
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
        b.append(sample());

        return b.toString();
    }

    public String sample() {
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

}

