package ai.liga.ligaai.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false, updatable = false, length = 30)
	@NotNull
	@Size(min = 3, max = 30)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Calendar created;

	@Column(length = 32)
	@Size(min = 6, max = 20)
	@NotNull
	private String password;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
