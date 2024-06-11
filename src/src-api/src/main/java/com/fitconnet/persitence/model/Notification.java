package com.fitconnet.persitence.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an notification entity.
 */
@Entity
@Table(name = "T_NOTIFICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Notification {
	private static final long serialVersionUID = 1L;
	/**
	 * The unique identifier for the notification.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_PK_NOTIFICATION_ID", unique = true, nullable = false)
	private Long id;

	/**
	 * The message content of the notification.
	 */
	@Column(name = "C_NOTIFICATION_MESSAGE", nullable = false)
	private String message;

	/**
	 * The date when the notification was created.
	 */
	@Column(name = "C_NOTIFICATION_DATE", nullable = false)
	private Date date;

	/**
	 * The user who receives the notification.
	 */
	@ManyToOne
	@JoinColumn(name = "C_NOTIFICATION_RECIEVER", nullable = false)
	private User receiver;

}
