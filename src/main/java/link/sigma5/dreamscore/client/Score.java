package link.sigma5.dreamscore.client;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;
import javax.validation.Valid;

/**
 * Score
 */
public class Score   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("position")
  private Integer position = null;

  @JsonProperty("score")
  private Long score = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("date")
  private Date date = null;

  @JsonProperty("subscription")
  private String subscription = null;

  @JsonProperty("app")
  private String app = null;

  @JsonProperty("section")
  private String section = null;

  @JsonProperty("selected")
  private Boolean selected = false;


  public Score id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Score position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
   **/
    public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Score score(Long score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
   **/
    public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Score name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Score date(Date date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
    @Valid
    public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Score subscriotin(String subscriotin) {
    this.subscription = subscriotin;
    return this;
  }

  /**
   * Get subscriotin
   * @return subscriotin
   **/
    public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score = (Score) o;
    return Objects.equals(this.id, score.id) &&
        Objects.equals(this.position, score.position) &&
        Objects.equals(this.score, score.score) &&
        Objects.equals(this.name, score.name) &&
        Objects.equals(this.date, score.date) &&
        Objects.equals(this.subscription, score.subscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, position, score, name, date, subscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Score {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    subscriotin: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    app: ").append(toIndentedString(app)).append("\n");
    sb.append("    section: ").append(toIndentedString(section)).append("\n");
    sb.append("    selected: ").append(toIndentedString(selected)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public Boolean getSelected() {
    return selected;
  }

  public void setSelected(Boolean selected) {
    this.selected = selected;
  }

}
