package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="FTGL_FTTJS")
public class Ftxx implements Serializable {
	private static final long serialVersionUID = 1L;
	private int BMBH;
	private String BMLX;
	private String BMMC;
	private String FTKL;
	private String BZ;
	private String SYSPT;

@Id
@Column(name="BMBH",unique = true)
@GenericGenerator(name="bmbhgenerator",strategy = "assigned")
@GeneratedValue(generator = "bmbhgenerator")
public int getBMBH() {
	return BMBH;
}
public void setBMBH(int bMBH) {
	BMBH = bMBH;
}

@Column(name="BMLX",length = 10)
public String getBMLX() {
	return BMLX;
}
public void setBMLX(String bMLX) {
	BMLX = bMLX;
}

@Column(name="BMMC",length = 20)
public String getBMMC() {
	return BMMC;
}
public void setBMMC(String bMMC) {
	BMMC = bMMC;
}

@Column(name="FTKL",length = 20)
public String getFTKL() {
	return FTKL;
}
public void setFTKL(String fTKL) {
	FTKL = fTKL;
}

@Column(name="BZ",length = 20)
public String getBZ() {
	return BZ;
}
public void setBZ(String bZ) {
	BZ = bZ;
}

@Column(name="SYSPT",length = 100)
public String getSYSPT() {
	return SYSPT;
}
public void setSYSPT(String sYSPT) {
	SYSPT = sYSPT;
}


	@Override
	public String toString() {
		return "Ftxx{" +
				"BMBH=" + BMBH +
				", BMLX='" + BMLX + '\'' +
				", BMMC='" + BMMC + '\'' +
				", FTKL='" + FTKL + '\'' +
				", BZ='" + BZ + '\'' +
				", SYSPT='" + SYSPT + '\'' +
				'}';
	}
}
