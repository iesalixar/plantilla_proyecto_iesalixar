package com.fitconnet.persitence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	/**
	 * Checks if an activity exists for a given date.
	 *
	 * @param date The date to check.
	 * @return true if an activity exists for the given date, false otherwise.
	 */
	@Query("SELECT COUNT(n) > 0 FROM Activity n WHERE n.date = ?1")
	Boolean existByDate(Date date);

	/**
	 * Finds activities by their place.
	 *
	 * @param place The place of the activities to find.
	 * @return An optional containing a list of activities with the specified place.
	 */
	@Query("SELECT n FROM Activity n WHERE n.place = :place")
	Optional<List<Activity>> findByActivityPlace(@Param("place") String place);

	/**
	 * Finds activities by their type.
	 *
	 * @param activityType The type of the activities to find.
	 * @return An optional containing a list of activities with the specified type.
	 */
	@Query("SELECT n FROM Activity n WHERE n.type = :activityType")
	Optional<List<Activity>> findByActivityType(@Param("activityType") String activityType);

	/**
	 * Finds activities by the username of the creator.
	 *
	 * @param username The username of the creator.
	 * @return An optional containing a list of activities created by the specified
	 *         username.
	 */
	@Query("SELECT a FROM Activity a WHERE a.creator.username = :username")
	Optional<List<Activity>> findByCreatorUsername(@Param("username") String username);
}