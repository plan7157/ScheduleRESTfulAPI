package schedule.model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Status Class
 * this class is Status model
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class Status implements Serializable {
    private String subject;
    private String description;
}
