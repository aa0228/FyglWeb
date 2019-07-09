package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="PUB_DMB")
@IdClass(value=UnitId.class)
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;
    //类别编号
    private String LBBH;
    //代码编号-部门代号
    private String DMBH;
    //代码描述-部门名称
    private String DMMS;

    @Id
   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="LBBH")
    public String getLBBH() {
        return LBBH;
    }

    public void setLBBH(String LBBH) {
        this.LBBH = LBBH;
    }
    @Id
    @Column(name="DMBH")
    @GenericGenerator(name="dmbhgenerator",strategy = "native")
    @GeneratedValue(generator = "dmbhgenerator")
    public String getDMBH() {
        return DMBH;
    }

    public void setDMBH(String DMBH) {
        this.DMBH = DMBH;
    }
    @Column(name="DMMS")
    public String getDMMS() {
        return DMMS;
    }

    public void setDMMS(String DMMS) {
        this.DMMS = DMMS;
    }

    public Unit() {
    }

    public Unit(String LBBH, String DMBH, String DMMS) {
        this.LBBH = LBBH;
        this.DMBH = DMBH;
        this.DMMS = DMMS;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "LBBH='" + LBBH + '\'' +
                ", DMBH='" + DMBH + '\'' +
                ", DMMS='" + DMMS + '\'' +
                '}';
    }
}
