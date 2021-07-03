package com.family.service;

import com.family.constants.ListResponse;
import com.family.constants.Response;
import com.family.model.User;

public interface UserService {

	Response saveUserData(User user);

	ListResponse getAllUsersIsActive(boolean isActive);

	Response getUserId(int userId);

	Response deleteUserById(int id);

}
