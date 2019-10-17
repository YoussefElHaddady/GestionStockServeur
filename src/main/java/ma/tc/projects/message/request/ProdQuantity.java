package ma.tc.projects.message.request;


import javax.validation.constraints.NotBlank;
 
public class ProdQuantity {
    @NotBlank
    private long idMagasin;
 
    @NotBlank
    private long idCategorie;

	public long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}

	
}