package nju.software.fygl.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FtxxModel {
   private String FYDM;
    private int BMBH;
    private String BMLX;
    private String BMMC;
    private String FTKL;
    private String BZ;
    private String SYSPT;

    public String getFYDM() {
        return FYDM;
    }

    public void setFYDM(String FYDM) {
        this.FYDM = FYDM;
    }

    public int getBMBH() {
        return BMBH;
    }

    public void setBMBH(int BMBH) {
        this.BMBH = BMBH;
    }

    public String getBMLX() {
        return BMLX;
    }

    public void setBMLX(String BMLX) {
        this.BMLX = BMLX;
    }

    public String getBMMC() {
        return BMMC;
    }

    public void setBMMC(String BMMC) {
        this.BMMC = BMMC;
    }

    public String getFTKL() {
        return FTKL;
    }

    public void setFTKL(String FTKL) {
        this.FTKL = FTKL;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getSYSPT() {
        return SYSPT;
    }

    public void setSYSPT(String SYSPT) {
        this.SYSPT = SYSPT;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
