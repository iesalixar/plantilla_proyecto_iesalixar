package com.fitconnet.service.implementations.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fitconnet.persitence.model.Publication;
import com.fitconnet.persitence.repository.PublicationRepository;
import com.fitconnet.service.interfaces.entity.PublicationServiceI;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationServiceI {

	private final PublicationRepository publicationRepo;

	@Override
	public Publication getById(Long id) {
		return publicationRepo.findById(id).get();
	}

	@Override
	public List<Publication> getAll() {
		return publicationRepo.findAll();
	}

	@Override
	public Boolean existById(Long id) {
		return publicationRepo.existsById(id);
	}

	@Override
	public void create(Publication publication) {
		if (!publicationRepo.exists(publication)) {
			publicationRepo.save(publication);
		}
	}

	@Override
	public void update(Long id, Publication newPublication) {
		if (publicationRepo.existsById(id)) {
			Publication aux = publicationRepo.findById(id).get();
			publicationRepo.delete(aux);
			publicationRepo.save(newPublication);
		}
	}

	@Override
	public void delete(Long id) {

		if (publicationRepo.existsById(id)) {
			publicationRepo.deleteById(id);

		}

	}

}
