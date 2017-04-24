package schedule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.model.response.ResponseModel;
import schedule.model.Todo;
import schedule.model.exception.BusinessException;
import schedule.repository.TodoRepository;
import schedule.service.TodoService;

import java.util.List;

import static schedule.component.Constants.ERROR_GENERIC_MESSAGE;

/**
 * ScheduleController Class
 * this class is control the endpoint mapping for front-end.
 */
@RestController
@Slf4j
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    /**
     * getTodoList
     * this method use for get all task from DB
     *
     * @return Task List
     * @throws BusinessException Exception
     */
    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<List<Todo>>> getTodoList() throws BusinessException {
        try {
            ResponseModel<List<Todo>> dataOutput = todoService.getAllTodo();
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }
    }

    /**
     * getTodo
     * this method use for get specific task from DB By task id
     *
     * @param id the task id want to get from DB
     * @return the same task with id if success
     * @throws BusinessException Exception
     */
    @GetMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<Todo>> getTodo(@PathVariable int id) throws BusinessException {
        try {
            ResponseModel<Todo> dataOutput = todoService.getTodoById(id);
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }
    }

    /**
     * addTodo
     * this method use for add a new task to DB
     *
     * @param todoInput the task want to add to DB
     * @return the same task with todoInput if success
     * @throws BusinessException Exception
     */
    @PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<Todo>> addTodo(@RequestBody Todo todoInput) throws BusinessException {
        InputLog(todoInput);
        try {
            ResponseModel<Todo> dataOutput = todoService.addTodo(todoInput);
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }
    }

    /**
     * updateTodo
     * this method use for update detail a task in DB
     *
     * @param todoInput the task want to update in DB
     * @return the same task with todoInput if success
     * @throws BusinessException Exception
     */
    @PutMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<Todo>> updateTodo(@RequestBody Todo todoInput) throws BusinessException {
        InputLog(todoInput);
        try {
            ResponseModel<Todo> dataOutput = todoService.updateTodo(todoInput);
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }
    }

    /**
     * updateTodoStatus
     * this method use for update status a task in DB
     *
     * @param todoInput the task want to update status in DB
     * @return the same task with todoInput if success
     * @throws BusinessException Exception
     */
    @PutMapping(value = "/todo/status", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<Todo>> updateTodoStatus(@RequestBody Todo todoInput) throws BusinessException {
        InputLog(todoInput);
        try {
            ResponseModel<Todo> dataOutput = todoService.updateStatus(todoInput);
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }

    }

    /**
     * deleteTodoStatus
     * this method use for delete a task in DB
     *
     * @param id the task id want to delete from DB
     * @return the same task with id if success
     * @throws BusinessException Exception
     */
    @DeleteMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseModel<Todo>> deleteTodoStatus(@PathVariable int id) throws BusinessException {
        try {
            ResponseModel<Todo> dataOutput = todoService.deleteTodo(id);
            return new ResponseEntity<>(dataOutput, HttpStatus.OK);
        } catch (BusinessException e) {
            throw new BusinessException(e.getSubject(), e.getDescription(), e);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, e);
        }

    }

    private void InputLog(Todo todo) {
        log.debug("Todo Input : " + todo.toString());
    }

}

