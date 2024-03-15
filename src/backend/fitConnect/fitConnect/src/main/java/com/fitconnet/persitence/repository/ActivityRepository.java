package com.fitconnet.persitence.repository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

@Repository
public interface ActivityRepository extends MyBaseRepository<Activity, Long> {

	Optional<List<Activity>> findByUser(User user);

	Optional<List<Activity>> findByParticipantsContains(User participant);

	Optional<List<Activity>> findByActivityType(String activityType);

	Optional<List<Activity>> findByDurationLessThan(Duration duration);

}
