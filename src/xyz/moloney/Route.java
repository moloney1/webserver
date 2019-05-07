package xyz.moloney;

// Represents route of a HTTP request e.g. /index.html
public class Route {

    private final String route;
    private final String[] dirs;
    private final boolean isRoot;

    public Route(String request) {

        this.route = request.split(" ")[1];
        //System.out.printf("Route: %s\n", route );

        if (!route.equals("/")) {
            this.dirs = route.split("/");
            this.isRoot = false;
        } else {
            this.dirs = new String[]{"/"};
            this.isRoot = true;
        }
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public String getFilename() {
        if (!isRoot) {
            return dirs[dirs.length - 1];
        }
        return null;
    }

    public String[] getDirs() {
        return this.dirs;
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();
        for (String s : this.dirs) {
            b.append(s).append("/");
        }
        b.append("\n");
        return b.toString();
    }

}
