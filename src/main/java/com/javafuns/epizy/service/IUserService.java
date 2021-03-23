package com.javafuns.epizy.service;

import java.util.List;

import com.javafuns.epizy.model.User;

public interface IUserService {

	String saveUser(User user) throws Exception;
	User getuser(int id) throws Exception;
	List<User> getAllUser() throws Exception;
	boolean isPresent(int id) throws Exception;
	String updateUser(User user,int id) throws Exception;
	String deactiveUser(User user,int id)throws Exception;
	String activeUser(User user,int id)throws Exception;
	String makeAdmin(User user,int id);
}
