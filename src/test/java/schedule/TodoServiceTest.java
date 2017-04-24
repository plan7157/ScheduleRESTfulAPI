package schedule;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import schedule.model.response.ResponseModel;
import schedule.model.response.Status;
import schedule.model.Todo;
import schedule.model.exception.BusinessException;
import schedule.repository.TodoRepository;
import schedule.service.TodoService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static schedule.component.Constants.STATUS_SUCCESS;

/**
 * Created by natapon on 23-Apr-17.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Before
    public void before_each_test() {
        Todo todo1 = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Todo todo2 = new Todo()
                .setId(2)
                .setSubject("Teat2")
                .setDetail("TeatDetail2")
                .setStatus(false);

        try {
            when(todoRepository.getTodoById(1)) .thenReturn(todo1);
            when(todoRepository.getTodoById(2)) .thenReturn(todo2);
            when(todoRepository.getAllTodo()) .thenReturn(Arrays.asList(todo1, todo2));
        } catch (BusinessException e) {
            log.info(e.getMessage(),e);
        }


    }
    @Test
    public void updateStatusPass() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<Todo> todoResponseModel = todoService.updateStatus(todo);
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().getId(),equalTo(todo.getId()));
        assertThat(todoResponseModel.getDataObj().getSubject(),equalTo(todo.getSubject()));
        assertThat(todoResponseModel.getDataObj().getDetail(),equalTo(todo.getDetail()));
        assertThat(todoResponseModel.getDataObj().getStatus(),equalTo(todo.getStatus()));
    }

    @Test
    public void updateStatusFail() throws Exception {
        Todo todo = new Todo()
                .setId(0)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status("Missing ID",null);
        try {
            ResponseModel<Todo> todoResponseModel = todoService.updateStatus(todo);
        }catch (BusinessException e){
            assertThat(e.getSubject(),equalTo(status.getSubject()));
            assertThat(e.getDescription(),equalTo(status.getDescription()));
        }

    }

    @Test
    public void updateTodoPass() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<Todo> todoResponseModel = todoService.updateTodo(todo);
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().getId(),equalTo(todo.getId()));
        assertThat(todoResponseModel.getDataObj().getSubject(),equalTo(todo.getSubject()));
        assertThat(todoResponseModel.getDataObj().getDetail(),equalTo(todo.getDetail()));
        assertThat(todoResponseModel.getDataObj().getStatus(),equalTo(todo.getStatus()));
    }

    @Test
    public void updateTodoFail() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject(null)
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status("Missing Subject",null);
        try {
            ResponseModel<Todo> todoResponseModel = todoService.updateTodo(todo);
        }catch (BusinessException e){
            assertThat(e.getSubject(),equalTo(status.getSubject()));
            assertThat(e.getDescription(),equalTo(status.getDescription()));
        }
    }

    @Test
    public void addTodoPass() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<Todo> todoResponseModel = todoService.addTodo(todo);
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().getId(),equalTo(todo.getId()));
        assertThat(todoResponseModel.getDataObj().getSubject(),equalTo(todo.getSubject()));
        assertThat(todoResponseModel.getDataObj().getDetail(),equalTo(todo.getDetail()));
        assertThat(todoResponseModel.getDataObj().getStatus(),equalTo(todo.getStatus()));
    }

    @Test
    public void getTodoByIdPass() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<Todo> todoResponseModel = todoService.getTodoById(1);
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().getId(),equalTo(todo.getId()));
        assertThat(todoResponseModel.getDataObj().getSubject(),equalTo(todo.getSubject()));
        assertThat(todoResponseModel.getDataObj().getDetail(),equalTo(todo.getDetail()));
        assertThat(todoResponseModel.getDataObj().getStatus(),equalTo(todo.getStatus()));
    }

    @Test
    public void getAllTodoPass() throws Exception {
        Todo todo1 = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Todo todo2 = new Todo()
                .setId(2)
                .setSubject("Teat2")
                .setDetail("TeatDetail2")
                .setStatus(false);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<List<Todo>> todoResponseModel = todoService.getAllTodo();
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().get(0).getId(),equalTo(todo1.getId()));
        assertThat(todoResponseModel.getDataObj().get(0).getSubject(),equalTo(todo1.getSubject()));
        assertThat(todoResponseModel.getDataObj().get(0).getDetail(),equalTo(todo1.getDetail()));
        assertThat(todoResponseModel.getDataObj().get(0).getStatus(),equalTo(todo1.getStatus()));
        assertThat(todoResponseModel.getDataObj().get(1).getId(),equalTo(todo2.getId()));
        assertThat(todoResponseModel.getDataObj().get(1).getSubject(),equalTo(todo2.getSubject()));
        assertThat(todoResponseModel.getDataObj().get(1).getDetail(),equalTo(todo2.getDetail()));
        assertThat(todoResponseModel.getDataObj().get(1).getStatus(),equalTo(todo2.getStatus()));
    }

    @Test
    public void deleteTodoPsss() throws Exception {
        Todo todo = new Todo()
                .setId(1)
                .setSubject("Teat1")
                .setDetail("TeatDetail1")
                .setStatus(true);
        Status status = new Status(STATUS_SUCCESS,"-");
        ResponseModel<Todo> todoResponseModel = todoService.deleteTodo(1);
        assertThat(todoResponseModel.getStatus().getSubject(),equalTo(status.getSubject()));
        assertThat(todoResponseModel.getStatus().getDescription(),equalTo(status.getDescription()));
        assertThat(todoResponseModel.getDataObj().getId(),equalTo(todo.getId()));
        assertThat(todoResponseModel.getDataObj().getSubject(),equalTo(todo.getSubject()));
        assertThat(todoResponseModel.getDataObj().getDetail(),equalTo(todo.getDetail()));
        assertThat(todoResponseModel.getDataObj().getStatus(),equalTo(todo.getStatus()));
    }

}