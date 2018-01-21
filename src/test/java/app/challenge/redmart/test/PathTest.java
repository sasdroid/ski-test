package app.challenge.redmart.test;

import app.challenge.redmart.model.Coordinate;
import app.challenge.redmart.model.Path;
import app.challenge.redmart.model.PathManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PathTest {

    @Test
    public void givenValidPath_whenAddCoordinate_thenAddCoordinateToPath(){
        Path path = new Path();
        path.addCoordinate(new Coordinate(1, 0, 0, 4));

        assertTrue("Coordinate is not added to Path", path.getLength() == 1);
    }

    @Test
    public void givenValidPath_whenAddPath_thenAddNewPathToOldPath(){
        Path path = new Path();
        path.addCoordinate(new Coordinate(1, 0, 0, 4));

        Path newPath = new Path();
        newPath.addCoordinate(new Coordinate(2, 0, 1, 3));

        path.addPath(newPath);

        assertTrue("New Path is not added to Path", path.getLength() == 2);
    }

    @Test
    public void givenFirstPathLongerThanSecondPath_whenPathIsCompared_thenReturnTrue(){
        Path firstPath = new Path();
        firstPath.addCoordinate(new Coordinate(1, 0, 0, 4));
        firstPath.addCoordinate(new Coordinate(2, 0, 1, 2));

        Path secondPath = new Path();
        secondPath.addCoordinate(new Coordinate(3, 0, 3, 2));

        assertTrue("FirstPath should be longer than SecondPath", firstPath.isLongerThan(secondPath));
    }

    @Test
    public void givenFirstPathSameLengthWithSecondPathAndFirstPathMoreSteepThanSecondPath_whenPathIsCompared_thenReturnTrue(){
        Path firstPath = new Path();
        firstPath.addCoordinate(new Coordinate(1, 0, 0, 4));
        firstPath.addCoordinate(new Coordinate(2, 0, 1, 2)); // First Path has drop 2

        Path secondPath = new Path();
        secondPath.addCoordinate(new Coordinate(3, 0, 3, 2));
        secondPath.addCoordinate(new Coordinate(3, 0, 3, 1)); // Second Path has drop 1

        assertTrue("FirstPath should be longer than SecondPath", firstPath.isLongerThan(secondPath));
    }

}
