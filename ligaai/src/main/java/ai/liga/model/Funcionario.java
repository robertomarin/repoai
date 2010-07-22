package ai.liga.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(final Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
