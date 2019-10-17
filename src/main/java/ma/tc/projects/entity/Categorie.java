package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategorie;

	@NotNull
	private String label;

	private String description;

	@JsonIgnoreProperties("categorie")
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produit> produits = new ArrayList<>();

	// this attribute well not be mapped (not a database attribute)
	// we'll need it to return the quantities of products in the category (for the
	// current Magasin)
	@Transient
	private List<Integer> quantites;

	public Categorie() {

	}

	public Categorie(@NotNull String label, String description) {
		super();
		this.label = label;
		this.description = description;
	}

	public Categorie(long idCategorie, @NotNull String label, String description) {
		super();
		this.idCategorie = idCategorie;
		this.label = label;
		this.description = description;
	}

	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Integer> getQuantites() {
		return quantites;
	}

	public void setQuantites(List<Integer> quantites) {
		this.quantites = quantites;
	}

	public void addProduit(Produit produit) {
		produits.add(produit);
		produit.setCategorie(this);
	}

	public void removeProduit(Produit produit) {
		produits.remove(produit);
		produit.setCategorie(null);
	}

}