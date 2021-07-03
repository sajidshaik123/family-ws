package com.family.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllAndByIsActive(boolean isActive);

	User findByEmailAndIsActive(String email, boolean b);

	User findByPrimaryPhoneAndIsActive(String primaryPhone, boolean b);
	
	


}
