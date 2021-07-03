package com.family.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.family.constants.CommonConstants;
import com.family.constants.ListResponse;
import com.family.constants.Response;
import com.family.model.User;
import com.family.repository.UserRepository;
import com.family.service.UserService;
import com.family.utils.DateUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	public Response saveUserData(User user) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		User userEmailObj = null;
		User userPhoneObj = null;
		userEmailObj = userRepository.findByEmailAndIsActive(user.getEmail(), true);
		userPhoneObj = userRepository.findByPrimaryPhoneAndIsActive(user.getPrimaryPhone(), true);

		try {

			if (user.getUserId() != null) {
				if (userEmailObj != null && userPhoneObj != null) {
					if ((user.getUserId().equals(userEmailObj.getUserId()))
							&& (user.getUserId().equals(userPhoneObj.getUserId()))) {
						user.setIsActive(true);
						user.setUpdatedOn(DateUtil.getTodayDate());
						response.setData(userRepository.save(user));
						status.setMessage(CommonConstants.User.SAVE);
						status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
						response.setStatus(status);
						return response;
					} else {
						status.setMessage(CommonConstants.User.EMAILEXIST);
						status.setSuccess(CommonConstants.ResponseStatus.FAIL);
						response.setStatus(status);
						return response;
					}

				} else if (userEmailObj == null || userPhoneObj == null) {
					user.setUpdatedOn(DateUtil.getTodayDate());
					response.setData(userRepository.save(user));
					status.setMessage(CommonConstants.User.SAVE);
					status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);

					response.setStatus(status);
					return response;
				}
			} else {
				if ((userEmailObj != null)) {
					status.setMessage(CommonConstants.User.EMAILEXIST);
					status.setSuccess(CommonConstants.ResponseStatus.FAIL);
					response.setStatus(status);
					return response;
				}
			}
			response.setData(userRepository.save(user));
			status.setMessage(CommonConstants.User.SAVE);
			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			response.setStatus(status);
		}
		catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("User session exception issue " + e.getMessage());
			status.setMessage(CommonConstants.Authentication.INVALIDATESESSIONFAIL);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}
		return response;
	}

	public ListResponse getAllUsersIsActive(boolean isActive) {
		ListResponse response = new ListResponse();
		ListResponse.Status status = new ListResponse.Status();
		try {

			List<User> userList = userRepository.findAllAndByIsActive(isActive);

			if (!userList.isEmpty() && userList.size() > 0) {
				response.setData(userList);
				status.setMessage(CommonConstants.User.LISTFOUND);
				status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			} else {
				response.setData(null);
				status.setMessage(CommonConstants.User.LISTNOTFOUND);
				status.setSuccess(CommonConstants.ResponseStatus.FAIL);

			}
			response.setTotalResult(userList.size());

		} catch (Exception e) {
			status.setMessage(e.toString());
		}
		response.setStatus(status);
		return (response);
	}

	@Override
	public Response getUserId(int userId) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		User user = userRepository.findById(userId).get();
		if (user != null) {
			response.setData(user);
			status.setMessage(CommonConstants.User.RECORDFOUND);
			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		}
		response.setStatus(status);
		return response;
	}

	@Override
	public Response deleteUserById(int deleteUserId) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		userRepository.deleteById(deleteUserId);
		status.setMessage(CommonConstants.User.DELETE);
		status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		response.setStatus(status);
		return response;
	}

}
