package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Item;
import id.ac.ui.cs.advprog.eshop.repository.GenericRepository;

@Service
public abstract class GenericService<T extends Item> {
	
	@Autowired
	protected GenericRepository<T> repository;
	
    public T create(T item) {
    	repository.create(item);
		return item;
    }
    
    public List<T> findAll(){
    	Iterator<T> carIterator = repository.findAll();
    	List<T> allItem = new ArrayList<>();
		carIterator.forEachRemaining(allItem::add);
		return allItem;
    }
    
    public T findById(String id) {
    	T item = repository.findById(id);
		return item;
    }
    
    public T update(String id, T entity) {
    	return repository.update(id,entity);
    }
    
    public void deleteById(String id) {
    	repository.delete(id);
    }
}
