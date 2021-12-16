package com.afs.todoList.controller;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListControllerTest {
  public static final String TODOLIST_URL_BASE = "/todos";
  @Autowired
  MockMvc mockMvc;
  @Autowired
  TodoListRepository todoListRepository;

  @BeforeEach
  void cleanRepository() {
    todoListRepository.deleteAll();
  }

  @Test
  public void should_get_all_todos_when_GET_given_todos() throws Exception {
    TodoItem todoItem = new TodoItem(null, "Do Something", false);
    todoListRepository.insert(todoItem);

    mockMvc.perform(MockMvcRequestBuilders.get(TODOLIST_URL_BASE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].id").isString())
      .andExpect(jsonPath("$[0].content").value("Do Something"))
      .andExpect(jsonPath("$[0].done").value(false));
  }

  @Test
  public void should_create_todo_when_POST_given_new_todo() throws Exception {
      String todoItemJson = "{\n" +
        "        \"content\": \"Testing 1\"\n" +
        "    }";

      mockMvc.perform(MockMvcRequestBuilders.post(TODOLIST_URL_BASE)
        .contentType(MediaType.APPLICATION_JSON)
        .content(todoItemJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isString())
        .andExpect(jsonPath("$.content").value("Testing 1"))
        .andExpect(jsonPath("$.done").value(false));
  }

  @Test
  public void should_edit_todo_when_PUT_given_updated_todo() throws Exception {
    TodoItem todoItem = new TodoItem(null, "Do Something", false);
    todoListRepository.insert(todoItem);

    String updatedTodoItemJson = "{\n" +
      "        \"content\": \"Edited\",\n" +
      "        \"done\": true\n" +
      "    }";

    mockMvc.perform(MockMvcRequestBuilders.put(TODOLIST_URL_BASE + "/" + todoItem.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(updatedTodoItemJson))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content").value("Edited"))
      .andExpect(jsonPath("$.done").value(true));
  }
}
