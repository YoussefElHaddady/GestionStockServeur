package ma.tc.projects.service;

import java.util.List;

public interface ICrudService<T, IDT> {

	List<T> getAll();
	
	void add(T entity);
	
	void update(T entity);
	
	void delete(IDT id);
	
	void saveAll(Iterable<T> iterable);
	
	void deleteAll(Iterable<T> iterable);
}
