package ma.tc.projects.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "types_de_charges")
public class TypeDeCharge implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTypeCharge;

	@NotNull
	private String libele;

	public TypeDeCharge() {

	}

	public TypeDeCharge(long idTypeCharge, @NotNull String libele) {
		super();
		this.idTypeCharge = idTypeCharge;
		this.libele = libele;
	}

	public long getIdTypeCharge() {
		return idTypeCharge;
	}

	public void setIdTypeCharge(long idTypeCharge) {
		this.idTypeCharge = idTypeCharge;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

}