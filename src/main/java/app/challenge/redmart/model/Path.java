package app.challenge.redmart.model;

import java.util.LinkedList;

public class Path {
    private final LinkedList<Coordinate> coordinates;

    public Path(){
        this.coordinates = new LinkedList<>();
    }

    public void addCoordinate(Coordinate coordinate){
        this.coordinates.add(coordinate);
    }

    public void addPath(Path path){
        if(path == null) return;
        this.coordinates.addAll(path.coordinates);
    }

    public int getLength(){
        return coordinates.size();
    }

    public int getDrop(){
        Coordinate first = this.coordinates.getFirst();
        Coordinate last = this.coordinates.getLast();
        if(first == null || last == null) return 0;

        return first.elevation - last.elevation;
    }

    public boolean isLongerThan(Path path){
        if(this.getLength() == path.getLength()) return this.getDrop() > path.getDrop();
        return this.getLength() > path.getLength();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[length:" + this.getLength() + "][drop:" + this.getDrop() + "]");
        this.coordinates.forEach(sb::append);
        return sb.toString();
    }
}
