package ma.tc.projects.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.entity.Categorie;
import ma.tc.projects.entity.Ouvrier;
import ma.tc.projects.service.ICrudService;
import ma.tc.projects.service.Imp.OuvrierService;
 

/*
 * testing if the URLs work well and the user roles are respected
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	
//	@Autowired
//	public ICrudService<Category, Long> categoryService;
	
//	@Autowired
//	private OuvrierService ouvrierService;
  
  //@PreAuthorize to decide whether a method can actually be invoked or not
  @GetMapping("/api/test/user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String userAccess() {
    return ">>> User Contents!";
  }
 
  @GetMapping("/api/test/pm")
  @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
  public String projectManagementAccess() {
    return ">>> Project Management Board";
  }
  
  @GetMapping("/api/test/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return ">>> Admin Contents";
  }
  
  /*@GetMapping("/api/test/appusers")
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> getAppUsers() {
	  List users = new ArrayList<>();
	  
	  users.add(new User(1, "user1", "pass1"));
	  users.add(new User(2, "user2", "pass3"));
	  return users;
  }*/
  
//  @GetMapping("/api/test/cats")
//  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//  public List<Category> getCategories(){
//	return categoryService.getAll();
//  }
  
//  @GetMapping("/api/ouvrier/reset_absence")
//  public boolean resetAbsence() {
//	  ouvrierService.resetAbsenceNumberForAllWorkers();
//	  
//	  return true;
//  }
}
