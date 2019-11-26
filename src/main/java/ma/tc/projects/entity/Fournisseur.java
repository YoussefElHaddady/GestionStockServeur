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
	
//	credit positif : la somme d'argent que le magasin doit payer à la personne (fournisseur)
//	credit negatif (prêt) : La somme d’argent que la personne (fournisseur) doit payer au magasin
	private double credit;

	public Fournisseur() {

	}

	public Fournisseur(String raison_sociale) {
		super();
		this.raison_sociale = raison_sociale;
	}

	public Fournisseur(String raison_sociale, double credit) {
		super();
		this.raison_sociale = raison_sociale;
		this.credit = credit;
	}

	public Fournisseur(String CIN, String name, int RIP, String phone, String adresse, String email, String picture,
			String raison_sociale) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.raison_sociale = raison_sociale;
	}

	public Fournisseur(String CIN, String name, int RIP, String phone, String adresse, String email, String picture,
			String raison_sociale, double credit) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.raison_sociale = raison_sociale;
		this.credit = credit;
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

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}
}