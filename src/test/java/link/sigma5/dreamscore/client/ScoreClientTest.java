package link.sigma5.dreamscore.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Random;
@Disabled
class ScoreClientTest {
    public static final String TEST_DEVICE_ID = "testDeviceID";
    String urlLocal = "http://localhost:8080/score/v3";
    String urlRemote = "http://dreamscore.sigma5.link:8080/score/v3";
//    String urlRemote = "http://ec2-18-159-129-136.eu-central-1.compute.amazonaws.com:8080/score/v3";
    String url;

    @BeforeEach
    void before() {
        if ("remote".equals(System.getenv("profile"))) {
            url = urlRemote;
        } else {
            url = urlLocal;
        }
        System.out.println("Test configured for link: " + url);
    }


    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void pullScore(boolean localScore) throws IOException, InterruptedException {
        ScoreClient scoreClient = new ScoreClient("test", TEST_DEVICE_ID, url);
        List<Score> scores = scoreClient.pullScore("test", localScore, 0, 10);
        scores.forEach(System.out::println);
        if (localScore) {
            scores.forEach(score -> assertEquals(TEST_DEVICE_ID, score.getDeviceId()));
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void pushScore(boolean localScore) throws IOException, InterruptedException {
        Random random = new Random();
        ScoreClient scoreClient = new ScoreClient("test", TEST_DEVICE_ID, url);
        List<Score> scores = scoreClient.pushScore("test", localScore, "Jon Doe", random.nextInt(1000));
        scores.forEach(System.out::println);
        if (localScore) {
            scores.forEach(score -> assertEquals(TEST_DEVICE_ID, score.getDeviceId()));
        }
    }
}