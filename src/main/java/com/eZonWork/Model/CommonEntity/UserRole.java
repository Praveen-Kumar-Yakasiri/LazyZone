package com.eZonWork.Model.CommonEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleId;
	
//	private String userName;
	
	private int roleId;
	
	
	private String userRole;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="userId")
	private UserInfo userDetails;
	
	   @Override
	    public String toString() {
	        return "UserRole{" +
	                "userRoleId=" + userRoleId +
	               
	                ", roleId=" + roleId +
	                ", userRole='" + userRole + '\'' +
	                '}';
	    }

}
