# dream-score-client

The library to communicate with DreamScore service.

Download the code and install into local maven repo.

`mvn install`

Add the dependency to your pom.xml file:

    <dependency>
        <groupId>link.sigma5</groupId>
        <artifactId>dream-score-client</artifactId>
        <version>1.0.0</version>
    </dependency>

Code to push your score:

    ScoreClient scoreClient = new ScoreClient("MyGame", "GameSection");
    List<Score> scores = scoreClient.pushScore("UserName", 100);

You can separate score for different game levels.
After pushing you will get score records around your achievement.
