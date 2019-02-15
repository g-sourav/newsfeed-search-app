package com.news.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.Search;
import com.news.entity.User;
import com.news.service.user.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends GlobalErrorHandlerController{
	
	@Autowired
	private UserService userService;
	
	@PostMapping("searchSave")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String searchSave(@RequestBody Search search)
	{
		return userService.searchSave(search);
	}
	
	@GetMapping("searchHistory/{userEmail}")
	@PreAuthorize("hasRole('ROLE_USER')")
		public List<Search> searchHistory(@PathVariable("userEmail") String userEmail){		
			return userService.searchHistory(userEmail);	
			}
	@GetMapping("searchDelete/{searchId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String searchDelete(@PathVariable("searchId") Long searchId){
		return userService.searchDelete(searchId);
	}

}