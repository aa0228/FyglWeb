package nju.software.fygl.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class YhModel {

	private String yhbh;
	private String yhmc;
	private String yhdm;
	private String yhkl;

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdlm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getYhkl() {
		return yhkl;
	}

	public void setYhkl(String yhkl) {
		this.yhkl = yhkl;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
