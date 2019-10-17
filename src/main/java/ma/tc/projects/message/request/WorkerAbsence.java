package ma.tc.projects.message.request;


import javax.validation.constraints.NotBlank;
 
public class WorkerAbsence {
    @NotBlank
    private long idOuvrier;
 
    @NotBlank
    private int month;

	public long getIdOuvrier() {
		return idOuvrier;
	}

	public void setIdOuvrier(long idOuvrier) {
		this.idOuvrier = idOuvrier;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	
}