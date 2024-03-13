package com.fitconnet.persitence.repository;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface MyBaseRepository<T, I extends Serializable> extends Repository<T, I> {
	T findById(I id);

	T save(T entity);

	Iterable<T> findAll();

	Long count();

	T delete(I id);

	boolean exists(I id);
}
