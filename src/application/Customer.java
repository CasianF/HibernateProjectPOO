package application;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	public Customer() {
	}

	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	int id;

	@Column(name = "username", nullable = false)
	String username;

	@Column(name = "email")
	String email;

	@Column(name = "password")
	String password;

	@OneToMany(targetEntity = Elev.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "prof_id")
	public List<Elev> elevi = new ArrayList<>();

	public List<Elev> getElevi() {
		return elevi;
	}

	public void setElevi(List<Elev> elevi) {
		this.elevi = elevi;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}