package com.saras.todo.app.repository;

import com.saras.todo.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {

    // JPQL that's why p instead of *
    @Query("SELECT t FROM Todo t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Todo> searchTodo(String keyword);
}
