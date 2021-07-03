package com.family.controller;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.family.constants.ListResponse;
import com.family.constants.Response;
import com.family.model.User;
import com.family.service.UserService;

@RestController
@RequestMapping(value = { "/user" })
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/create")
	public Response createUser(@RequestBody User user) {
		return userService.saveUserData(user);
	}

	@GetMapping(value = "/get")
	public ListResponse getAllUser(@PathParam(value = "isActive") boolean isActive) {
		return userService.getAllUsersIsActive(isActive);
	}

	@GetMapping(value = "/getUserId")
	public Response getBycri(@PathParam(value = "userId") Integer userId) {
		return userService.getUserId(userId);
	}

	@DeleteMapping(value = "/deleteUserId")
	public Response deleteUserId(@RequestParam(value = "userId") int userId) {
		return userService.deleteUserById(userId);
	}

}
