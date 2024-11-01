package com.example.demo.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.security.CustomUserDetail;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.service.CommentServiceImpl;
import com.example.demo.service.RecommendationServiceImpl;

import model.Recommendation;
import model.User;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private RecommendationServiceImpl recommendationService; // Assuming you have a service to handle database
																// operations
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private CommentServiceImpl commentService;

	@GetMapping("/getAllUsers")
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin/userlist"; // Assuming userList.jsp is the name of your JSP file
	}
	
	@PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        // Call the service method to delete the user by ID
		//commentService.deleteCommentWithUserId(userId);
        userService.deleteUserById(userId);
        
        // Redirect to the page displaying the updated list of users
        return "admin/userList2";
    }
	
	@GetMapping("/dashboard")
	public String adminDashboard(Principal principal) {
		// Access the username
//        String username = principal.getName();

		// Access the roles
//        Collection<? extends GrantedAuthority> authorities = ((Authentication) principal);
//        boolean isAdmin = authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

		// Your logic based on user roles
		// ...

		return "admin/dashboard";
	}

	@PostMapping("/submitRecommendation")
	public String submitRecommendation(@ModelAttribute("rec") Recommendation rec, Principal principal) {
		// Get the currently authenticated user's ID
		// This assumes you have a UserDetails service implementation that returns a
		// User object with an ID
		int userId = getUserId(principal);

		// Set the user_id and timestamp before saving the recommendation
		rec.setUserId(userId);
		rec.setTimestamp(new Timestamp(System.currentTimeMillis()));
		rec.setRecommendationText(rec.getRecommendationText());

		// Save the recommendation
		recommendationService.saveRecommendation(rec);

		return "redirect:/admin/dashboard.jsp"; // Redirect to the admin dashboard or any other page
	}

	private int getUserId(Principal principal) {
		// Implement a method to get the user ID from your UserDetails implementation
		// For example, if your UserDetails is of type CustomUserDetail, you can extract
		// the ID from it
		// Alternatively, you can directly use your user_id retrieval logic here
		// For simplicity, let's assume your UserDetails implementation has a method
		// getUserId()
		CustomUserDetail userDetails = (CustomUserDetail) ((Authentication) principal).getPrincipal();
		return userDetails.getUserId();
	}
}
