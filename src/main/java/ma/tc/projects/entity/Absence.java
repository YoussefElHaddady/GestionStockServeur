package ma.tc.projects.entity;

import java.util.Date;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "absences")
public class Absence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAbsence;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateAbsence;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "absences" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_ouvrier", nullable = false)
	private Ouvrier ouvrier;
	
	
	public Absence() {
	}
	
	public Absence(long idAbsence, @NotNull Date dateAbsence) {
		this.idAbsence = idAbsence;
		this.dateAbsence = dateAbsence;
	}

	public Absence(@NotNull Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(long idAbsence) {
		this.idAbsence = idAbsence;
	}

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public Ouvrier getOuvrier() {
		return ouvrier;
	}

	public void setOuvrier(Ouvrier ouvrier) {
		this.ouvrier = ouvrier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAbsence ^ (idAbsence >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Absence))
			return false;
		Absence other = (Absence) obj;
		if (idAbsence != other.idAbsence)
			return false;
		return true;
	}
	
}
