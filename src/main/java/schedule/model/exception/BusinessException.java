package schedule.model.exception;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * BusinessException Class
 * this class is BusinessException model
 */
@Getter
@Accessors(chain = true)
public class BusinessException extends Exception {
    private final String subject;
    private final String description;

    /**
     * method is for create BusinessException object with Exception data
     *
     * @param subject
     * @param throwable
     */
    public BusinessException(String subject, Throwable throwable) {
        super(throwable);
        this.subject = subject;
        this.description = null;
    }

    /**
     * method is for create BusinessException object with Exception data
     *
     * @param subject
     * @param description
     * @param throwable
     */
    public BusinessException(String subject, String description, Throwable throwable) {
        super(throwable);
        this.subject = subject;
        this.description = description;
    }

    /**
     * method is for create BusinessException object without Exception data
     *
     * @param subject
     */
    public BusinessException(String subject) {
        this.subject = subject;
        this.description = null;
    }
}
