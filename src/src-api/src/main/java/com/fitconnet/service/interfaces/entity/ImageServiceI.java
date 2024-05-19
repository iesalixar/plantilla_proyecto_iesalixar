package com.fitconnet.service.interfaces.entity;

import java.util.List;

import com.fitconnet.persitence.model.Image;

public interface ImageServiceI {

	List<Image> getAll();

	Image getById(Long id);

	void delete(Long id);

	void create(Image img);

	void update(Long id, Image img);

	boolean existById(Long id);

}
