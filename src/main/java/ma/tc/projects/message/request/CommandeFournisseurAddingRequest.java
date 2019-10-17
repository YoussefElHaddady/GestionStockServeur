package ma.tc.projects.message.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import ma.tc.projects.entity.CommandeFournisseur;
import ma.tc.projects.entity.LigneCmdFournisseur;

public class CommandeFournisseurAddingRequest extends CommandeFournisseur{
   
    @NotBlank
    private long idMagasin;
    
    @NotBlank
    private List<LigneCmdFournisseur> lignesCmdFournisseur;

	public CommandeFournisseurAddingRequest(@NotBlank long idMagasin, @NotBlank List<LigneCmdFournisseur> lignesCmdFournisseur) {
		super();
		this.idMagasin = idMagasin;
		this.lignesCmdFournisseur = lignesCmdFournisseur;
	}

	public long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public List<LigneCmdFournisseur> getLignesCmdFournisseur() {
		return lignesCmdFournisseur;
	}

	public void setLignesCmdFournisseur(List<LigneCmdFournisseur> lignesCmdFournisseur) {
		this.lignesCmdFournisseur = lignesCmdFournisseur;
	}

	

}