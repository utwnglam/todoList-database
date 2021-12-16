package com.afs.todoList.controller;

import com.afs.todoList.entity.TodoItem;
import com.afs.todoList.service.TodoListService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin("https://localhost:3000/")
@RequestMapping("/todos")
public class TodoListController {
  private final TodoListService todoListService;

  public TodoListController(TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @GetMapping
  public List<TodoItem> getAllTodos() {
    return todoListService.findAll();
  }
}
