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
@Table(name = "ligne_cmd_client")
public class LigneCmdClient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLigneCmdClient;

	@NotNull
	private int quantiteDemandee;
	@NotNull
	private int quantiteServie;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cmd_client", nullable = false)
	private CommandeClient commandeClient;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_produit", nullable = false)
	private Produit produit;

	public LigneCmdClient() {

	}

	public LigneCmdClient(long idLigneCmdClient, @NotNull int quantiteDemandee, @NotNull int quantiteServie,
			CommandeClient commandeClient, Produit produit) {
		super();
		this.idLigneCmdClient = idLigneCmdClient;
		this.quantiteDemandee = quantiteDemandee;
		this.quantiteServie = quantiteServie;
		this.commandeClient = commandeClient;
		this.produit = produit;
	}

	public long getIdLigneCmdClient() {
		return idLigneCmdClient;
	}

	public void setIdLigneCmdClient(long idLigneCmdClient) {
		this.idLigneCmdClient = idLigneCmdClient;
	}

	public int getQuantiteDemandee() {
		return quantiteDemandee;
	}

	public void setQuantiteDemandee(int quantiteDemandee) {
		this.quantiteDemandee = quantiteDemandee;
	}

	public int getQuantiteServie() {
		return quantiteServie;
	}

	public void setQuantiteServie(int quantiteServie) {
		this.quantiteServie = quantiteServie;
	}

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}