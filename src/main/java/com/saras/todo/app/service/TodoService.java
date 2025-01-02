package com.saras.todo.app.service;

import com.saras.todo.app.model.Todo;
import com.saras.todo.app.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepo repo;

    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    public Todo getTodoById(int todoId) {
        return repo.findById(todoId).orElse(null);
    }

    public Todo createTodo(Todo todo) {
        return repo.save(todo);
    }

    public Todo updateTodo(int todoId, Todo todo) {
        Optional<Todo> optionalTodo = repo.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            return repo.save(existingTodo);
        }
        return null;
    }

    public void deleteTodo(int todoId) {
        repo.deleteById(todoId);
    }

    public List<Todo> searchTodo(String keyword) {
        return repo.searchTodo(keyword);
    }
}
