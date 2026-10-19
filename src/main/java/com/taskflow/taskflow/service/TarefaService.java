package com.taskflow.taskflow.service;

import com.taskflow.taskflow.domain.Tarefa;
import com.taskflow.taskflow.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    private Tarefa buscarTarefaPorId(Long id){
        return tarefaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);

    }
    public Tarefa listarTarefa(Long id) {
        return buscarTarefaPorId(id);
    }


    public List<Tarefa> listarTarefas(){
        return tarefaRepository.findAll();
    }

    public Tarefa atualizarTarefa(Long id,Tarefa tarefa){
        Tarefa tarefaEncontrada = buscarTarefaPorId(id);
        tarefaEncontrada.setTitulo(tarefa.getTitulo());
        tarefaEncontrada.setConcluida(tarefa.getConcluida());
        return tarefaRepository.save(tarefaEncontrada);
    }
    public void deletarTarefa(Long id){

        tarefaRepository.delete(buscarTarefaPorId(id));
    }

}
