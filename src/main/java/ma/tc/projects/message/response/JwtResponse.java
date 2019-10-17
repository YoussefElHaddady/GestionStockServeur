package ma.tc.projects.message.response;


import java.util.Collection;

import ma.tc.projects.entity.AppUser;
import ma.tc.projects.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

/**
 * Role :
 * 		JwtResponse object will be returned by SpringBoot server once an authentication is successful,
 * 		it contains:

		+)	JWT Token
		+)	Schema Type of Token
		+)	Username
		+)	Array of User’s Authorities
 *
 */
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private Collection<? extends GrantedAuthority> authorities;
  private long magasinId = 0;
  private String magasinName = "";


  public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities, AppUserRepository userRepo) {
    this.token = accessToken;
    this.username = username;
    this.authorities = authorities;
    
    AppUser user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));
    
    this.magasinName = user.getMagasin().getNom();
    this.magasinId = user.getMagasin().getIdMagasin();
  }
 
  public String getAccessToken() {
    return token;
  }
 
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }
 
  public String getTokenType() {
    return type;
  }
 
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
  public String getUsername() {
    return username;
  }
 
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public long getMagasinId() {
	  System.out.println("getMagasinId :: " + magasinId);
	return magasinId;
  }
	
  public void setMagasinId(long magasinId) {
	this.magasinId = magasinId;
  }
	
  public String getMagasinName() {
	  System.out.println("getMagasinName :: " + magasinName);
	return magasinName;
  }
	
  public void setMagasinName(String magasinName) {
	this.magasinName = magasinName;
  }

}
