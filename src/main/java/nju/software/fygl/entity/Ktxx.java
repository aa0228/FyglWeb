package nju.software.fygl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoupeipei
 *
 */
@Entity
@Table(name="FTNQ_FTSYSQDJ")
@IdClass(value=KtxxId.class)
public class Ktxx implements Serializable{
	private static final long serialVersionUID = 1L;
	//案件序号
	private Integer AJXH;
	//申请编号
	private Integer SQBH;

//	@EmbeddedId
//	private KtxxId ktxxId;
	//部门编号
	private String UNIT;
	//开庭人数
	private Integer MEN_COUNT;
	//案由
	private String CASE_REASON;
	//被告
	private String BG_MEN;
	//原告
	private String YG_MEN;
	//联系人
	private String LX_MEN;
	//是否安排
	private String SFAP;
	//申请开庭日期
	private Date SQ_DATE;
	//开庭日期
	private Date RQ;
	//申请法庭
	private String SQFT;
	//实际法庭
	private String SJFT;
	//实际调解室
	private String SJTJS;
	//分钟数
	private Integer FZ;

	@Id
    @Column(name="AJXH")
	public int getAJXH() {
		return AJXH;
	}
	public void setAJXH(int aJXH) {
		AJXH = aJXH;
	}
	@Id
	@Column(name="SQBH")
	@GenericGenerator(name="bmbhgenerator",strategy = "native")
	@GeneratedValue(generator = "bmbhgenerator")
	public int getSQBH() {
		return SQBH;
	}
	public void setSQBH(int sQBH) {
		SQBH = sQBH;
	}
	@Column(name="UNIT")
	public String getUNIT() {
		return UNIT;
	}
	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}
	@Column(name="MEN_COUNT")
	public Integer getMEN_COUNT() {
		return MEN_COUNT;
	}
	public void setMEN_COUNT(Integer mEN_COUNT) {
		MEN_COUNT = mEN_COUNT;
	}
	@Column(name="CASE_REASON")
	public String getCASE_REASON() {
		return CASE_REASON;
	}
	public void setCASE_REASON(String cASE_REASON) {
		CASE_REASON = cASE_REASON;
	}
	@Column(name="BG_MEN")
	public String getBG_MEN() {
		return BG_MEN;
	}
	public void setBG_MEN(String bG_MEN) {
		BG_MEN = bG_MEN;
	}
	@Column(name="YG_MEN")
	public String getYG_MEN() {
		return YG_MEN;
	}
	public void setYG_MEN(String yG_MEN) {
		YG_MEN = yG_MEN;
	}
	@Column(name="LX_MEN")
	public String getLX_MEN() {
		return LX_MEN;
	}
	public void setLX_MEN(String lX_MEN) {
		LX_MEN = lX_MEN;
	}
	@Column(name="SFAP",columnDefinition="String default '是'")
	public String getSFAP() {
		return SFAP;
	}
	public void setSFAP(String sFAP) {
		SFAP = sFAP;
	}
	@Column(name="SQ_DATE")
	public Date getSQ_DATE() {
		return SQ_DATE;
	}
	public void setSQ_DATE(Date sQ_DATE) {
		SQ_DATE = sQ_DATE;
	}
	@Column(name="RQ")
	public Date getRQ() {
		return RQ;
	}
	public void setRQ(Date rQ) {
		RQ = rQ;
	}
	@Column(name="SJFT")
	public String getSJFT() {
		return SJFT;
	}
	public void setSJFT(String sJFT) {
		SJFT = sJFT;
	}
	@Column(name="SQFT")
	public String getSQFT() {
		return SQFT;
	}

	public void setSQFT(String SQFT) {
		this.SQFT = SQFT;
	}
	@Column(name="SJTJS")
	public String getSJTJS() {
		return SJTJS;
	}

	public void setSJTJS(String SJTJS) {
		this.SJTJS = SJTJS;
	}
	@Column(name="FZ")
	public Integer getFZ() {
		return FZ;
	}

	public void setFZ(Integer FZ) {
		this.FZ = FZ;
	}

	public Ktxx() {
		// TODO Auto-generated constructor stub
	}

	public Ktxx( String UNIT, int MEN_COUNT, String CASE_REASON, String BG_MEN, String YG_MEN, String LX_MEN, String SFAP, Date SQ_DATE, Date RQ, String SQFT, String SJFT, String SJTJS, int FZ) {
//		this.AJXH = AJXH;
//		this.SQBH = SQBH;
		this.UNIT = UNIT;
		this.MEN_COUNT = MEN_COUNT;
		this.CASE_REASON = CASE_REASON;
		this.BG_MEN = BG_MEN;
		this.YG_MEN = YG_MEN;
		this.LX_MEN = LX_MEN;
		this.SFAP = SFAP;
		this.SQ_DATE = SQ_DATE;
		this.RQ = RQ;
		this.SQFT = SQFT;
		this.SJFT = SJFT;
		this.SJTJS = SJTJS;
		this.FZ = FZ;
	}

	@Override
	public String toString() {
		return "Ktxx{" +
				", UNIT='" + UNIT + '\'' +
				", MEN_COUNT=" + MEN_COUNT +
				", CASE_REASON='" + CASE_REASON + '\'' +
				", BG_MEN='" + BG_MEN + '\'' +
				", YG_MEN='" + YG_MEN + '\'' +
				", LX_MEN='" + LX_MEN + '\'' +
				", SFAP='" + SFAP + '\'' +
				", SQ_DATE=" + SQ_DATE +
				", RQ=" + RQ +
				", SQFT='" + SQFT + '\'' +
				", SJFT='" + SJFT + '\'' +
				", SJTJS='" + SJTJS + '\'' +
				", FZ=" + FZ +
				'}';
	}

//    public KtxxId getKtxxId() {
//        return ktxxId;
//    }
//
//    public void setKtxxId(KtxxId ktxxId) {
//        this.ktxxId = ktxxId;
//    }
}
