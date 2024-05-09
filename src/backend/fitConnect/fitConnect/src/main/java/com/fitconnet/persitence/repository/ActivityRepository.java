package com.fitconnet.persitence.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT n FROM Activity n WHERE n.date = ?1")
	Boolean existByDate(Date date);

}
