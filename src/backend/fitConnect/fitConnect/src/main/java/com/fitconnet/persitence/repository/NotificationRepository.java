package com.fitconnet.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitconnet.persitence.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
