package com.example.demoapp1.controller;

import com.example.demoapp1.model.Todo;
import com.example.demoapp1.service.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TodoController {
	
	@Autowired
	TodoRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@CrossOrigin
	@GetMapping("/list-todos")
	public String showTodos(@RequestParam String userName) {
		List<Todo> todoList = repository.findByUser(userName);
		//model.put("todos", service.retrieveTodos(name));
		return todoList.toString();
	}

	private String getLoggedInUserName() {

		return "in28minutes";
	}

	@PostMapping(path = "/add-todo",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String showAddTodoPage(@RequestBody Todo todo) {
		todo.setTargetDate(new Date());
		Todo savedTodo = repository.save(todo);
		return savedTodo.toString();
	}

	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {

		//if(id==1)
			//throw new RuntimeException("Something went wrong");
		repository.deleteById(id);
		//service.deleteTodo(id);
		return "Delete successful";
	}


}
