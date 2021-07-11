package sda.practical;

import com.google.gson.annotations.SerializedName;
public class IssApiResponse {
    private Long timestamp;
    private String message;

    @SerializedName("iss_position")
    private GeoLocation issPosition;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GeoLocation getIss() {
        return issPosition;
    }

    public void setIss(GeoLocation iss) {
        this.issPosition = issPosition;
    }
}
