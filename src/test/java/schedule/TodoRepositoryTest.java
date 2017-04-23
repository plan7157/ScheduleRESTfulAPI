package schedule;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import schedule.model.Response.ResponseModel;
import schedule.model.Response.Status;
import schedule.model.Todo;
import schedule.model.exception.BusinessException;
import schedule.repository.TodoRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static schedule.component.Constants.ERROR_GENERIC_MESSAGE;
import static schedule.component.Constants.STATUS_SUCCESS;

/**
 * Created by natapon on 24-Apr-17.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before_each_test() throws Exception {
        jdbcTemplate.execute("truncate table todo");
        jdbcTemplate.update("INSERT INTO todo (detail, status, subject) VALUES ('- Nintendo Switch', TRUE, 'BuyList')");
        jdbcTemplate.update("INSERT INTO todo (detail, status, subject) VALUES ('- Mariokart', FALSE, 'PlayGame')");
    }

    @Test
    public void getAllTodoPass() throws Exception {
        Todo todo1 = new Todo()
                .setId(1)
                .setSubject("BuyList")
                .setDetail("- Nintendo Switch")
                .setStatus(true);
        Todo todo2 = new Todo()
                .setId(2)
                .setSubject("PlayGame")
                .setDetail("- Mariokart")
                .setStatus(false);
        List<Todo> todoList = todoRepository.getAllTodo();
        assertThat(todoList.get(0).getSubject(),equalTo(todo1.getSubject()));
        assertThat(todoList.get(0).getDetail(),equalTo(todo1.getDetail()));
        assertThat(todoList.get(0).getStatus(),equalTo(todo1.getStatus()));
        assertThat(todoList.get(1).getSubject(),equalTo(todo2.getSubject()));
        assertThat(todoList.get(1).getDetail(),equalTo(todo2.getDetail()));
        assertThat(todoList.get(1).getStatus(),equalTo(todo2.getStatus()));
    }

    @Test
    public void getTodoByIdPass() throws Exception {
        List<Todo> todoList = todoRepository.getAllTodo();
        Todo todo  = todoRepository.getTodoById(todoList.get(0).getId());
        assertThat(todo.getId(),equalTo(todoList.get(0).getId()));
        assertThat(todo.getSubject(),equalTo(todoList.get(0).getSubject()));
        assertThat(todo.getDetail(),equalTo(todoList.get(0).getDetail()));
        assertThat(todo.getStatus(),equalTo(todoList.get(0).getStatus()));
    }

    @Test
    public void getTodoByIdFail() throws Exception {
        Status status = new Status(ERROR_GENERIC_MESSAGE,"Fail to get a task by id");
        try {
            todoRepository.getTodoById(10);
        }catch (BusinessException e){
            assertThat(e.getSubject(),equalTo(status.getSubject()));
            assertThat(e.getDescription(),equalTo(status.getDescription()));
        }
    }

    @Test
    public void addTodoPass() throws Exception {
        Todo todo = new Todo()
                .setId(0)
                .setSubject("Test3")
                .setDetail("TestDetail3")
                .setStatus(true);
        todoRepository.addTodo(todo);
        List<Todo> todoList = todoRepository.getAllTodo();
        assertThat(todo.getSubject(),equalTo(todoList.get(todoList.size()-1).getSubject()));
        assertThat(todo.getDetail(),equalTo(todoList.get(todoList.size()-1).getDetail()));
        assertThat(todo.getStatus(),equalTo(todoList.get(todoList.size()-1).getStatus()));
    }

    @Test
    public void addTodoFail() throws Exception {
        Todo todo = new Todo()
                .setId(1000)
                .setSubject("Test3")
                .setDetail("TestDetail3")
                .setStatus(true);

        Status status = new Status(ERROR_GENERIC_MESSAGE,"Fail to add new task");
        try {
            todoRepository.addTodo(todo);
        }catch (BusinessException e){
            assertThat(e.getSubject(),equalTo(status.getSubject()));
            assertThat(e.getDescription(),equalTo(status.getDescription()));
        }
    }

    @Test
    public void updateTodoPass() throws Exception {
        Todo todoAfter = new Todo()
                .setId(0)
                .setSubject("Test4")
                .setDetail("TestDetail4")
                .setStatus(true);
        Todo todoBefore;
        Todo todoActual;
        List<Todo> todoList = todoRepository.getAllTodo();
        todoBefore = todoList.get(0);
        todoAfter.setId(todoList.get(0).getId());
        todoRepository.updateTodo(todoAfter);
        todoActual = todoRepository.getTodoById(todoAfter.getId());
        assertThat(todoActual.getId(),equalTo(todoBefore.getId()));
        assertThat(todoActual.getSubject(),not(todoBefore.getSubject()));
        assertThat(todoActual.getDetail(),not(todoBefore.getDetail()));
        assertThat(todoActual.getId(),equalTo(todoAfter.getId()));
        assertThat(todoActual.getSubject(),equalTo(todoAfter.getSubject()));
        assertThat(todoActual.getDetail(),equalTo(todoAfter.getDetail()));
    }

    @Test
    public void deleteTodoPass() throws Exception {
        List<Todo> todoListBefore = todoRepository.getAllTodo();
        todoRepository.deleteTodo(todoListBefore.get(0));
        List<Todo> todoListAfter = todoRepository.getAllTodo();
        assertThat(todoListAfter.size(),equalTo(todoListBefore.size()-1));
    }

}