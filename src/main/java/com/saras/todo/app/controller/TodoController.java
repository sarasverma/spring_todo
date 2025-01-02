package com.saras.todo.app.controller;

import com.saras.todo.app.model.Todo;
import com.saras.todo.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable int todoId){
        Todo todo = service.getTodoById(todoId);

        if(todo != null)
            return new ResponseEntity<>(todo, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo){
        try {
            todo.setDate(LocalDateTime.now().toString());
            Todo savedTodo = service.createTodo(todo);
            return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todo/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody Todo todo){
        try {
            Todo updateTodo = service.updateTodo(todoId, todo);
            if(updateTodo != null)
                return new ResponseEntity<>(updateTodo, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable int todoId){
        // another way of pre-check see update
        if(service.getTodoById(todoId) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.deleteTodo(todoId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/todo/search")
    public ResponseEntity<List<Todo>> searchTodo(@RequestParam String keyword){
        return new ResponseEntity<>(service.searchTodo(keyword), HttpStatus.OK);
    }
}
