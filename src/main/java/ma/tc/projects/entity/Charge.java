package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ma.tc.projects.enums.EtatDeCharge;

@Entity
@Table(name = "charges")
public class Charge implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCharge;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateCharge;

	@Enumerated(EnumType.STRING)
	private EtatDeCharge etat;
	private double montant;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_typeDeCharge", nullable = false)
	private TypeDeCharge typeDeCharge;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_user", nullable = false)
	private AppUser user;

	public Charge() {

	}

	public Charge(long idCharge, @NotNull Date dateCharge, EtatDeCharge etat, double montant,
			TypeDeCharge typeDeCharge) {
		super();
		this.idCharge = idCharge;
		this.dateCharge = dateCharge;
		this.etat = etat;
		this.montant = montant;
		this.typeDeCharge = typeDeCharge;
	}

	public long getIdCharge() {
		return idCharge;
	}

	public void setIdCharge(long idCharge) {
		this.idCharge = idCharge;
	}

	public Date getDateCharge() {
		return dateCharge;
	}

	public void setDateCharge(Date dateCharge) {
		this.dateCharge = dateCharge;
	}

	public EtatDeCharge getEtat() {
		return etat;
	}

	public void setEtat(EtatDeCharge etat) {
		this.etat = etat;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public TypeDeCharge getTypeDeCharge() {
		return typeDeCharge;
	}

	public void setTypeDeCharge(TypeDeCharge typeDeCharge) {
		this.typeDeCharge = typeDeCharge;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

}
