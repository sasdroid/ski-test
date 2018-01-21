package app.challenge.redmart.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PathManager {
    private final Map<Coordinate, Path> paths;

    public PathManager(){
        this.paths = new ConcurrentHashMap<>();
    }

    public void addLongestPath(Coordinate from, Path path){
        this.paths.put(from, path);
    }

    public Path getLongestFrom(Coordinate coordinate){
        if(coordinate == null) return null;

        return paths.get(coordinate);
    }
}
