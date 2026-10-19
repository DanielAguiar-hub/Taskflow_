package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.domain.Tarefa;
import com.taskflow.taskflow.dto.TarefaRequestDTO;
import com.taskflow.taskflow.dto.TarefaResponseDTO;
import com.taskflow.taskflow.service.TarefaService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    private TarefaResponseDTO converterParaDTO(Tarefa tarefa){
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO();
        tarefaResponseDTO.setId(tarefa.getId());
        tarefaResponseDTO.setTitulo(tarefa.getTitulo());
        tarefaResponseDTO.setConcluida(tarefa.getConcluida());
        return tarefaResponseDTO;
    }

    @Operation(
            summary = "Criar tarefa",
            description = "Cadastra uma nova tarefa após validar os dados enviados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    public TarefaResponseDTO criar(@Valid @RequestBody TarefaRequestDTO dados){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setConcluida(dados.getConcluida());
        return converterParaDTO(tarefaService.criarTarefa(tarefa));
    }
    @Operation(
            summary = "Listar tarefa",
            description = "Lista a tarefa após validar se realmente existe"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada e listada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada, verificar o id")
    })
    @GetMapping("/{id}")
    public TarefaResponseDTO listar(@PathVariable Long id){
        return converterParaDTO(tarefaService.listarTarefa(id));
    }
    @Operation(
            summary = "Listar todas tarefas",
            description = "Listar  todas as tarefas, mesmo que não tenha nenhuma"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas e listadas com sucesso")
    }

    )
    @GetMapping
    public List<TarefaResponseDTO> listarTarefas(){
         return tarefaService.listarTarefas().stream().map(this::converterParaDTO).toList();
    }
    @Operation(
            summary = "Atualiza a tarefa",
            description = "Atualiza a tarefa correspondente ao ID informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados errados"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public TarefaResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody TarefaRequestDTO dados){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setConcluida(dados.getConcluida());
        return converterParaDTO(tarefaService.atualizarTarefa(id, tarefa));
    }
    @Operation(summary = "Deleta a tarefa",
    description = "Exclui a tarefa correspondente ao ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados errados"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
    }


}
