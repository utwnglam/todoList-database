package com.afs.todoList.repository;

import com.afs.todoList.entity.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends MongoRepository<TodoItem, String> {
}
