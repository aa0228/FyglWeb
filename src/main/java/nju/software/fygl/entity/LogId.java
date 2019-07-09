package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LogId implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer BH;
    Integer AJXH;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogId)) return false;
        LogId logId = (LogId) o;
        return Objects.equals(getBH(), logId.getBH()) &&
                Objects.equals(getAJXH(), logId.getAJXH());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBH(), getAJXH());
    }

    public LogId() {
    }

    public LogId(Integer BH, Integer AJXH) {
        this.BH = BH;
        this.AJXH = AJXH;
    }
}
