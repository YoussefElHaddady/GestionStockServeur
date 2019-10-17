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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "commande_fournisseur")
public class CommandeFournisseur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCmdFournisseur;

	@NonNull
	@NaturalId
	private String codeCmdF;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(updatable = false)
	private Date dateCmdF;

	@NonNull
	private double montantTotal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_fournisseur", nullable = false)
	private Fournisseur fournisseur;

	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "commandeFournisseur") private Facture facture;
	 */
	
	@OneToMany(mappedBy = "commandeFournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReglementFournisseur> reglements;

	public CommandeFournisseur() {

	}

	public CommandeFournisseur(String codeCmdF, @NotNull Date dateCmdF, double montantTotal, Fournisseur fournisseur) {
		super();
		this.codeCmdF = codeCmdF;
		this.dateCmdF = dateCmdF;
		this.montantTotal = montantTotal;
		this.fournisseur = fournisseur;
	}

	public long getIdCmdFournisseur() {
		return idCmdFournisseur;
	}

	public void setIdCmdFournisseur(long idCmdFournisseur) {
		this.idCmdFournisseur = idCmdFournisseur;
	}

	public String getCodeCmdF() {
		return codeCmdF;
	}

	public void setCodeCmdF(String codeCmdF) {
		this.codeCmdF = codeCmdF;
	}

	public Date getDateCmdF() {
		return dateCmdF;
	}

	public void setDateCmdF(Date dateCmdF) {
		this.dateCmdF = dateCmdF;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
	
	public List<ReglementFournisseur> getReglements() {
		return reglements;
	}

	public void setReglements(List<ReglementFournisseur> reglements) {
		this.reglements = reglements;
	}

	// this two methods (addReglement & removeReglemnt) used to synchronize both
	// sides of the bidirectional association
	public void addReglement(ReglementFournisseur reglement) {
		this.reglements.add(reglement);
		reglement.setCommandeFournisseur(this);
	}

	public void removeReglement(ReglementFournisseur reglement) {
		this.reglements.remove(reglement);
		reglement.setCommandeFournisseur(null);
	}

}