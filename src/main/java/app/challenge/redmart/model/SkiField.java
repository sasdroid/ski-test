package app.challenge.redmart.model;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkiField {
    private final Map<Integer, Coordinate> skiCoordinates;
    private final int skiLength;
    private final int skiWidth;
    private final PathManager pathManager;

    public SkiField(Stream<String> lines ) throws Exception{

        if(lines == null) throw new Exception("Invalid Input");
        this.pathManager = new PathManager();

        List<int[]> arrInt = lines.map(StringUtils::split).map(arr -> Stream.of(arr).filter(StringUtils::isNumeric).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());
        if(arrInt == null || arrInt.size() == 0 || arrInt.get(0).length != 2)
            throw new Exception("Unexpected input format");

        int[] skiArea = arrInt.remove(0);
        skiLength = skiArea[0];
        skiWidth = skiArea[1];
        this.skiCoordinates = new HashMap<>(skiLength * skiWidth);

        for(int widthIdx=0; widthIdx< arrInt.size(); widthIdx++){
            int[] arrLength = arrInt.get(widthIdx);
            for(int lengthIdx =0; lengthIdx< arrLength.length; lengthIdx++){
                int id = skiWidth * widthIdx + lengthIdx;
                skiCoordinates.put(id, new Coordinate(id, lengthIdx, widthIdx, arrLength[lengthIdx] ));
            }
        }
    }

    public Collection<Coordinate> getCoordinates(){
        return this.skiCoordinates.values();
    }

    public Coordinate getCoordinate(int length, int width){
        if(length > this.skiLength || length < 0 || width > this.skiWidth || width < 0) return null;
        int id = width * this.skiWidth + length;
        return skiCoordinates.get(id);
    }

    private Coordinate[] getAllPossibleNextCoordinates(Coordinate coordinate){
        if(coordinate == null) return null;

        Coordinate up = getCoordinate(coordinate.axis, coordinate.ordinate - 1);
        Coordinate down = getCoordinate(coordinate.axis, coordinate.ordinate + 1);
        Coordinate left = getCoordinate(coordinate.axis - 1 , coordinate.ordinate);
        Coordinate right = getCoordinate(coordinate.axis + 1 , coordinate.ordinate);

        List<Coordinate> possibleNextCoordinate = new ArrayList<>();
        if(up != null && up.elevation < coordinate.elevation) possibleNextCoordinate.add(up);
        if(down != null && down.elevation < coordinate.elevation) possibleNextCoordinate.add(down);
        if(left != null && left.elevation < coordinate.elevation) possibleNextCoordinate.add(left);
        if(right != null && right.elevation < coordinate.elevation) possibleNextCoordinate.add(right);

        return possibleNextCoordinate.toArray(new Coordinate[possibleNextCoordinate.size()]);
    }

    public Path getLongestFrom(Coordinate coordinate){
        if(coordinate == null) return null;
        Path path = this.pathManager.getLongestFrom(coordinate);
        if(path != null) return path;

        Coordinate[] nextCoordinates = getAllPossibleNextCoordinates(coordinate);

        // Find longest next path
        Path nextPath = null;
        for(Coordinate nextCoordinate: nextCoordinates){
            Path tempPath = new Path();
            tempPath.addCoordinate(coordinate);
            tempPath.addPath(getLongestFrom(nextCoordinate));
            if(nextPath == null || tempPath.isLongerThan(nextPath)) nextPath = tempPath;
        }

        path = new Path();

        if(nextPath != null) path.addPath(nextPath);
        else path.addCoordinate(coordinate);

        pathManager.addLongestPath(coordinate, path);

        return path;
    }

}
