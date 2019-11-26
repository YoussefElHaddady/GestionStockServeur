package ma.tc.projects.entity;

import java.io.Serializable;
import java.util.Date;

public class DetailProduit implements Serializable{

	private long idMagasin;
	private Date dateDetailProduit;
	private double prixAchat;
	private double prixVente;
	private int quantite;

	public DetailProduit() {
		super();
	}

	public DetailProduit(long idMagasin, Date dateDetailProduit, double prixAchat, double prixVente, int quantite) {
		super();
		this.idMagasin = idMagasin;
		this.dateDetailProduit = dateDetailProduit;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.quantite = quantite;
	}

	public long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public Date getDateDetailProduit() {
		return dateDetailProduit;
	}

	public void setDateDetailProduit(Date dateDetailProduit) {
		this.dateDetailProduit = dateDetailProduit;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}