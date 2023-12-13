package link.sigma5.dreamscore.client;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ScoreClientTest {

    @Test
    void pullScore() throws IOException, InterruptedException {
        ScoreClient scoreClient = new ScoreClient("test", "test");
        scoreClient.pullScore(0, 10).forEach(System.out::println);
    }

    @Test
    void pushScore() throws IOException, InterruptedException {
        Random random = new Random();
        ScoreClient scoreClient = new ScoreClient("test", "test");
        scoreClient.pushScore("Jon Doe", random.nextInt(1000)).forEach(System.out::println);
    }
}