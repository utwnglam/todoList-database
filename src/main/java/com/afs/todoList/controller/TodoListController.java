package com.afs.todoList.controller;

import com.afs.todoList.dto.TodoItemRequest;
import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.mapper.TodoListMapper;
import com.afs.todoList.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000/")
@RequestMapping("/todos")
public class TodoListController {
  private final TodoListService todoListService;
  private final TodoListMapper todoListMapper;

  public TodoListController(TodoListService todoListService, TodoListMapper todoListMapper) {
    this.todoListService = todoListService;
    this.todoListMapper = todoListMapper;
  }

  @GetMapping
  public List<TodoItem> getAllTodos() {
    return todoListService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TodoItem createTodo(@RequestBody TodoItemRequest todoItemRequest) {
    return todoListService.create(todoListMapper.toEntity(todoItemRequest));
  }
  
}
