package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduit;

	@NotNull
	@NaturalId
	private String codeProduit;

	@NotNull
	private String libelle;
	private String description;
	private String image;

	@Temporal(TemporalType.DATE)
	private Date dateExp;
	@Temporal(TemporalType.DATE)
	private Date datePro;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "produits" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_categorie", nullable = false)
	private Categorie categorie;

	@Transient
	private List<DetailProduit> details;

	public Produit() {

	}

	public Produit(@NotNull String codeProduit, @NotNull String libelle, String description, String image, Date dateExp,
			Date datePro, Categorie categorie) {
		super();
		this.codeProduit = codeProduit;
		this.libelle = libelle;
		this.description = description;
		this.image = image;
		this.dateExp = dateExp;
		this.datePro = datePro;
		this.categorie = categorie;
	}

	public Produit(long idProduit, @NotNull String codeProduit, @NotNull String libelle, String description,
			String image, Date dateExp, Date datePro, Categorie categorie, List<DetailProduit> details) {
		super();
		this.idProduit = idProduit;
		this.codeProduit = codeProduit;
		this.libelle = libelle;
		this.description = description;
		this.image = image;
		this.dateExp = dateExp;
		this.datePro = datePro;
		this.categorie = categorie;
		this.details = details;
	}

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDateExp() {
		return dateExp;
	}

	public void setDateExp(Date dateExp) {
		this.dateExp = dateExp;
	}

	public Date getDatePro() {
		return datePro;
	}

	public void setDatePro(Date datePro) {
		this.datePro = datePro;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<DetailProduit> getDetails() {
		return details;
	}

	public void setDetails(List<DetailProduit> details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProduit ^ (idProduit >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Produit))
			return false;
		Produit other = (Produit) obj;
		if (idProduit != other.idProduit)
			return false;
		return true;
	}

}