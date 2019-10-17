package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "factures_clients")
public class Facture implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFacture;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateFacture;

	@NotNull
	private double totalFinal;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_commandeClient", nullable = false)
	public CommandeClient commandeClient;

	/*
	 * @OneToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "id_commandeFournisseur", nullable = false) public
	 * CommandeFournisseur commandeFournisseur;
	 */

	public Facture() {

	}

	public Facture(long idFacture, @NotNull Date dateFacture, @NotNull double totalFinal,
			CommandeClient commandeClient) {
		super();
		this.idFacture = idFacture;
		this.dateFacture = dateFacture;
		this.totalFinal = totalFinal;
		this.commandeClient = commandeClient;
	}

	public long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(long idFacture) {
		this.idFacture = idFacture;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public double getTotalFinal() {
		return totalFinal;
	}

	public void setTotalFinal(double totalFinal) {
		this.totalFinal = totalFinal;
	}

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

}