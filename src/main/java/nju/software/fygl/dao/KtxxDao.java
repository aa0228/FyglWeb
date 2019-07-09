package nju.software.fygl.dao;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import nju.software.fygl.entity.*;
import nju.software.fygl.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class KtxxDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FtxxDao.class);

    /**
     *分页查询信息
     * @return
     */
    public List<Ktxx> findKtxx(int startIndex,int pageSize) {
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createQuery("from Ktxx").setFirstResult(startIndex).setMaxResults(pageSize);
        List<Ktxx> result1 = query.list();
        session.flush();
        session.close();
        return result1;
    }

    public List<UnitAndKtxx> findKtxxBytime(int startIndex, int pageSize, Date startdate, Date enddate) {
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        long longTime1 = startdate.getTime();
        Timestamp timestamp1 = new Timestamp(longTime1);
        long longTime2 = enddate.getTime();
        Timestamp timestamp2 = new Timestamp(longTime2);
        String sql="select new nju.software.fygl.entity.UnitAndKtxx(u.AJXH,u.SQBH,g.DMMS,u.MEN_COUNT, u.CASE_REASON,u.BG_MEN, u.YG_MEN,u.LX_MEN, u.SFAP,u.SQ_DATE,u.RQ,u.SQFT,u.SJFT,u.SJTJS,u.FZ)from Ktxx u, Unit g where u.UNIT=g.DMBH and g.LBBH='USR206-99'and u.SQ_DATE>=:timestamp1 and u.SQ_DATE<=:timestamp2";
      //  String sql="from Ktxx where SQ_DATE>=:timestamp1 and SQ_DATE<=:timestamp2";
        Query query=session.createQuery(sql).setFirstResult(startIndex).setMaxResults(pageSize);
        query.setParameter("timestamp1",timestamp1).setParameter("timestamp2",timestamp2);
        List<UnitAndKtxx> result1 = query.list();
        session.flush();
        session.close();
        return result1;
    }

    /**
     *
     * 不分页时，导出的EXCEL
     */
    public List<UnitAndKtxx> findKtxxBytimeAll(Date startdate, Date enddate) {
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        long longTime1 = startdate.getTime();
        Timestamp timestamp1 = new Timestamp(longTime1);
        long longTime2 = enddate.getTime();
        Timestamp timestamp2 = new Timestamp(longTime2);
        String sql="select new nju.software.fygl.entity.UnitAndKtxx(u.AJXH,u.SQBH,g.DMMS,u.MEN_COUNT, u.CASE_REASON,u.BG_MEN, u.YG_MEN,u.LX_MEN, u.SFAP,u.SQ_DATE,u.RQ,u.SQFT,u.SJFT,u.SJTJS,u.FZ)from Ktxx u, Unit g where u.UNIT=g.DMBH and g.LBBH='USR206-99'and u.SQ_DATE>=:timestamp1 and u.SQ_DATE<=:timestamp2";
        Query query=session.createQuery(sql);
        query.setParameter("timestamp1",timestamp1).setParameter("timestamp2",timestamp2);
        List<UnitAndKtxx> result1 = query.list();
        session.flush();
        session.close();
        return result1;
    }

    public UnitAndKtxx findKtinfo(int ajbh,int sqbh){
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        String sql="select new nju.software.fygl.entity.UnitAndKtxx(u.AJXH,u.SQBH,g.DMMS,u.MEN_COUNT, u.CASE_REASON,u.BG_MEN, u.YG_MEN,u.LX_MEN, u.SFAP,u.SQ_DATE,u.RQ,u.SQFT,u.SJFT,u.SJTJS,u.FZ)from Ktxx u, Unit g where u.UNIT=g.DMBH and g.LBBH='USR206-99'and u.AJXH=:stamp1 and u.SQBH=:stamp2";
        Query query=session.createQuery(sql);
        query.setParameter("stamp1",ajbh).setParameter("stamp2",sqbh);
        List<UnitAndKtxx> result1 = query.list();
        session.flush();
        session.close();
        return result1.get(0);
    }




    public List<Statistics> countByYear(Date start,Date end){
        Session session=getSessionFactory().openSession();
        session.beginTransaction();
        String sql="select g.DMMS," +
                "SUM(case when month(u.SQ_DATE) =1 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =2 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =3 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =4 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =5 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =6 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =7 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =8 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =9 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =10 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =11 then u.SQBH else 0 end)," +
                "SUM(case when month(u.SQ_DATE) =12 then u.SQBH else 0 end)from Ktxx u, Unit g where u.UNIT=g.DMBH and g.LBBH='USR206-99'and u.SQ_DATE>=:timestamp1 and u.SQ_DATE<=:timestamp2 group by g.DMMS";
        Query query=session.createQuery(sql);
        query.setParameter("timestamp1",start).setParameter("timestamp2",end);
        List<Object[]> result1 = query.list();
        List<Statistics> lidata=new ArrayList<Statistics>();
        for(int i=0;i<result1.size();i++){
            Object[] tempObj = result1.get(i);
            for(int j=0;j<tempObj.length;j++){
                System.out.print(tempObj[j]);
            }
            System.out.println(" ");
            Statistics ne=new Statistics();
            ne.setDMMS(tempObj[0].toString());
            ne.setJAN(Integer.parseInt(tempObj[1].toString()));
            ne.setFEB(Integer.parseInt(tempObj[2].toString()));
            ne.setMAR(Integer.parseInt(tempObj[3].toString()));
            ne.setAPR(Integer.parseInt(tempObj[4].toString()));
            ne.setMAY(Integer.parseInt(tempObj[5].toString()));
            ne.setJUN(Integer.parseInt(tempObj[6].toString()));
            ne.setJULY(Integer.parseInt(tempObj[7].toString()));
            ne.setAUG(Integer.parseInt(tempObj[8].toString()));
            ne.setSEP(Integer.parseInt(tempObj[9].toString()));
            ne.setOCT(Integer.parseInt(tempObj[10].toString()));
            ne.setNOV(Integer.parseInt(tempObj[11].toString()));
            ne.setDEC(Integer.parseInt(tempObj[12].toString()));
            lidata.add(ne);
            System.out.println(lidata.size());
        }
        System.out.println("统计");
        System.out.println(result1.size());
        session.flush();
        session.close();
        return lidata;
    }


