package schedule.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import schedule.model.Todo;
import schedule.model.exception.BusinessException;

import java.util.List;

import static schedule.component.Constants.ERROR_GENERIC_MESSAGE;

/**
 * TodoRepository class
 * this class is repositories container,
 * them use for communicate with DB
 */
@Repository
@Transactional
@Slf4j
public class TodoRepository {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get All task from DB
     *
     * @return Task List
     * @throws BusinessException Exception
     */
    public List<Todo> getAllTodo() throws BusinessException {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            List<Todo> todoList = session.createQuery("from Todo").list();
            todoList.forEach(todo -> log.debug(todo.toString()));
            return todoList;
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, "Fail to get task list", e);
        }
    }

    /**
     * a task you want to get from db by id
     *
     * @param id task id
     * @return a task
     * @throws BusinessException Exception
     */
    public Todo getTodoById(int id) throws BusinessException {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            Todo todo = session.load(Todo.class, id);
            log.debug(todo.toString());
            return todo;
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, "Fail to get a task by id", e);
        }
    }

    /**
     * add a task to db
     *
     * @param todoInput the task you want to add to db
     * @throws BusinessException Exception
     */
    public void addTodo(Todo todoInput) throws BusinessException {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            session.persist(todoInput);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, "Fail to add new task", e);
        }
    }

    /**
     * update a task in db
     *
     * @param todoInput the task you want to update in db
     * @throws BusinessException Exception
     */
    public void updateTodo(Todo todoInput) throws BusinessException {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            session.update(todoInput);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, "Fail to update new task", e);
        }
    }

    /**
     * delete a task you want to remove from db
     *
     * @param todoInput the task you want to remove from db
     * @throws BusinessException Exception
     */
    public void deleteTodo(Todo todoInput) throws BusinessException {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            session.delete(todoInput);
        } catch (Exception e) {
            throw new BusinessException(ERROR_GENERIC_MESSAGE, "Fail to delete a task", e);
        }
    }

}
