package com.taskflow.taskflow.service;
import com.taskflow.taskflow.domain.Tarefa;
import com.taskflow.taskflow.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

public class TarefaServiceTest {
    @Mock
    private TarefaRepository tarefaRepository;
    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void deveCriarTarefa(){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Test mokito");
        tarefa.setConcluida(false);

        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa resultado = tarefaService.criarTarefa(tarefa);
        assertEquals("Test mokito", resultado.getTitulo());
        assertFalse(resultado.getConcluida());
        verify(tarefaRepository).save(tarefa);
    }

    @Test
     void deveLancarErroQuandoTarefaNaoExiste(){



        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException excesao = assertThrows(
                ResponseStatusException.class,
                () -> tarefaService.listarTarefa(99L)
        );
        assertEquals(404, excesao.getStatusCode().value());

    }
    @Test
    void deveAtualizarTarefa(){
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        tarefa1.setTitulo("Estudar Java");
        tarefa1.setConcluida(false);
        tarefa2.setTitulo("Estudar Spring Boot");
        tarefa2.setConcluida(true);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa1));
        when(tarefaRepository.save(tarefa1)).thenReturn(tarefa1);
        Tarefa resultado = tarefaService.atualizarTarefa(1L,tarefa2);
        assertEquals("Estudar Spring Boot", resultado.getTitulo());
        assertTrue(resultado.getConcluida());
        verify(tarefaRepository).save(tarefa1);
    }
    @Test
    void deveExcluirTarefa(){
        Tarefa tarefa1 = new Tarefa();
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa1));
        tarefaService.deletarTarefa(1L);
        verify(tarefaRepository).delete(tarefa1);
    }
    @Test
    void deveRetornarLista(){
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        tarefa1.setTitulo("teste1");
        tarefa2.setTitulo("teste2");
        when(tarefaRepository.findAll()).thenReturn(List.of(tarefa1,tarefa2));
        List<Tarefa> resultado = tarefaService.listarTarefas();
        verify(tarefaRepository).findAll();
        assertEquals(2, resultado.size());
        assertEquals("teste1", resultado.get(0).getTitulo());
        assertEquals("teste2", resultado.get(1).getTitulo());


    }

}
