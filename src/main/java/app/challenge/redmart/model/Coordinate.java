package app.challenge.redmart.model;

public class Coordinate {
    public final int id;
    public final int axis;
    public final int ordinate;
    public final int elevation;

    public Coordinate(final int id, final int axis, final int ordinate, final int elevation){
        this.id = id;
        this.axis = axis;
        this.ordinate = ordinate;
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "[axis:" + axis + "][ordinate:" + ordinate + "][elevation:" + elevation + "]";
    }
}
