package ma.tc.projects.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
//@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@DynamicUpdate
public class AppUser extends Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private boolean actived;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "user" })
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private Magasin magasin;

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public AppUser() {
		super();
	}

	public AppUser(long idUser, String username, String password) {
		super();
		this.id = idUser;
		this.username = username;
		this.password = password;
	}

	public AppUser(@NotNull @Size(min = 3, max = 50) String username,
			@NotNull @Size(min = 8, max = 100) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AppUser(String name, @NotNull @Size(min = 3, max = 50) String username, String email,
			@NotNull @Size(min = 8, max = 100) String password) {
		super(null, name, 0, null, null, email, null);
		this.username = username;
		this.password = password;
	}

	public AppUser(String name, @NotNull @Size(min = 3, max = 50) String username,
			@NotNull @Size(min = 8, max = 100) String password, String email, String CIN, int RIP, String phone,
			String adresse, String picture) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

}
