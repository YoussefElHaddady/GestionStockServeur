package ma.tc.projects.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ouvriers")
public class Ouvrier extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOuvrier;

	@NotNull
	private double salaire;
	
	private double avance;
	
	@JsonIgnoreProperties("ouvrier")
	@OneToMany(mappedBy = "ouvrier", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Absence> absences;

	public Ouvrier() {

	}

	public Ouvrier(double salaire, List<Absence> absences) {
		super();
		this.salaire = salaire;
		this.absences = absences;
	}

	public Ouvrier(String CIN, String name, int RIP, String phone, String adresse, String email, String picture,
			double salaire, List<Absence> absences) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.salaire = salaire;
		this.absences = absences;
	}

	public long getIdOuvrier() {
		return idOuvrier;
	}

	public void setIdOuvrier(long idOuvrier) {
		this.idOuvrier = idOuvrier;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public void decreaseAvanceAtAbsenceSign() {
		double a = this.avance - (this.salaire / 30);
		this.avance = Math.floor(a * 100) / 100;
	}
	
	public void decreaseAvanceBy(double montant) {
		double a = this.avance - montant;
		this.avance = Math.floor(a * 100) / 100;
	}

	public void addAbsence(Absence absence) {
		this.absences.add(absence);
		absence.setOuvrier(this);
	}

	public void removeAbsence(Absence absence) {
		this.absences.remove(absence);
		absence.setOuvrier(null);
	}

}