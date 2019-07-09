package nju.software.fygl.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PUB_XTGL_YHB")
public class Yhb implements Serializable {

	private static final long serialVersionUID = 1L;


	private String yhbh;

	private String yhmc;

	private String yhdm;

	private String yhkl;
	
	@Id
	@Column(name = "YHBH", unique = true, nullable = false)
	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	@Column(name = "YHMC", length = 30, nullable = false)
	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	@Column(name = "YHDM", length = 32, nullable = false)
	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	@Column(name = "YHKL", length = 20, nullable = false)
	public String getYhkl() {
		return yhkl;
	}

	public void setYhkl(String yhkl) {
		this.yhkl = yhkl;
	}
}
