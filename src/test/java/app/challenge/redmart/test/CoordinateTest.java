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
public class CoordinateTest {

    @Test
    public void givenValidIdAxisOrdinateElevation_whenConstructed_thenValidCoordinateObject(){
        Coordinate coordinate = new Coordinate(1, 0, 1, 9);
        assertTrue("Coordinate is not correctly constructed", 1 == coordinate.id && 0 == coordinate.axis && 1 == coordinate.ordinate && 9 == coordinate.elevation );
    }
}
