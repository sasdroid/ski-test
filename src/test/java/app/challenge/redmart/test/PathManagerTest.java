package app.challenge.redmart.test;

import app.challenge.redmart.model.Coordinate;
import app.challenge.redmart.model.Path;
import app.challenge.redmart.model.PathManager;
import app.challenge.redmart.model.SkiField;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PathManagerTest {

    private PathManager pathManager;

    @Before
    public void init() throws Exception{
        this.pathManager = new PathManager();
    }

    @Test
    public void givenValidCoordinateAndPath_whenAddLongestPath_thenAddNewCoordinatePathMapping(){
        Coordinate coordinate = new Coordinate(1, 0, 0, 4);
        Coordinate nextCoordinate = new Coordinate(2, 0, 0, 2);
        Path path = new Path();
        path.addCoordinate(coordinate);
        path.addCoordinate(nextCoordinate);

        pathManager.addLongestPath(coordinate, path);
        assertTrue("Longest Path is not added", pathManager.getLongestFrom(coordinate) != null && pathManager.getLongestFrom(coordinate).getDrop() == path.getDrop());
    }
}
