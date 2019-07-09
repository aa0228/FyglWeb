package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PUB_AJ_JB")
public class Ajjb  implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer AJXH;
    String AH;

    public Ajjb() {
    }

    public Ajjb(Integer AJXH, String AH) {
        this.AJXH = AJXH;
        this.AH = AH;
    }
    @Id
    @GenericGenerator(name="bmbhgenerator",strategy = "native")
    @GeneratedValue(generator = "bmbhgenerator")
    @Column(name="AJXH",unique = true)
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

    @Override
    public String toString() {
        return "Ajjb{" +
                "AJXH=" + AJXH +
                ", AH='" + AH + '\'' +
                '}';
    }
}
