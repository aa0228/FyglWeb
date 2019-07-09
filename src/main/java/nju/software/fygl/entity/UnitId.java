package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.Objects;

public class UnitId  implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    //类别编号
    private String LBBH;
    //代码编号-部门代号
    private String DMBH;

    public UnitId() {
    }

    public UnitId(String LBBH, String DMBH) {
        this.LBBH = LBBH;
        this.DMBH = DMBH;
    }

   @Column(name="LBBH")
    public String getLBBH() {
        return LBBH;
    }

    public void setLBBH(String LBBH) {
        this.LBBH = LBBH;
    }
    @Column(name="DMBH")
    @GenericGenerator(name="dmbhgenerator",strategy = "native")
    @GeneratedValue(generator = "dmbhgenerator")
    public String getDMBH() {
        return DMBH;
    }

    public void setDMBH(String DMBH) {
        this.DMBH = DMBH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitId)) return false;
        UnitId unitId = (UnitId) o;
        return Objects.equals(getLBBH(), unitId.getLBBH()) &&
                Objects.equals(getDMBH(), unitId.getDMBH());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLBBH(), getDMBH());
    }

    @Override
    public String toString() {
        return "UnitId{" +
                "LBBH='" + LBBH + '\'' +
                ", DMBH='" + DMBH + '\'' +
                '}';
    }
}
