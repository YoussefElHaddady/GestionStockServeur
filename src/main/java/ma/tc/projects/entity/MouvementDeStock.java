package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ma.tc.projects.enums.TypeDeMvmt;

@Entity
@Table(name = "mouvement_de_stock")
public class MouvementDeStock implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMvmtStk;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(updatable = false)
	private Date dateMvmt;

	@NotNull
	private int quantite;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TypeDeMvmt type;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_produit", nullable = false)
	public Produit produit;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_magasin", nullable = false)
	public Magasin magasin;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "id_utilisateur", nullable = false) private User
	 * utilisateur;
	 */

	public MouvementDeStock() {

	}

	public MouvementDeStock(Produit produit, Magasin magasin, @NotNull TypeDeMvmt type, @NotNull int quantite,
			@NotNull Date dateMvmt) {
		super();
		this.produit = produit;
		this.magasin = magasin;
		this.type = type;
		this.quantite = quantite;
		this.dateMvmt = dateMvmt;
	}

	public long getIdMvmtStk() {
		return idMvmtStk;
	}

	public void setIdMvmtStk(long idMvmtStk) {
		this.idMvmtStk = idMvmtStk;
	}

	public Date getDateMvmt() {
		return dateMvmt;
	}

	public void setDateMvmt(Date dateMvmt) {
		this.dateMvmt = dateMvmt;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public TypeDeMvmt getType() {
		return type;
	}

	public void setType(TypeDeMvmt type) {
		this.type = type;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	/*
	 * public User getUtilisateur() { return utilisateur; }
	 * 
	 * public void setUtilisateur(User utilisateur) { this.utilisateur =
	 * utilisateur; }
	 */
}