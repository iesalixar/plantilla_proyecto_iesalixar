package com.fitconnet.persitence.repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Notification;
import com.fitconnet.persitence.model.User;

@Repository("notificationRepository")
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n FROM Notification n WHERE n.receiver = ?1")
	Optional<Set<Notification>> findByRecipient(Optional<User> user);

	@Query("SELECT n FROM Notification n WHERE n.date = ?1")
	Boolean existByDate(Date date);

}
