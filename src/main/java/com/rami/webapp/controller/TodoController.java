package com.rami.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rami.webapp.entity.Todo;
import com.rami.webapp.service.TodoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping({"", "index", "/", "/todos"})
    public String obtenerTodos(Model model) {
        model.addAttribute("todos", todoService.listarTodos());
        return "index";
    }
    @GetMapping("/todos/crear")
    public String formCrearTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "todo-form";
    }
    
    @PostMapping("/todos")
    public String crearTodo(@ModelAttribute("todo") Todo todo) {
        todoService.crearTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/todos/editar/{id}")
    public String formEditar(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService.obtenerPorID(id);
        model.addAttribute("todo", todo);
        return "todo-form";
    }
    
    
    @PostMapping("/todos/actualizar/{id}")
    public String editarTodo(@PathVariable("id") Long id, @ModelAttribute("todo") Todo todo) {
        todoService.actualizarTodo(id, todo);    
        return "redirect:/";
    }
    @GetMapping("/todos/eliminar/{id}")
    public String eliminarTodo(@PathVariable("id") Long id) {
        todoService.eliminarTodo(id);
        return "redirect:/";
    }
    
    
}
