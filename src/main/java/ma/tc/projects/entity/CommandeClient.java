package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "commande_client")
public class CommandeClient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCommandeClient;

	@NonNull
	@NaturalId
	private String codeCmd;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(updatable = false)
	private Date dateCmd;

	@NonNull
	private double montantPaye;
	@NonNull
	private double montantTotal;

	private boolean livraison;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_client", nullable = false)
	private Client client;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commandeClient")
	private Facture facture;

	@OneToMany(mappedBy = "commandeClient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReglementClient> reglements;

	public CommandeClient() {

	}

	public CommandeClient(String codeCmd, @NotNull Date dateCmd, double montantPaye, double montantTotal,
			boolean livraison) {
		super();
		this.codeCmd = codeCmd;
		this.dateCmd = dateCmd;
		this.montantPaye = montantPaye;
		this.montantTotal = montantTotal;
		this.livraison = livraison;
	}

	public long getIdCommandeClient() {
		return idCommandeClient;
	}

	public void setIdCommandeClient(long idCommandeClient) {
		this.idCommandeClient = idCommandeClient;
	}

	public String getCodeCmd() {
		return codeCmd;
	}

	public void setCodeCmd(String codeCmd) {
		this.codeCmd = codeCmd;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ReglementClient> getReglements() {
		return reglements;
	}

	public void setReglements(List<ReglementClient> reglements) {
		this.reglements = reglements;
	}

	// this two methods (addReglement & removeReglemnt) used to synchronize both
	// sides of the bidirectional association
	public void addReglement(ReglementClient reglement) {
		this.reglements.add(reglement);
		reglement.setCommandeClient(this);
	}

	public void removeReglement(ReglementClient reglement) {
		this.reglements.remove(reglement);
		reglement.setCommandeClient(null);
	}

	public boolean isLivraison() {
		return livraison;
	}

	public void setLivraison(boolean livraison) {
		this.livraison = livraison;
	}

}