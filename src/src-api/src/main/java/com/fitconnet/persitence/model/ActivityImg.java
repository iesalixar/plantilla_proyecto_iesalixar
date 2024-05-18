package com.fitconnet.persitence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@DiscriminatorValue("ACTIVITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityImg extends Image {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "C_FK_ACTIVITY_ID", nullable = false)
	private Activity actividad;

}
