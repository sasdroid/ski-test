package app.challenge.redmart.test;

import app.challenge.redmart.model.Coordinate;
import app.challenge.redmart.model.Path;
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
public class SkiFieldTest {

    private SkiField skiField;

    @Before
    public void init() throws Exception{
        List<String> inputData = new ArrayList<>();
        inputData.add("4 4"); //Field size 2 x 2
        inputData.add("4 8 7 3");
        inputData.add("2 5 9 3");
        inputData.add("6 3 2 5");
        inputData.add("4 4 1 6");

        this.skiField = new SkiField(inputData.stream());
    }

    @Test
    public void givenSkiField4x4_whenGetCoordinates_thenReturn16Coordinate(){

        Collection<Coordinate> coordinates = skiField.getCoordinates();
        assertTrue("Coordinates is incorrect: " + coordinates, coordinates.size() == 16);
    }

    @Test
    public void givenSkiField4x4_whenCoordinateIsAxis2Ordinate1_thenReturnCoordinateWithId6(){
        Coordinate coordinate = skiField.getCoordinate(2, 1);
        assertEquals("Incorrect Coordinate Obtained", 6, coordinate.id);
    }

    @Test
    public void givenSkiField4x4AndFromCoordinateId6_whenGetLongestPath_thenReturnPathWithLength5(){
        Coordinate fromCoordinate = skiField.getCoordinate(2, 1);
        Path path = skiField.getLongestFrom(fromCoordinate);
        assertEquals("Path length is wrong", 5, path.getLength());
    }
}
