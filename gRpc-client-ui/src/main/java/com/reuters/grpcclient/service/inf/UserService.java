package com.reuters.grpcclient.service.inf;

import com.reuters.grpcclient.bean.User;

/**
 * Service Interface
 * 
 * @author qingqingz
 *
 */
public interface UserService {

	Iterable<User> listAllUsers();

	User getUserById(Integer id);

	User saveUser(User user);

	void deleteUser(Integer id);

}
