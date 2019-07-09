package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PUB_XTGL_LOG")
@IdClass(value=LogId.class)
public class XtglLog implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer BH;
    Integer AJXH;
    String AH;
    String YHMC;
    String CZ;
    String SJ;
    String BZ;

    public XtglLog() {
    }

    public XtglLog(Integer BH, Integer AJXH, String AH, String YHMC, String CZ, String SJ, String BZ) {
        this.BH = BH;
        this.AJXH = AJXH;
        this.AH = AH;
        this.YHMC = YHMC;
        this.CZ = CZ;
        this.SJ = SJ;
        this.BZ = BZ;
    }
    @Id
    @Column(name="BH")
    @GenericGenerator(name="bmbhgenerator",strategy = "native")
    @GeneratedValue(generator = "bmbhgenerator")
    public Integer getBH() {
        return BH;
    }

    public void setBH(Integer BH) {
        this.BH = BH;
    }

    @Id
    @Column(name="AJXH")
    public Integer getAJXH() {
        return AJXH;
    }

    public void setAJXH(Integer AJXH) {
        this.AJXH = AJXH;
    }
    @Column(name="AH")
    public String getAH() {
        return AH;
    }

    public void setAH(String AH) {
        this.AH = AH;
    }
    @Column(name="YHMC")
    public String getYHMC() {
        return YHMC;
    }

    public void setYHMC(String YHMC) {
        this.YHMC = YHMC;
    }
    @Column(name="CZ")
    public String getCZ() {
        return CZ;
    }

    public void setCZ(String CZ) {
        this.CZ = CZ;
    }
    @Column(name="SJ")
    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }
    @Column(name="BZ")
    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    @Override
    public String toString() {
        return "XtglLog{" +
                "BH=" + BH +
                ", AJXH=" + AJXH +
                ", AH='" + AH + '\'' +
                ", YHMC='" + YHMC + '\'' +
                ", CZ='" + CZ + '\'' +
                ", SJ='" + SJ + '\'' +
                ", BZ='" + BZ + '\'' +
                '}';
    }
}
