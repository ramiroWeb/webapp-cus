package com.rami.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rami.webapp.entity.Todo;
import com.rami.webapp.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public Todo crearTodo(Todo nuevoTodo){
        return todoRepository.save(nuevoTodo);
    }
    public List<Todo> listarTodos(){
        return todoRepository.findAll();
    }
    public void eliminarTodo(Long id){
        todoRepository.deleteById(id);
    }
    public Todo actualizarTodo(Long id, Todo todoActualizado){
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setTitle(todoActualizado.getTitle());
        todo.setCompleted(todoActualizado.isCompleted());
        return todoRepository.save(todo);
    }
    public Todo obtenerPorID(Long id){
        return todoRepository.findById(id).orElseThrow();
    }
}
