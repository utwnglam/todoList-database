package com.afs.todoList.service;

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
}
