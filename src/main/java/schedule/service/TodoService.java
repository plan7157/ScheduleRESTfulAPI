package schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.model.Response.ResponseModel;
import schedule.model.Todo;
import schedule.model.exception.BusinessException;
import schedule.repository.TodoRepository;

import java.util.List;

import static schedule.component.Constants.STATUS_SUCCESS;

/**
 * TodoService Class
 * this class is services container and the business logic will do at this class.
 */
@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    /**
     * update a task status in db
     *
     * @param todoInput the task you want to update status in db
     * @return task
     * @throws BusinessException Exception
     */
    public ResponseModel<Todo> updateStatus(Todo todoInput) throws BusinessException {
        log.info("validateStatusTodoId");
        validateStatusTodoId(todoInput);
        log.info("getSubjectAndDetailById");
        getSubjectAndDetailById(todoInput);
        log.info("updateTodo to DB");
        todoRepository.updateTodo(todoInput);
        return new ResponseModel<>(STATUS_SUCCESS, todoInput.passValue());
    }

    private void validateStatusTodoId(Todo todo) throws BusinessException {
        if (todo.getId() == 0) {
            throw new BusinessException("Missing ID");
        }
    }

    private void getSubjectAndDetailById(Todo todo) throws BusinessException {
        Todo todoBuffer = todoRepository.getTodoById(todo.getId());
        todo.setDetail(todoBuffer.getDetail()).setSubject(todoBuffer.getSubject());
    }

    /**
     * update a task in db
     *
     * @param todoInput the task you want to update in db
     * @return task
     * @throws BusinessException Exception
     */
    public ResponseModel<Todo> updateTodo(Todo todoInput) throws BusinessException {
        log.info("validateTodoSubject");
        validateTodoSubject(todoInput);
        log.info("updateTodo to DB");
        todoRepository.updateTodo(todoInput);
        return new ResponseModel<>(STATUS_SUCCESS, todoInput.passValue());
    }

    /**
     * add a task to db
     *
     * @param todoInput the task you want to add to db
     * @return task
     * @throws BusinessException Exception
     */
    public ResponseModel<Todo> addTodo(Todo todoInput) throws BusinessException {
        log.info("validateTodoSubject");
        validateTodoSubject(todoInput);
        log.info("addTodo to DB");
        todoRepository.addTodo(todoInput);
        return new ResponseModel<>(STATUS_SUCCESS, todoInput.passValue());
    }

    private void validateTodoSubject(Todo todo) throws BusinessException {
        if (todo.getSubject() == null) {
            throw new BusinessException("Missing Subject");
        }
    }

    /**
     * a task you want to get from db by id
     *
     * @param id task id
     * @return task
     * @throws BusinessException Exception
     */
    public ResponseModel<Todo> getTodoById(int id) throws BusinessException {
        log.info("getTodoById");
        return new ResponseModel<>(STATUS_SUCCESS, todoRepository.getTodoById(id).passValue());
    }

    /**
     * Get All task from DB
     *
     * @return Task List
     * @throws BusinessException Exception
     */
    public ResponseModel<List<Todo>> getAllTodo() throws BusinessException {
        log.info("getAllTodo");
        return new ResponseModel<>(STATUS_SUCCESS, todoRepository.getAllTodo());
    }

    /**
     * delete a task you want to remove from db
     *
     * @param id the task you want to remove from db
     * @return the task is removed.
     * @throws BusinessException Exception
     */
    public ResponseModel<Todo> deleteTodo(int id) throws BusinessException {
        log.info("getTodoById");
        Todo todo = todoRepository.getTodoById(id);
        log.info("delete a task from DB");
        todoRepository.deleteTodo(todo);
        return new ResponseModel<>(STATUS_SUCCESS, todo.passValue());
    }
}