/*
    在新增中，根据输入的部门，得到部门编号插入到开庭信息表中
 */
    public String getDMBHbyDMMS(String dmms){
        List<Unit> result1 = new ArrayList<Unit>();
        String sql = "from Unit where LBBH=? and DMMS =?";
        result1 =getHibernateTemplate().find(sql,"USR206-99",dmms);
        if(null==result1 ||result1.isEmpty())
        {
            return null;
        }
        String dmbh=result1.get(0).getDMBH();
        return dmbh;
    }

    /**得到对应所有信息
     *
     * @return
     */
     public List<Ktxx> getKtxxAll(){
        List<Ktxx> result1=new ArrayList<Ktxx>();
        String sql="from Ktxx";
         result1 =getHibernateTemplate().find(sql);
         return result1;
     }


    /**
     * 得到某一条信息
     * @param sqbh
     * @return
     */
    public Ktxx getKtxxInfo(int ajbh,int sqbh){
    List<Ktxx> result1 = new ArrayList<Ktxx>();
    String sql = "from Ktxx where AJXH=? and SQBH =?";
    result1 =getHibernateTemplate().find(sql,ajbh,sqbh);
    if(null==result1 ||result1.isEmpty())
     {
        return null;
      }
			return result1.get(0);
      }

    /**
     * 得到最大主键,并生成新主键
     *
     */
    public int  getmaxId(){
        List<Ktxx> result1 = new ArrayList<Ktxx>();
        String sql = "from Ktxx where SQBH=(select max(SQBH) from Ktxx where AJXH=0)";
        result1=getHibernateTemplate().find(sql);
        //int maxid=result1.get(0).getKtxxId().getSQBH();
        int maxid = result1.get(0).getSQBH();
        maxid=maxid+1;
        return maxid;
    }

    /**
     * 得到所需n条信息
     * @param startdate
     * @param enddate
     * @return
     */
    public List<Ktxx> getKtxxSomeInfo(Date startdate,Date enddate){
        List<Ktxx> result1 = new ArrayList<Ktxx>();
        String sql = "from Ktxx where SQ_DATE>=? and SQ_DATE<=?";
        long longTime1 = startdate.getTime();
        Timestamp timestamp1 = new Timestamp(longTime1);
        long longTime2 = enddate.getTime();
        Timestamp timestamp2 = new Timestamp(longTime2);
        result1 =getHibernateTemplate().find(sql,timestamp1,timestamp2);
        if(null==result1 ||result1.isEmpty())
        {
            return null;
        }
        return result1;
    }


    /**删除相应的消息
     *
     * @param sqbh
     * @return
     */
    public boolean deleteKtxxInfo(int ajbh,int sqbh){
        Ktxx kt = getKtxxInfo(ajbh,sqbh);
        if (kt == null) {
            return false;
        }
        try {
            getHibernateTemplate().delete(kt);
            getHibernateTemplate().flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
     }

    /**
     * 更新某一条信息
     * @param sqbh
     * @param unit
     * @param men_count
     * @param sq_date
     * @param yg_men
     * @param rq
     * @param case_reason
     * @param bg_men
     * @param sjft
     * @param lx_men
     * @param fz
     * @return
     */
     public boolean updateKtxxInfo(int ajxh,int sqbh, String unit, int men_count, Date sq_date,String yg_men,Date rq,String case_reason,String bg_men,String sjft,String lx_men,int fz){
         Ktxx kt = getKtxxInfo(ajxh,sqbh);
         kt.setBG_MEN(bg_men);
         kt.setCASE_REASON(case_reason);
         kt.setLX_MEN(lx_men);
         kt.setMEN_COUNT(men_count);
         kt.setSQ_DATE(sq_date);
         kt.setYG_MEN(yg_men);
         kt.setUNIT(unit);
         kt.setSQFT(sjft);
         kt.setSJFT(sjft);
         kt.setFZ(fz);
         kt.setRQ(rq);
         return true;
     }

    /**
     * 查询某段时间的那个法庭被使用了
     */
     public boolean insertOrNot(String fating,Date d1,Date d2){
         long longTime1 = d1.getTime();
         Timestamp timestamp1 = new Timestamp(longTime1);
         long longTime2 = d2.getTime();
         Timestamp timestamp2 = new Timestamp(longTime2);
         List<Ktxx> result1 = new ArrayList<Ktxx>();
         String sql = "from Ktxx where RQ>=? and RQ<=?";
         result1=getHibernateTemplate().find(sql,timestamp1,timestamp2);
         System.out.println(timestamp1);
         System.out.println(timestamp2);
         if(result1.size()==0){
             return true;
         }
         else{
             for(int i=0;i<result1.size();i++){
                 String sf=result1.get(i).getSJFT();
                 System.out.println(sf);
                 if(StringUtil.equalsIgnoreWhitespace(sf,fating)){
                     return false;
                 }
             }
             return true;
         }
     }

    /**
     * 查询所有的部门
     */
    public List<Unit> selectUnit(){
        List<Unit> result1 = new ArrayList<Unit>();
        String sql = "from Unit where LBBH=?";
        result1=getHibernateTemplate().find(sql,"USR206-99" );
        return result1;
    }



    /**
     * 新增某一条信息
     * @param kt
     * @return
     */
    public boolean addKtxx(Ktxx kt,Date d1,Date d2,String bumen){
     try {
        kt.setSQFT(kt.getSJFT());
        KtxxId ktxxId = new KtxxId();
        ktxxId.setAJXH(0);
        int max=getmaxId();
        ktxxId.setSQBH(max);

    //   kt.setKtxxId(ktxxId);
        String unit=getDMBHbyDMMS(bumen);
        kt.setSFAP("Y");
        kt.setUNIT(unit);
        kt.setSQBH(max);
        kt.setAJXH(0);
        kt.setSQBH(max);
        kt.setSQ_DATE(d1);
        kt.setRQ(d2);
        getHibernateTemplate().save(kt);
        getHibernateTemplate().flush();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    }

   /*
     查询联系人
    */
    public String FindLxRen(int ajxh,int sqbh){
        List<Ktxx> lx=new ArrayList<>();
        String sql="from Ktxx where AJXH=? and SQBH =?";
        lx=getHibernateTemplate().find(sql,ajxh,sqbh);
        return lx.get(0).getLX_MEN();
    }

    /*
       查询AH的内容
      */
    public String FindAh(int ajxh){
        List<Ajjb> aj=new ArrayList<>();
        String sql="from Ajjb where AJXH=?";
        aj=getHibernateTemplate().find(sql,ajxh);
        String ah="null";
        if(aj.size()==0){
            return ah;
        }
        else{
            return aj.get(0).getAH();
        }

    }


    /**
     * 得到日志最大主键,并生成新主键
     *
     */
    public int  getLogmaxId(int ajxh){
        List<XtglLog> result1 = new ArrayList<>();
        String sql = "from XtglLog where BH=(select max(BH) from XtglLog where AJXH=?)and AJXH=?";
        result1=getHibernateTemplate().find(sql,ajxh,ajxh);
        int maxid=0;
        if(result1.size()==0){
             maxid=1;
        }
        else{
             maxid = result1.get(0).getBH();
             maxid=maxid+1;
        }
        return maxid;
    }

    /**
     * 修改后加入日志
     */
    public void insertLogUpdate(int ajxh,String yhmc,String time){
        XtglLog xl=new XtglLog();
        int maxid=getLogmaxId(ajxh);
        xl.setBH(maxid);
        xl.setAJXH(ajxh);
        xl.setAH(FindAh(ajxh));
        xl.setYHMC(yhmc);
        xl.setCZ("修改信息");
        xl.setSJ(time);
        xl.setBZ(yhmc+"在"+time+"修改了信息");
        try {
            this.getHibernateTemplate().save(xl);
            getHibernateTemplate().flush();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    /**
     * 删除后加入日志
     */
    public void insertLogDelete(int ajxh,String yhmc,String time){
        XtglLog xl=new XtglLog();
        int maxid=getLogmaxId(ajxh);
        xl.setBH(maxid);
        xl.setAJXH(ajxh);
        xl.setAH(FindAh(ajxh));
        xl.setYHMC(yhmc);
        xl.setCZ("删除信息");
        xl.setSJ(time);
        xl.setBZ(yhmc+"在"+time+"删除了信息");
        try {
            this.getHibernateTemplate().save(xl);
            getHibernateTemplate().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
