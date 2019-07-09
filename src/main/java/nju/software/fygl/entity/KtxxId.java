package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

public class KtxxId implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    //案件序号
    private Integer AJXH;
    //申请编号
    private Integer SQBH;

    public KtxxId() {
    }

    public KtxxId(Integer AJXH, Integer SQBH) {
        this.AJXH = AJXH;
        this.SQBH = SQBH;
    }

    @Override
    public String toString() {
        return "KtxxId{" +
                "AJXH=" + AJXH +
                ", SQBH=" + SQBH +
                '}';
    }

    @Column(name="AJXH")
    public int getAJXH() {
        return AJXH;
    }
    public void setAJXH(int aJXH) {
        AJXH = aJXH;
    }
    @Column(name="SQBH")
    @GenericGenerator(name="bmbhgenerator",strategy = "native")
    @GeneratedValue(generator = "bmbhgenerator")
    public int getSQBH() {
        return SQBH;
    }
    public void setSQBH(int sQBH) {
        SQBH = sQBH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KtxxId)) return false;
        KtxxId ktxxId = (KtxxId) o;
        return AJXH == ktxxId.AJXH &&
                SQBH == ktxxId.SQBH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AJXH, SQBH);
    }
}
