package com.fitconnet.persitence.model;

import com.fitconnet.dto.entities.ImageDTO;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@DiscriminatorValue("USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfileImg extends Image {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "C_FK_USER_ID", nullable = false)
	private User user;

	public ProfileImg(ImageDTO imageDTO, User user) {
		super(null, imageDTO.getImage());
		this.user = user;
	}

}