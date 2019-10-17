package ma.tc.projects.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Caisse implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCaisse;

	private double somme;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_user", nullable = false)
	private AppUser user;

	public Caisse() {

	}

	public Caisse(long idCaisse, double somme) {
		super();
		this.idCaisse = idCaisse;
		this.somme = somme;
	}

	public long getIdCaisse() {
		return idCaisse;
	}

	public void setIdCaisse(long idCaisse) {
		this.idCaisse = idCaisse;
	}

	public double getSomme() {
		return somme;
	}

	public void setSomme(double somme) {
		this.somme = somme;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

}
