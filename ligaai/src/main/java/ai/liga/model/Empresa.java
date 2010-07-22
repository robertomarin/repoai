package ai.liga.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empresa {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Funcionario> funcionarios;

	public Empresa() {

	}

	public Empresa(final Long id) {
		this.id = id;
	}

	public void addFuncionario(final Funcionario funcionario) {
		if (this.getFuncionarios() == null) {
			this.setFuncionarios(new ArrayList<Funcionario>());
		}

		this.getFuncionarios().add(funcionario);
	}

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

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(final Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(final List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!this.getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Empresa other = (Empresa) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}
	
}
