package com.project.springmongo.controller;

import com.project.springmongo.model.TodoDTO;
import com.project.springmongo.repository.TodoRepository;
import com.project.springmongo.repository.UserRepository;
import com.project.springmongo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final TodoService todoService;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TestController(TodoService todoService, UserRepository userRepository, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String simpleUserAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/allTodo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<TodoDTO> userAccess() {
        return new ArrayList<>(todoService.findAll());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public Optional<TodoDTO> findById(@PathVariable("id") String id){
        return todoService.findById(id);
    }
    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        todoService.save(todo);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody TodoDTO todo){
        todoService.update(id, todo);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        todoService.delete(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    //МЕТОДЫ ДЛЯ ПРОВЕРКИ АГРЕГАТНЫХ ФУНКЦИЙ
    @GetMapping("/startWithM")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String usernameStartsWithS(){
        return "Имена начинающиеся с буквы M" + userRepository.findUsersNameStartsWithM().toString();
    }

    @GetMapping("/unique")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String uniqueUserNames(){
        return "Уникальное имя" + userRepository.findAllUniqueUserNames().toString();
    }

    @GetMapping("/usersWithNameLana")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String usersWithNameUser(){
        return "Все Tom's в базе данных" + userRepository.findUsersWithNameUser().toString();
    }

    @GetMapping("/startWithY")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String usernameStartsWithY(){
        return "Имена начинающиеся с буквы Y" + todoRepository.findUsersNameStartsWithY().toString();
    }

    @GetMapping("/todosWithNameGibson")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String usersWithNameGibson(){
        return "Все Gibson в базе данных" + todoRepository.findUsersWithNameGibson().toString();
    }


}