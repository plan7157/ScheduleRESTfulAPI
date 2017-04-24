package schedule.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * This class is a generic HTTP response body.
 *
 * @param <T> The payload, if there is one.
 */
@Getter
@ToString
@Accessors(chain = true)
public class ResponseModel<T> implements Serializable {

    private Status status;

    /**
     * Generic type for payload
     */
    @JsonProperty(value = "data")
    private T dataObj;

    /**
     * Constructor with no payload
     *
     * @param subject     status subject
     * @param description status description (localized)
     */
    public ResponseModel(String subject, String description) {
        this.status = new Status(subject, description);
        this.dataObj = null;
    }

    /**
     * Constructor with payload
     *
     * @param subject     status subject
     * @param description status description (localized)
     * @param dataObj     payload object
     */
    public ResponseModel(String subject, String description, T dataObj) {
        this.status = new Status(subject, description);
        this.dataObj = dataObj;
    }

    /**
     * Constructor with payload no description
     *
     * @param subject status subject
     * @param dataObj payload object
     */
    public ResponseModel(String subject, T dataObj) {
        this.status = new Status(subject, "-");
        this.dataObj = dataObj;
    }
}