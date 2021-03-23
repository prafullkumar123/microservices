package com.javafuns.epizy.controller;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javafuns.epizy.exception.UserRegException;
import com.javafuns.epizy.jwtUtil.JWTUtil;
import com.javafuns.epizy.model.User;
import com.javafuns.epizy.model.UserRegReq;
import com.javafuns.epizy.model.UserRegResp;
import com.javafuns.epizy.model.UserRequest;
import com.javafuns.epizy.model.UserResponse;
import com.javafuns.epizy.service.IUserService;
import com.javafuns.epizy.validator.UserRegValidator;

@RestController
@RequestMapping("/rest")
public class RegistrationRestController  {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRegValidator validator;

	
	@Autowired
	private AuthenticationManager  authManager;
	
	/**
	 * @param userReg
	 * @return 
	 * @throws Exception 
	 * Description : This method is use for save user details for farther Validation or validation.
	 */
	@PostMapping("/save")
	public @ResponseBody UserRegResp regPage(@RequestBody UserRegReq userReg){
		
		UserRegResp userRegResp = null;
		String msg = null;
		
		try {
			validator.userRegvalidateRequest(userReg);
			
			User user = new User(userReg.getName(), userReg.getMobile(), userReg.getEmail(), userReg.getUserName(), userReg.getPwd());
			
			Calendar c = Calendar.getInstance();
			
			user.setDateOfReg(c.getTime());
			
			msg = userService.saveUser(user);
			
			userRegResp = new UserRegResp(msg);
			
		} catch (UserRegException exe) {
			
			userRegResp = new UserRegResp(exe.getRespMsg());
			
			exe.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
		return userRegResp;
	}

	/**
	 * @param userReg
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@PutMapping("/update/{id}")
	public UserRegResp  updateUser(@RequestBody UserRegReq userReg,@PathVariable int id) throws Exception {
		
		String msg = null;
		
		UserRegValidator validator = new UserRegValidator();
		
		try {
		
			validator.userRegvalidateRequest(userReg);
			
			User user = new User(userReg.getName(), userReg.getMobile(), userReg.getEmail(), userReg.getUserName(), userReg.getPwd());
			
			boolean present=userService.isPresent(id);
			
			if(present == true) {
			
				msg = userService.updateUser(user,id);
			
			}else {
			
				msg = "User not Exist";
			
			}
	
		} catch (UserRegException e) {
		
			return new UserRegResp(e.getRespMsg()); 
		
		}
		return new UserRegResp(msg);
	}


	@GetMapping("/all")
	public List<?> getAllUsers() throws Exception{
		
		List<User> list = null;
		
		list = userService.getAllUser();	
		
		return list;
	}

	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable Integer id) throws Exception {
		
		User userObj = userService.getuser(id);
		
		return userObj;
	}

	@PutMapping("/delete/{id}")
	public String disableUser(@PathVariable Integer id) throws Exception {
	
		String msg = null;

		User userObj = userService.getuser(id);

		if("DEACTIVE".equals(userObj.getUserStatus()))
			msg = "Already Disabled";
		else if(userObj!=null)
			msg = userService.deactiveUser(userObj, id);

		return msg;
	}

	@PutMapping("/activate/{id}")
	public String enableUser(@PathVariable Integer id) throws Exception {

		String msg = null;
		
		User userObj = userService.getuser(id);

		if("ACTIVE".equals(userObj.getUserStatus()))
			msg = "Already EnAbled";
		else if(userObj!=null)
			msg = userService.activeUser(userObj, id);

		return msg;
	}


	@PostMapping("/login")
	public UserResponse loginCheck(@RequestBody UserRequest request) {
		//It will cross check user data with  database db using user details service 
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),request.getPassword()));
		// generate token 
		String token = new JWTUtil().generateToken(request.getUsername());
		return new UserResponse("Success",token);
	}

	@GetMapping("/user")
	public String user() {
		
		return "Welcome to USER";
	
	}

	@GetMapping("/admin")
	public String admin() {
	
		return "Welcome to ADMIN";
	
	}


	@GetMapping("/makeAdmin/{id}")
	private String makeAdmin(@PathVariable Integer id) throws Exception {
	
		String msg = null;
		
		User userObj = userService.getuser(id);
		
		if("ADMIN".equals(userObj.getUserStatus()))
			msg = "Allready ADMIN";
		else if(userObj!=null)
			msg = userService.makeAdmin(userObj, id);

		return msg;
	}


}