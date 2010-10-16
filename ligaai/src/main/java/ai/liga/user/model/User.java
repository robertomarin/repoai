package ai.liga.user.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import ai.liga.util.Constants;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@XStreamAlias(Constants.USER)
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false, updatable = false, length = 30)
	@NotNull(message = "E-mail deve ser preenchido")
	@Size(min = 3, max = 30, message = "E-mail deve ter de 3 a 30 caracteres")
	private String email;

	@Column(length = 32)
	@Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
	@NotNull(message = "Senha deve ser preenchida")
	@XStreamOmitField()
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@Null
	@XStreamOmitField
	private Calendar created;

	@Column(nullable = false, length = 30)
	@Size(min = 2, max = 30, message = "O nome deve ter de 2 a 30 caracteres")
	private String name;

	@Column(columnDefinition = "bit default 0")
	private boolean avatar;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAvatar(boolean avatar) {
		this.avatar = avatar;
	}

	public boolean isAvatar() {
		return avatar;
	}

}
