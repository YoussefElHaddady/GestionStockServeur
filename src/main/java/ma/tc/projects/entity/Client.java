package ma.tc.projects.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import ma.tc.projects.enums.TypeClientEnum;

@Entity
@Table(name = "clients")
public class Client extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idClient;

	private String raison_sociale;

	@Enumerated(EnumType.STRING)
	@NonNull
	private TypeClientEnum type;

	public Client() {

	}

	public Client(String raison_sociale, TypeClientEnum type) {
		super();
		this.raison_sociale = raison_sociale;
		this.type = type;
	}

	public Client(String CIN, String name, int RIP, String phone, String adresse, String email, String picture,
			String raison_sociale, TypeClientEnum type) {
		super(CIN, name, RIP, phone, adresse, email, picture);
		this.raison_sociale = raison_sociale;
		this.type = type;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getRaison_sociale() {
		return raison_sociale;
	}

	public void setRaison_sociale(String raison_sociale) {
		this.raison_sociale = raison_sociale;
	}

	public TypeClientEnum getType() {
		return type;
	}

	public void setType(TypeClientEnum type) {
		this.type = type;
	}

}