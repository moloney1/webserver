package xyz.moloney;

public class Response {

    private String statusLine;

    public Response(int status) {
        this.statusLine = String.format("HTTP/1.1 %d %s\n", status, "OK");
    }

    public static void main(String[] args) {
        Response r = new Response(200);
        System.out.println(r.statusLine);
    }

    public String toString() {
        return this.statusLine;
    }

}
