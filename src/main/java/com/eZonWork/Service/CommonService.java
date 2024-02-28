package com.eZonWork.Service;

import java.net.UnknownHostException;

import com.eZonWork.Model.UserInfo;
import com.eZonWork.Utility.CommonResponse;

public interface CommonService {

  public CommonResponse<UserInfo> saveOrUpdateUser(UserInfo userDetails) throws UnknownHostException;

  public CommonResponse<UserInfo> getUserById(Integer userId);

  public CommonResponse<UserInfo> deleteUserById(Integer userId);

}
