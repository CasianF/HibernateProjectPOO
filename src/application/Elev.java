package application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "elevi")
public class Elev {

	public Elev() {}

	public Elev(int id, String nume, String prenume, String media_romana, String media_mate, String media_info,
			String media_bio, String media_sport, String media_generala) {
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.media_romana = media_romana;
		this.media_mate = media_mate;
		this.media_info = media_info;
		this.media_bio = media_bio;
		this.media_sport = media_sport;
		this.media_generala = media_generala;
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(name = "nume")
	String nume;

	@Column(name = "prenume")
	String prenume;

	@Column(name = "media_romana")
	String media_romana;

	@Column(name = "media_mate")
	String media_mate;

	@Column(name = "media_info")
	String media_info;

	@Column(name = "media_bio")
	String media_bio;

	@Column(name = "media_sport")
	String media_sport;

	@Column(name = "media_generala")
	String media_generala;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getMedia_romana() {
		return media_romana;
	}

	public void setMedia_romana(String media_romana) {
		this.media_romana = media_romana;
	}

	public String getMedia_mate() {
		return media_mate;
	}

	public void setMedia_mate(String media_mate) {
		this.media_mate = media_mate;
	}

	public String getMedia_info() {
		return media_info;
	}

	public void setMedia_info(String media_info) {
		this.media_info = media_info;
	}

	public String getMedia_bio() {
		return media_bio;
	}

	public void setMedia_bio(String media_bio) {
		this.media_bio = media_bio;
	}

	public String getMedia_sport() {
		return media_sport;
	}

	public void setMedia_sport(String media_sport) {
		this.media_sport = media_sport;
	}

	public String getMedia_generala() {
		return media_generala;
	}

	public void setMedia_generala(String media_generala) {
		this.media_generala = media_generala;
	}
}
