package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Item;

@Repository
public abstract class GenericRepository<T extends Item> {
	protected final List<T> data = new ArrayList<>();
	
	public T create(T entity) {
        if (entity.getId() == null) {
            UUID uuid = UUID.randomUUID();
            entity.setId(uuid.toString());
        }
        data.add(entity);
        return entity;
    }
	
	public Iterator<T> findAll() {
        return data.iterator();
    }
	
	public T findById(String id) {
        for (T entity : data) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new NoSuchElementException("Item not found with ID: " + id);
    }
	
	public T update(String id, T updatedEntity) {
        for (int i = 0; i < data.size(); i++) {
            T entity = data.get(i);
            if (entity.getId().equals(id)) {
                data.set(i, updatedEntity);
                return updatedEntity;
            }
        }
        throw new NoSuchElementException("Item not found with ID: " + id);
    }
	
	public void delete(String id) {
        if(!data.removeIf(entity -> entity.getId().equals(id))) {
        	throw new NoSuchElementException("Item not found with ID: " + id);
        }
    }


}
