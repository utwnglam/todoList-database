package com.afs.todoList.service;

import com.afs.todoList.advice.NoTodoFoundException;
import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
  private final TodoListRepository todoListRepository;

  public TodoListService(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  public List<TodoItem> findAll() {
    return todoListRepository.findAll();
  }

  public TodoItem create(TodoItem todoItem) {
    return todoListRepository.insert(todoItem);
  }

  public TodoItem edit(String id, TodoItem updatedTodoItem) {
    TodoItem todoItem = findById(id);
    if (updatedTodoItem.getDone() != null) {
      todoItem.setDone(updatedTodoItem.getDone());
    }
    if (updatedTodoItem.getContent() != null && !updatedTodoItem.getContent().equals("")) {
      todoItem.setContent(updatedTodoItem.getContent());
    }
    return todoListRepository.save(todoItem);
  }

  private TodoItem findById(String id) {
    return todoListRepository.findById(id)
      .orElseThrow(NoTodoFoundException::new);
  }
}
