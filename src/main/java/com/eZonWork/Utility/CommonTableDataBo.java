package com.eZonWork.Utility;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommonTableDataBo {
	
	private String crtUser;
	
	private String crtIp;
	
	private Date crtDate;
	
	private String lstUpdUser;
	
	private String lstUpdIp;
	
	private Date lstDate;

}
