package xyz.moloney;



public class Request {

    private String[] lines;
    private String method;
    private Route route;

    public Request(String req) {
        this.lines = req.split("\n");
        this.method = "GET";
        this.route = new Route(lines[0]);
        System.out.println(route.toString());
    }

    public Route getRoute() {
        return this.route;
    }

}
