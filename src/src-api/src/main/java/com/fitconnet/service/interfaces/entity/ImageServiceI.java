package com.fitconnet.service.interfaces.entity;

import java.util.List;
import java.util.Optional;

import com.fitconnet.persitence.model.Image;

public interface ImageServiceI {

	List<Image> getAll();

	Optional<Image> getById(Long id);

	void delete(Long id);

	void create(Image img);

	void update(Long id, Image img);

}
