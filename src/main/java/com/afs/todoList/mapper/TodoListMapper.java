package com.afs.todoList.mapper;

import com.afs.todoList.dto.TodoItemRequest;
import com.afs.todoList.entity.TodoItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoListMapper {
  public TodoItem toEntity(TodoItemRequest todoItemRequest) {
    TodoItem todoItem = new TodoItem();

    BeanUtils.copyProperties(todoItemRequest, todoItem);
    return todoItem;
  }
}
