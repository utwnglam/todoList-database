package com.afs.todoList.service;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TodoListServiceTest {
  @Mock
  TodoListRepository mockTodoListRepository;
  
  @InjectMocks
  TodoListService todoListService;
  
  @Test
  public void should_get_all_todos_when_GET_given_todos() {
    List<TodoItem> todoItemList = new ArrayList<>();
    todoItemList.add(new TodoItem(null, "Do something.", false));
    given(mockTodoListRepository.findAll())
      .willReturn(todoItemList);

    List<TodoItem> actual = todoListService.findAll();

    assertEquals(todoItemList.get(0).getContent(), actual.get(0).getContent());
    assertEquals(todoItemList.get(0).getDone(), actual.get(0).getDone());
  }

  @Test
  public void should_return_todo_when_GET_given_ID() {
    TodoItem todoItem = new TodoItem("1", "Do Something", false);
    given(mockTodoListRepository.findById(any()))
      .willReturn(java.util.Optional.of(todoItem));

    TodoItem actual = todoListService.findById(todoItem.getId());

    assertEquals(todoItem.getContent(), actual.getContent());
    assertEquals(todoItem.getDone(), actual.getDone());
  }

  @Test
  public void should_create_todo_when_POST_given_new_todo() {
    TodoItem todoItemInput = new TodoItem("1", "Do Something", null);
    TodoItem todoItemReturned = new TodoItem("1", "Do Something", false);

    given(mockTodoListRepository.insert(todoItemInput))
      .willReturn(todoItemReturned);

    TodoItem actual = todoListService.create(todoItemInput);

    assertEquals(todoItemReturned.getContent(), actual.getContent());
    assertEquals(todoItemReturned.getDone(), actual.getDone());
  }

  @Test
  public void should_edit_todo_when_PUT_given_updated_todo() {
    TodoItem todoItem = new TodoItem("1", "Do Something", true);
    TodoItem todoItemUpdated = new TodoItem("1", "Do Something and tired", false);

    given(mockTodoListRepository.findById(any()))
      .willReturn(java.util.Optional.of(todoItem));
    todoItem.setContent(todoItemUpdated.getContent());
    todoItem.setDone(todoItemUpdated.getDone());

    given(mockTodoListRepository.save(any(TodoItem.class)))
      .willReturn(todoItem);

    TodoItem actual = todoListService.edit(todoItem.getId(), todoItemUpdated);

    assertEquals(todoItem.getContent(), actual.getContent());
    assertEquals(todoItem.getDone(), actual.getDone());
  }
}
