package ma.tc.projects.controller;

import ma.tc.projects.entity.*;
import ma.tc.projects.repository.AppUserRepository;
import ma.tc.projects.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.tc.projects.repository.MagasinRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
 
  @Autowired
  AuthenticationManager authenticationManager;
 
  @Autowired
  AppUserRepository userRepository;
 

  @Autowired
  MagasinRepository magasinRepo;
 
  @Autowired
  PasswordEncoder encoder;
 /*
  @Autowired
  JwtProvider jwtProvider;
 */
  /**
   * @param loginRequest
   * @return JWT to client
   * @Role : 
   * 	+)	attempt to authenticate with AuthenticationManager bean.
   *	+)	add authentication object to SecurityContextHolder
   *	+)	generate JWT token, then return JWT to client
   */



  @Autowired
  private AccountService accountService;
  @PostMapping(value ="/register" )
  public AppUser register(@RequestBody UserForm userForm){
    return accountService.saveUser(userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());


  }


  /**
 * @param userRequested
 * @return ResponseMessage object
 * @Role : 
 * 		+)	check username/email is already in use.
 *		+)	create User object
 *		+)	store to database
 */
  /*

  Had le codeeeee siir l classe AcpuntServiceImpl  package ma.tc.projects.service;
  wdir traitement dyal hna lheh

@PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User userRequested) {
	
	System.out.println("username : " + userRequested.getUsername());
	System.out.println("password : " + userRequested.userPass());
	
	
    if (userRepository.existsByUsername(userRequested.getUsername())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }
 
    /*if (userRepository.existsByEmail(userRequested.getEmail())) {
      return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
          HttpStatus.BAD_REQUEST);
    }
 
    // Creating user's account
    AppUser user = new AppUser(userRequested.getName(), userRequested.getUsername(), userRequested.getEmail(),
        encoder.encode(userRequested.userPass()));

    Set<Role> strRoles = userRequested.getRoles();
    Set<Role> roles = new HashSet<>();
 
    strRoles.forEach(role -> {

    	System.out.println("role : " + role.getName());
      switch (role.getName()) {
      case ROLE_ADMIN:
        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(adminRole);
 
        break;
      case ROLE_PM:
        Role pmRole = roleRepository.findByName(RoleEnum.ROLE_PM)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(pmRole);
 
        break;
      default:
        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
      }
    });
 
    user.setRoles(roles);
    
    Magasin magasin = userRequested.getMagasin();
    if(magasinRepo.existsById(magasin.getIdMagasin()))
    	throw new RuntimeException("Fail! -> Cause: the Magasin object is already existed you have to insert a new one.");
    
    // Insert the user
    userRepository.save(user);
    
    // Inserting Magasin if not exist
    if (magasin.getIdMagasin() <= 0) {
    	magasin.setUser(user);
    	magasinRepo.save(magasin);
    }
 
    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
  }
  */
}
