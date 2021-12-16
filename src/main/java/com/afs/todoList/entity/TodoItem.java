package com.afs.todoList.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "todoList")
public class TodoItem {
  @MongoId(FieldType.OBJECT_ID)
  private String id;
  private String content;
  private Boolean done;

  public TodoItem(String id, String content, Boolean done) {
    this.id = id;
    this.content = content;
    this.done = done;
  }

  public TodoItem() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }
}
