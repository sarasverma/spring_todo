package com.saras.todo.app.repository;

import com.saras.todo.app.model.Doodle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoodleRepo extends JpaRepository<Doodle, String> {
}
