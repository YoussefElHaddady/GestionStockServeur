package ma.tc.projects.message.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import ma.tc.projects.entity.CommandeClient;
import ma.tc.projects.entity.LigneCmdClient;

public class CommandeClientAddingRequest extends CommandeClient{
   
    @NotBlank
    private long idMagasin;
    
    @NotBlank
    private List<LigneCmdClient> lignesCmdClient;

	public CommandeClientAddingRequest(@NotBlank long idMagasin, @NotBlank List<LigneCmdClient> lignesCmdClient) {
		super();
		this.idMagasin = idMagasin;
		this.lignesCmdClient = lignesCmdClient;
	}

	public long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public List<LigneCmdClient> getLignesCmdClient() {
		return lignesCmdClient;
	}

	public void setLignesCmdClient(List<LigneCmdClient> lignesCmdClient) {
		this.lignesCmdClient = lignesCmdClient;
	}

	

}