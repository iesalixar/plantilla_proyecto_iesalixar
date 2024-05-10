package com.fitconnet.persitence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT n FROM Activity n WHERE n.date = ?1")
	Boolean existByDate(Date date);

	@Query("SELECT n FROM Activity n WHERE a.place = :place")
	Optional<List<Activity>> findByActivityPlace(@Param("place") String place);

	@Query("SELECT n FROM Activity n WHERE a.type = :activityType")
	Optional<List<Activity>> findByActivityType(@Param("activityType") String activityType);

}
