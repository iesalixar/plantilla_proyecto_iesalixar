package com.fitconnet.persitence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Notification;

@Repository("notificationRepository")
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n FROM Notification n WHERE n.receiverId = ?1")
	Page<Notification> findByRecipientId(Long userId, Pageable pageable);

}
