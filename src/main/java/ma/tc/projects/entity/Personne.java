package ma.tc.projects.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Personne implements Serializable {

	private String CIN;
	private String name;
	// Les coordonnées bancaires de la personne
	private int RIP;

	@Column(length = 10)
	private String phone;
	private String address;

	private String email;
	private String picture;

	public Personne() {
		super();
	}

	public Personne(String CIN, String name, int RIP, String phone, String adresse, String email, String picture) {
		super();
		this.CIN = CIN;
		this.name = name;
		this.RIP = RIP;
		this.phone = phone;
		this.address = adresse;
		this.email = email;
		this.picture = picture;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String CIN) {
		this.CIN = CIN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRIP() {
		return RIP;
	}

	public void setRIP(int RIP) {
		this.RIP = RIP;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}