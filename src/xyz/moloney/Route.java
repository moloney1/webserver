package xyz.moloney;

// Represents route of a HTTP request e.g. /test.html
public class Route {

    private final String route;
    private final String[] path;
    private final boolean isRoot;

    public Route(String request) {

        System.out.println("request:" + request);

        this.route = request.split(" ")[1];
        System.out.printf("Route: %s\n", route );

        if (!route.equals("/")) {
            this.path = route.split("/");
            this.isRoot = false;
        } else {
            this.path = new String[]{"/"};
            this.isRoot = true;
        }
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public String getFilename() {
        if (!isRoot) {
            return path[path.length - 1];
        }
        return null;
    }

    public String[] getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return this.route;
    }


}
