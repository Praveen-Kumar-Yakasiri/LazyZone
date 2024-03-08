package com.eZonWork.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String userName;

	private String passWord;

	private String mobileNo;
	
	private String emaildId;
	
	private String address;

	private String crtUser;

	private String crtIp;

	private Date crtDate;

	private String lstUser;

	private String lstIp;

	private Date lstDate;

	private String status;

	@JsonManagedReference
	@OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UserRole userRoles;
}
