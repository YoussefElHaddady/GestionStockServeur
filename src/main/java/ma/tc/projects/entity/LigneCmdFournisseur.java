package ma.tc.projects.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ligne_cmd_fournisseur")
public class LigneCmdFournisseur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLigneCmdFournisseur;

	@NotNull
	private int quantite;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cmd_fournisseur", nullable = false)
	private CommandeFournisseur commandeFournisseur;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_produit", nullable = false)
	private Produit produit;

	public LigneCmdFournisseur() {

	}

	public LigneCmdFournisseur(@NotNull int quantite, CommandeFournisseur commandeFournisseur, Produit produit) {
		super();
		this.quantite = quantite;
		this.commandeFournisseur = commandeFournisseur;
		this.produit = produit;
	}

	public long getIdLigneCmdFournisseur() {
		return idLigneCmdFournisseur;
	}

	public void setIdLigneCmdFournisseur(long idLigneCmdFournisseur) {
		this.idLigneCmdFournisseur = idLigneCmdFournisseur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public CommandeFournisseur getCommandeFournisseur() {
		return commandeFournisseur;
	}

	public void setCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
		this.commandeFournisseur = commandeFournisseur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}