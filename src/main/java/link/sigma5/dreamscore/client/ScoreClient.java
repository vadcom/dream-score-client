package link.sigma5.dreamscore.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Library for communication with DreamScore server
 */
public class ScoreClient {
    static final String TARGET_URL = "http://3.120.34.51:8080/score/v3";

    String url;
    String applicationId;
    String sectionId;
    ObjectMapper objectMapper = new ObjectMapper();

    public ScoreClient(String applicationId, String sectionId) {
        this(applicationId, sectionId, TARGET_URL);
    }

    public ScoreClient(String applicationId, String sectionId, String url) {
        this.url = url;
        this.applicationId = applicationId;
        this.sectionId = sectionId;
    }

    /**
     * Pushes a score to the server
     *
     * @param name
     * @param score
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Score> pushScore(String name, long score) throws IOException, InterruptedException {
        Score scoreRecord = new Score().name(name).score(score);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url + "/" + applicationId + "/" + sectionId))
                .POST(HttpRequest.BodyPublishers.ofString(scoreRecord.toString()))
                .build();
        // Send the request and retrieve the response
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return getScores(httpResponse);
    }

    /**
     * Parses the response from the server
     *
     * @param httpResponse
     * @return
     * @throws IOException
     */
    private ArrayList<Score> getScores(HttpResponse<String> httpResponse) throws IOException {
        var result = new ArrayList<Score>();
        if (httpResponse.statusCode() == 200) {
            JsonNode root = objectMapper.readTree(httpResponse.body());
            root.iterator().forEachRemaining(jsonNode -> {
                try {
                    result.add(objectMapper.treeToValue(jsonNode, Score.class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            throw new RuntimeException("Error: " + httpResponse.statusCode());
        }
        return result;
    }

    /**
     * Pulls the scores from the server
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Score> pullScore(int page, int recordInPage) throws IOException, InterruptedException {

        String countToSkipName = URLEncoder.encode("positionToSkip", StandardCharsets.UTF_8);
        String countToSkip = URLEncoder.encode(String.valueOf(page * recordInPage), StandardCharsets.UTF_8);
        String countName = URLEncoder.encode("count", StandardCharsets.UTF_8);
        String count = URLEncoder.encode(String.valueOf(recordInPage), StandardCharsets.UTF_8);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(
                        URI.create(String.format(url + "/" + applicationId + "/" + sectionId + "?%s=%s&%s=%s",
                                countToSkipName, countToSkip, countName, count)))
                .GET()
                .build();
        // Send the request and retrieve the response
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return getScores(httpResponse);
    }

}
