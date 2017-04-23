package schedule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import schedule.model.Response.ResponseModel;
import schedule.model.exception.BusinessException;

import static schedule.component.Constants.ERROR_GENERIC_MESSAGE;

/**
 * ExceptionHandlerController Class
 * this class is Error Controller Class.
 * Every error message must be manage by this class.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    /**
     * method is for catch any exception is out of handle from handleBusinessError.
     *
     * @param ex Error Exception
     * @return ResponseEntity<ResponseModel>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> handleError(Exception ex) {
        log.error(ex.getMessage(), ex);
        ResponseModel responseModel = new ResponseModel(ERROR_GENERIC_MESSAGE, "-");
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    /**
     * method is for catch any exception in this project.
     *
     * @param ex BusinessException Exception
     * @return ResponseEntity<ResponseModel>
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseModel> handleBusinessError(BusinessException ex) {
        log.error("Error subject : " + ex.getSubject());
        log.error(ex.getMessage(), ex);
        String description = (ex.getDescription() == null ? ex.getCause().toString() : ex.getDescription());
        ResponseModel responseModel = new ResponseModel(ex.getSubject(), description);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
