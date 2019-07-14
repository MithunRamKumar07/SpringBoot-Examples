package com.genericRestService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.genericRestService.services.GenericService;

public class GenericController<T> {

	@Autowired
	private GenericService<T> service;

	@GetMapping
	public List<T> getAll() {
		return service.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public T create(@RequestBody T entity) {
		return service.create(entity);
	}

	@PutMapping(value = "{id}")
	public T update(@PathVariable(value = "id") long id, @RequestBody T entity) {
		return service.update(id, entity);
	}

	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable(value = "id") long id) {
		service.delete(id);
	}

	@GetMapping(value = "{id}")
	public T get(@PathVariable(value = "id") long id) {
		return service.get(id);
	}
}
