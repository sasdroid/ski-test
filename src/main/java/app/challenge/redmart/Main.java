package app.challenge.redmart;

import app.challenge.redmart.model.Coordinate;
import app.challenge.redmart.model.Path;
import app.challenge.redmart.model.SkiField;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (String... args) -> {

            URL url = ctx.getClassLoader().getResource("map.txt");
            if(url == null) return;

            try(Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {

                SkiField field = new SkiField(lines);
                Path longestPath = getLongestPath(field);
                if(longestPath != null) System.out.println("Longest Path on The Map has [length:" + longestPath.getLength() + "][drop:" + longestPath.getDrop() + "]");
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }

    private static Path getLongestPath(SkiField field){

        Collection<Coordinate> coordinates = field.getCoordinates();
        return coordinates.stream()
                .map(field::getLongestFrom)
                .filter(path -> path.getDrop() > 1410 && path.getLength() > 14)
                .max((pathA, pathB) -> pathA.isLongerThan(pathB)? 1 : -1).orElse(null)
                ;
    }

}
