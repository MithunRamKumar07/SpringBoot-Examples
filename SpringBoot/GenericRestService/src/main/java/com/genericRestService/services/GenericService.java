package com.genericRestService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.genericRestService.dao.GenericDao;

public class GenericService<T>{
	
	@Autowired
	public GenericDao<T> dao;
	
	public List<T> getAll() {
		return dao.findAll();
	}

	public T create(@RequestBody T entity) {
		return (T) dao.save(entity);
	}

	@Transactional
	public T update(@PathVariable(value = "id") long id, @RequestBody T entity) {
		return (T) dao.save(entity);
	}

	@Transactional
	public void delete(@PathVariable(value = "id") long id) {
		dao.deleteById(id);
	}
	
	@Transactional
	public T get(@PathVariable(value = "id") long id) {
		return (T) dao.getOne(id);
	}
	
}
