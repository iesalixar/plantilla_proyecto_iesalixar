package com.fitconnet.persitence.repository;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

@Repository
public interface ActivityRepository extends MyBaseRepository<Activity, Long> {

	List<Activity> findByUser(User user);

	List<Activity> findByParticipantsContains(User participant);

	List<Activity> findByActivityType(String activityType);

	List<Activity> findByDurationLessThan(Duration duration);

	List<Activity> findByDistanceGreaterThan(double distance);
}
