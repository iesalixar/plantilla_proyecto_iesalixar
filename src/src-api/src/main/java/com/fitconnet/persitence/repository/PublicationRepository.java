package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitconnet.persitence.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
