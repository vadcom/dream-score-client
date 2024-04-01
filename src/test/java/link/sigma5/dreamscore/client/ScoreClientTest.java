package link.sigma5.dreamscore.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

class ScoreClientTest {

    String urlLocal = "http://localhost:8080/score/v3";
    String urlRemote = "http://dreamscore.sigma5.link:8080/score/v3";
    String url;

    @BeforeEach
    void before() {
        url = urlRemote;
        System.out.println("Test configured for link: " + url);
    }

    @Test
    void pullScore() throws IOException, InterruptedException {
        ScoreClient scoreClient = new ScoreClient("test", "test", url);
        scoreClient.pullScore(0, 10).forEach(System.out::println);
    }

    @Test
    void pushScore() throws IOException, InterruptedException {
        Random random = new Random();
        ScoreClient scoreClient = new ScoreClient("test", "test",url);
        scoreClient.pushScore("Jon Doe", random.nextInt(1000)).forEach(System.out::println);
    }
}