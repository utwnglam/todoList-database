package com.afs.todoList.dto;

public class TodoItemRequest {
  private String content;
  private Boolean done;

  public TodoItemRequest() {
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
