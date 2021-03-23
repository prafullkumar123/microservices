package com.javafuns.epizy.service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javafuns.epizy.model.CustomSequences;
import com.javafuns.epizy.model.User;
import com.javafuns.epizy.repo.UserRepo;


@Service
public class UserServiceImpl implements IUserService,UserDetailsService {


	@Autowired
	private  UserRepo userRepo;

	@Autowired  
	private  MongoOperations mongo;

	@Autowired
	private BCryptPasswordEncoder encoder;
	/**
	 * Create auto generated id 
	 * @param seqName
	 * @return id
	 */
	public  int getNextSequence(String seqName)
	{
		CustomSequences counter = mongo.findAndModify(
				
				query(where("_id").is(seqName)),
				new Update().inc("seq",1),
				options().returnNew(true).upsert(true),
				CustomSequences.class);
		
		return counter.getSeq();
	}

	/**
	 * save User details taking User object given by user 
	 */
	@Override
	public String saveUser(User user) throws Exception {

		String pwd=user.getPwd();
		
		pwd=encoder.encode(pwd);
		
		user.setPwd(pwd);
		
		user.setId(getNextSequence("userSequence"));
		
		user.setRoles("USER");
		
		user.setUserStatus("ACTIVE");
		
		if(userRepo.save(user)==null)
			return "User Not Registered";
		else
			return "User Registerd";
	
	}
	
	@Override
	public List<User> getAllUser() throws Exception {
		// TODO Auto-generated method stub
		List<User> userObj = userRepo.findAll();
	
		return userObj;
	}

	@Override
	public User getuser(int id) throws Exception {
		
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findById(id);
		
		return user.get();
	}

	@Override
	public boolean isPresent(int id) throws Exception {
		
		// TODO Auto-generated method stub
		return userRepo.existsById(id);

	}

	@Override
	public String updateUser(User user,int id) throws Exception {
		user.setId(id);
		if(userRepo.save(user)==null)
			return "User is not Updated";
		else
			return "User Is Updated";
	}

	@Override
	public String deactiveUser(User user,int id) throws Exception {
	
		user.setId(id);
		
		user.setUserStatus("DEACTIVE");
		
		if(userRepo.save(user)==null)
			return "User is Unable to Deactivate";
		else
			return "User Is Deactivated";
	
	}

	@Override
	public String activeUser(User user, int id) throws Exception {
	
		user.setId(id);
		
		user.setUserStatus("ACTIVE");
		
		if(userRepo.save(user)==null)
			return "User is Unable to Activated";
		else
			return "User Is Activated";
	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user=userRepo.findByuserName(username);
		
		//Roles to Set<GA>
		Set<GrantedAuthority> authorities=new HashSet<>();
		
		String role=user.getRoles();
	
		authorities.add(new SimpleGrantedAuthority(role));
		//		for(String role:roles) {
		//			authorities.add(new SimpleGrantedAuthority(role));
		//		}

		// return Spring f/w User
		org.springframework.security.core.userdetails.User usr=
				new org.springframework.security.core.userdetails.User(
						user.getUserName(), user.getPwd(), authorities);
		
		return usr;
	
	}

	/**
	 * To Make Normal User as Admin
	 */
	@Override
	public String makeAdmin(User user, int id) {
	
		user.setId(id);
		
		user.setRoles("ADMIN");
		
		if(userRepo.save(user)==null)
			return "User is Unable make to ADMIN";
		else
			return "User is make as ADMIN";
	
	}

}
