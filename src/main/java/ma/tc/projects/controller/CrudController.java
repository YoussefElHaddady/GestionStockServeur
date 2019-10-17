package ma.tc.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ma.tc.projects.message.response.ResponseMessage;
import ma.tc.projects.service.ICrudService;

public class CrudController<T, IDT> {
	
	@Autowired
	private ICrudService<T, IDT> service;
	
	@GetMapping
	public List<T> getAll(){
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody T entity) {
		service.add(entity);
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.INSERT_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody T entity) {
		service.update(entity);
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.UPDATE_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable IDT id) {
		service.delete(id);
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_SUCCESS), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/all")
	public ResponseEntity<List<T>> addAll(@RequestBody List<T> list){
		service.saveAll(list);
		return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll(@RequestBody List<T> list){
		service.deleteAll(list);
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.DELETE_SUCCESS), HttpStatus.ACCEPTED);
	}
}
