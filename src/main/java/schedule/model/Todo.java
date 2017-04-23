package schedule.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class is task model
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "todo")
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;
    @Column(name = "subject")
    private String subject;
    @Column(name = "detail")
    private String detail;
    @Column(name = "status")
    private Boolean status = false;

    /**
     * This method use to pass the task get from hibernate to be normal obj
     *
     * @return normal task obj
     */
    public Todo passValue() {
        return new Todo()
                .setId(this.id)
                .setDetail(this.detail)
                .setSubject(this.subject)
                .setStatus(this.status);
    }
}
