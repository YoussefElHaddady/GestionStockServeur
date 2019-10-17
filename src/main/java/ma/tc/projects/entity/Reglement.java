package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import ma.tc.projects.enums.ModeReglementEnum;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Reglement implements Serializable {

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateReglement;

	@Enumerated(EnumType.STRING)
	@NotNull
	private ModeReglementEnum mode;
	@NotNull
	private double montant;

	public Reglement() {

	}

	public Reglement(@NotNull Date dateReglement, @NotNull ModeReglementEnum mode, @NotNull double montant) {
		super();
		this.dateReglement = dateReglement;
		this.mode = mode;
		this.montant = montant;
	}
	
	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	public ModeReglementEnum getMode() {
		return mode;
	}

	public void setMode(ModeReglementEnum mode) {
		this.mode = mode;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}