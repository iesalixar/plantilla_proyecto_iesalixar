package com.fitconnet.service.implementations.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fitconnet.persitence.model.Image;
import com.fitconnet.persitence.repository.ImageRepository;
import com.fitconnet.service.interfaces.entity.ImageServiceI;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageServiceI {

	private final ImageRepository imageRepository;

	@Override
	public List<Image> getAll() {
		return imageRepository.findAll();
	}

	@Override
	public Image getById(Long id) {
		return imageRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);
	}

	@Override
	public void create(Image img) {
		imageRepository.save(img);
	}

	@Override
	public void update(Long id, Image img) {
		Optional<Image> aux = imageRepository.findById(id);
		if (aux.isPresent()) {
			imageRepository.deleteById(id);
			imageRepository.save(img);
		}

	}

	@Override
	public Boolean existById(Long id) {
		return imageRepository.existsById(id);
	}

}
