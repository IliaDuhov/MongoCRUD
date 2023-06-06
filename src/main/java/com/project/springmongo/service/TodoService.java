package com.project.springmongo.service;

import com.project.springmongo.model.TodoDTO;
import com.project.springmongo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> findAll() {
        return todoRepository.findAll();
    }

    public void save(TodoDTO todo) {
        todo.setCreateAt(new Date(System.currentTimeMillis()));
        todoRepository.save(todo);
    }

    public Optional<TodoDTO> findById(String id) {
        return todoRepository.findById(id);
    }

    public void update(String id, TodoDTO todo) {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            TodoDTO todoToSave = todoOptional.get();
            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
            todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());
            todoToSave.setUpdateAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToSave);
        }
    }

    public void delete(String id){
        todoRepository.deleteById(id);
    }
}
