package com.ait.project.template.modules.user.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="user", schema="public")
public class UserOBT {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_user_id_seq")
	@SequenceGenerator(name="generator_user_id_seq", sequenceName = "user_id_seq", schema = "public", allocationSize = 1)
	private long userId;
	
	@Column(name="name")
	private String userName;
	
	@Column(name="address")
	private String userAddress;

	@Column(name="email")
	private String userEmail;

	@Column(name="password")
	private String userPassword;

	@CreationTimestamp
	private ZonedDateTime createdAt;

	@UpdateTimestamp
	private ZonedDateTime updatedAt;
}
