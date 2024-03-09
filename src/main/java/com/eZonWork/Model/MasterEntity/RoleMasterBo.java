package com.eZonWork.Model.MasterEntity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ROLE_MASTER")
public class RoleMasterBo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String roleName;
	
	private Integer roleId;
	
	private String status;
	
    private String crtUser;
	
	private String crtIp;
	
	private Date crtDate;
	
	private String lstUpdUser;
	
	private String lstUpdIp;
	
	private Date lstDate;
	
	@Transient
	private String crudFlag;
	
}
