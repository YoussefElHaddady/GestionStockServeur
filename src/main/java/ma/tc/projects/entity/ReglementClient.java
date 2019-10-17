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
import javax.validation.constraints.NotNull;

import ma.tc.projects.enums.ModeReglementEnum;

@Entity
@Table(name = "reglementsClient")
public class ReglementClient extends Reglement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReglement;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cmd_client", nullable = false)
	private CommandeClient commandeClient;


	public ReglementClient() {
		super();
	}
	
	public ReglementClient(long idReglement, CommandeClient commandeClient) {
		super();
		this.idReglement = idReglement;
		this.commandeClient = commandeClient;
	}

	public ReglementClient(@NotNull Date dateReglement, @NotNull ModeReglementEnum mode, @NotNull double montant,
			CommandeClient commandeClient) {
		super(dateReglement, mode, montant);
		this.commandeClient = commandeClient;
	}

	public long getIdReglement() {
		return idReglement;
	}

	public void setIdReglement(long idReglement) {
		this.idReglement = idReglement;
	}

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

	/*
	 * In this child entity (of CammadeClient entity) we implement the equals and
	 * hashCode methods. Since we cannot rely on a natural identifier for equality
	 * checks, we need to use the entity identifier instead. However, we need to do
	 * it properly so that equality is consistent across all entity state
	 * transitions. Because we rely on equality for the removeReglement, (it’s good
	 * practice to override equals and hashCode for the child entity in a
	 * bidirectional association).
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idReglement ^ (idReglement >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ReglementClient))
			return false;
		ReglementClient other = (ReglementClient) obj;
		if (idReglement != other.idReglement)
			return false;
		return true;
	}

}