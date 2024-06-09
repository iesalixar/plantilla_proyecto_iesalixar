package com.fitconnet.persitence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;

/**
 * Repository interface for managing activities.
 */
@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT a FROM Activity a WHERE a.creator.id = :creatorId")
	List<Activity> findAllByCreatorId(@Param("creatorId") Long creatorId);
}