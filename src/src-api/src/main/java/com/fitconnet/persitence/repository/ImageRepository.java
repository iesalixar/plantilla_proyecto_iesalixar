package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitconnet.persitence.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
