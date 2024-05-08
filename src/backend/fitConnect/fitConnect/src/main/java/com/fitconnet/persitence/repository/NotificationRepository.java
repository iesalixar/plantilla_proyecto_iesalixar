package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitconnet.persitence.model.Notification;

@Repository("notificationRepository")
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
