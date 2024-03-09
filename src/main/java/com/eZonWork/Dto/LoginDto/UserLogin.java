package com.eZonWork.Dto.LoginDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	
	private String userName;
	
	private String passWord;

}
