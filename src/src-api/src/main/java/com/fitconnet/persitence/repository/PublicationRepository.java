package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fitconnet.persitence.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Publication p WHERE p = :publication")
	boolean exists(Publication publication);

}
