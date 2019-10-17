package ma.tc.projects.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "magasins")
public class Magasin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMagasin;

	private String nom;

	private String adresse;

	private int numero_patente;
	private int superficie;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "magasin" })
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_user", nullable = false,referencedColumnName = "id")
	public AppUser user;

	public Magasin() {

	}
	
	public Magasin(String nom, String adresse, int numero_patente, int superficie) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.numero_patente = numero_patente;
		this.superficie = superficie;
	}

	public Magasin(long idMagasin, String nom, String adresse, int numero_patente, int superficie) {
		super();
		this.idMagasin = idMagasin;
		this.nom = nom;
		this.adresse = adresse;
		this.numero_patente = numero_patente;
		this.superficie = superficie;
	}

	public long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNumero_patente() {
		return numero_patente;
	}

	public void setNumero_patente(int numero_patente) {
		this.numero_patente = numero_patente;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

}
