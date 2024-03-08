package com.eZonWork.Service.Impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eZonWork.Model.CommonEntity.UserInfo;
import com.eZonWork.Repo.CommonRepo.UserDetailsRepo;
import com.eZonWork.Service.CommonService;
import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonResponse;
import com.eZonWork.Utility.CommonUtility;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private UserDetailsRepo detailsRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public CommonResponse<List<UserInfo>> getAllRegister() {
		try {
			List<UserInfo> infos = detailsRepo.findByStatus(CommonConstant.IS_ACTIVE);
			return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success, "Data Fetched Successfully",
					infos);
		} catch (Exception ex) {
			return CommonResponse.of(CommonConstant.ErrorCode, CommonConstant.Error, "Data Fetched UnSuccessful",
					ex.getMessage());
		}

	}

	@Override
	public CommonResponse<UserInfo> saveOrUpdateUser(UserInfo userDetails) throws UnknownHostException {

		try {
			userDetails.setCrtDate(new Date());
			userDetails.setCrtIp(CommonUtility.getIpAddress());
			userDetails.setCrtUser(userDetails.getUserName());
			userDetails.setPassWord(encoder.encode(userDetails.getPassWord()));
			userDetails.setStatus(CommonConstant.IS_ACTIVE);
			userDetails = detailsRepo.save(userDetails);
			return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success, "Data Saved Successfully",
					userDetails);
		} catch (Exception ex) {

			return CommonResponse.of(CommonConstant.ErrorCode, CommonConstant.Error, "Data Saved UnSuccessful",
					ex.getMessage());
		}

	}

	@Override
	public CommonResponse<UserInfo> updateUser(UserInfo userDetail) throws UnknownHostException {
		// TODO Auto-generated method stub
		try {
			UserInfo info = detailsRepo.findById(userDetail.getUserId()).orElseThrow();
			if (info != null) {
				userDetail.setCrtDate(info.getCrtDate());
				userDetail.setCrtIp(info.getCrtIp());
				userDetail.setCrtUser(info.getCrtUser());
				userDetail.setLstDate(new Date());
				userDetail.setLstIp(CommonUtility.getIpAddress());
				userDetail.setLstUser(userDetail.getUserName());
				userDetail.setStatus(CommonConstant.IS_ACTIVE);
				userDetail.setPassWord(encoder.encode(userDetail.getPassWord()));

			}
			userDetail = detailsRepo.save(userDetail);
			return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success, "Data Updated Successfully",
					userDetail);
		} catch (Exception ex) {

			return CommonResponse.of(CommonConstant.ErrorCode, CommonConstant.Error, "Data Updated UnSuccessful",
					ex.getMessage());
		}
	}

	@Override
	public CommonResponse<UserInfo> getUserById(Integer userId) {
		try {
			UserInfo userInfo = detailsRepo.findById(userId).orElseThrow();

			return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success, "Data Fetched Successfully",
					userInfo);

		} catch (Exception ex) {
			return CommonResponse.of(CommonConstant.ErrorCode, CommonConstant.Error, "Data Fetched UnSuccessful",
					ex.getMessage());
		}

	}

	@Override
	public CommonResponse<UserInfo> deleteUserById(Integer userId) {
		try {
			UserInfo userInfo = detailsRepo.findById(userId).orElseThrow();
			if (userInfo != null) {
				userInfo.setStatus(CommonConstant.IS_DEAVTIVE);
				userInfo.setLstDate(new Date());
				userInfo.setLstIp(CommonUtility.getIpAddress());
				userInfo.setLstUser(userInfo.getUserName());
			}
			detailsRepo.save(userInfo);
			return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success, "Data Deleted Successfully");
		} catch (Exception ex) {
			return CommonResponse.of(CommonConstant.ErrorCode, CommonConstant.Error, "Data Deleted UnSuccessful",
					ex.getMessage());
		}

	}

}
