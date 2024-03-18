package com.fitconnet.persitence.repository;

import java.time.Duration;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	public abstract List<Activity> findByUser(User user);

	public abstract List<Activity> findByParticipantsContains(User participant);

	public abstract List<Activity> findByActivityType(String activityType);

	public abstract List<Activity> findByDurationLessThan(Duration duration);

	public abstract Page<Activity> findAll(Pageable pageable);

}
