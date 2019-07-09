package nju.software.fygl.entity;


import java.util.Date;

public class UnitAndKtxx {
    //案件序号
    private int AJXH;
    //申请编号
    private int SQBH;
    //代码描述-部门名称
    private String DMMS;
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


    public int getAJXH() {
        return AJXH;
    }

    public void setAJXH(int AJXH) {
        this.AJXH = AJXH;
    }

    public int getSQBH() {
        return SQBH;
    }

    public void setSQBH(int SQBH) {
        this.SQBH = SQBH;
    }


    public Integer getMEN_COUNT() {
        return MEN_COUNT;
    }

    public void setMEN_COUNT(Integer MEN_COUNT) {
        this.MEN_COUNT = MEN_COUNT;
    }

    public String getCASE_REASON() {
        return CASE_REASON;
    }

    public void setCASE_REASON(String CASE_REASON) {
        this.CASE_REASON = CASE_REASON;
    }

    public String getBG_MEN() {
        return BG_MEN;
    }

    public void setBG_MEN(String BG_MEN) {
        this.BG_MEN = BG_MEN;
    }

    public String getYG_MEN() {
        return YG_MEN;
    }

    public void setYG_MEN(String YG_MEN) {
        this.YG_MEN = YG_MEN;
    }

    public String getLX_MEN() {
        return LX_MEN;
    }

    public void setLX_MEN(String LX_MEN) {
        this.LX_MEN = LX_MEN;
    }

    public String getSFAP() {
        return SFAP;
    }

    public void setSFAP(String SFAP) {
        this.SFAP = SFAP;
    }

    public Date getSQ_DATE() {
        return SQ_DATE;
    }

    public void setSQ_DATE(Date SQ_DATE) {
        this.SQ_DATE = SQ_DATE;
    }

    public Date getRQ() {
        return RQ;
    }

    public void setRQ(Date RQ) {
        this.RQ = RQ;
    }

    public String getSQFT() {
        return SQFT;
    }

    public void setSQFT(String SQFT) {
        this.SQFT = SQFT;
    }

    public String getSJFT() {
        return SJFT;
    }

    public void setSJFT(String SJFT) {
        this.SJFT = SJFT;
    }

    public String getSJTJS() {
        return SJTJS;
    }

    public void setSJTJS(String SJTJS) {
        this.SJTJS = SJTJS;
    }

    public Integer getFZ() {
        return FZ;
    }

    public void setFZ(Integer FZ) {
        this.FZ = FZ;
    }
    public String getDMMS() {
        return DMMS;
    }

    public void setDMMS(String DMMS) {
        this.DMMS = DMMS;
    }

    public UnitAndKtxx() {
    }

    public UnitAndKtxx(int AJXH, int SQBH, String DMMS, Integer MEN_COUNT, String CASE_REASON, String BG_MEN, String YG_MEN, String LX_MEN, String SFAP, Date SQ_DATE, Date RQ, String SQFT, String SJFT, String SJTJS, Integer FZ) {
        this.AJXH = AJXH;
        this.SQBH = SQBH;
        this.DMMS = DMMS;
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
}
