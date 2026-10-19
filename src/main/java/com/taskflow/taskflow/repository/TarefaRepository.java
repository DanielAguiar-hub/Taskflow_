package com.taskflow.taskflow.repository;

import com.taskflow.taskflow.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> id(Long id);
}
