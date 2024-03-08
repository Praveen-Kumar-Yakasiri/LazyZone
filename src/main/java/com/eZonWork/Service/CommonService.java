package com.eZonWork.Service;

import java.net.UnknownHostException;
import java.util.List;

import com.eZonWork.Model.CommonEntity.UserInfo;
import com.eZonWork.Utility.CommonResponse;

public interface CommonService {

	public CommonResponse<UserInfo> saveOrUpdateUser(UserInfo userDetails) throws UnknownHostException;

	public CommonResponse<UserInfo> getUserById(Integer userId);

	public CommonResponse<UserInfo> deleteUserById(Integer userId);

	public CommonResponse<List<UserInfo>> getAllRegister();

	public CommonResponse<UserInfo> updateUser(UserInfo userDetail) throws UnknownHostException;

}
