package ma.tc.projects.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fournisseurs")
public class Fournisseur extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFournisseur;

	private String raison_sociale;

	public Fournisseur() {

	}

	public Fournisseur(String raison_sociale) {
		super();
		this.raison_sociale = raison_sociale;
	}

	public Fournisseur(String CIN, String name, int RIP, String phone, String adresse, String email, String picture,
			String raison_sociale) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.raison_sociale = raison_sociale;
	}

	public long getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(long idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	public String getRaison_sociale() {
		return raison_sociale;
	}

	public void setRaison_sociale(String raison_sociale) {
		this.raison_sociale = raison_sociale;
	}

}