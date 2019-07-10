package com.reuters.grpcclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reuters.grpcclient.bean.User;
import com.reuters.grpcclient.service.inf.UserService;

/**
 * User controller.
 * 
 * @author qingqingz
 *
 */
@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * List all users.
	 *
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userService.listAllUsers());
		return "index";
	}

	/**
	 * View a specific user by its id.
	 *
	 * @param id
	 */
	@RequestMapping("user/{id}")
	public String showUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "usershow";
	}

	/**
	 * Edit a specific user by its id.
	 * 
	 * @param id
	 */
	@RequestMapping("user/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "userform";
	}

	/**
	 * Add user.
	 *
	 */
	@RequestMapping("user/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "userform";
	}

	/**
	 * Save user
	 *
	 */
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Validated User user, BindingResult rs) {
		if (rs.hasErrors()) {
			return "userform";
		}
		userService.saveUser(user);
		return "redirect:/";
//		return "redirect:/user/" + user.getId();
	}

	/**
	 * Delete user by id.
	 *
	 */
	@RequestMapping("user/delete/{id}")
	public String delete(@PathVariable Integer id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

}
