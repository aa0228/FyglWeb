package nju.software.fygl.Impl;

import nju.software.fygl.entity.Ktxx;
import nju.software.fygl.entity.Statistics;
import nju.software.fygl.entity.UnitAndKtxx;
import nju.software.fygl.model.KtxxModel;
import nju.software.fygl.service.KtxxService;
import nju.software.fygl.dao.KtxxDao;
import nju.software.fygl.model.PageBean;
import nju.software.fygl.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KtxxServiceImpl implements KtxxService {
    @Autowired
   private KtxxDao ktd;

    @Override
    public PageBean<Ktxx> findKtxxWithPage(int pageNum,int pageSize) {
        List<Ktxx> list = ktd.getKtxxAll();
        int totalRecord=list.size()-1;
        PageBean<Ktxx> pd=new PageBean<Ktxx>(pageNum,pageSize,totalRecord);
        int startIndex=pd.getStartIndex();
        pd.setList(ktd.findKtxx(startIndex,pageSize));
        return pd;
    }
 /*
    @Override
    public PageBean<Ktxx> findKtxxWithPageBytime(int pageNum,int pageSize,Date startdate,Date enddate){
        List<Ktxx> list=ktd.getKtxxSomeInfo(startdate,enddate);
        int totalRecord=list.size();
        PageBean<Ktxx> pd=new PageBean<Ktxx>(pageNum,pageSize,totalRecord);
        int startIndex=pd.getStartIndex();
        pd.setList(ktd.findKtxxBytime(startIndex,pageSize,startdate,enddate));
        return pd;
    }
    */
   @Override
   public PageBean<UnitAndKtxx> findKtxxWithPageBytime(int pageNum, int pageSize, Date startdate, Date enddate) {
       List<UnitAndKtxx> list=ktd.findKtxxBytimeAll(startdate, enddate);
  //     List<Ktxx> list = ktd.getKtxxSomeInfo(startdate, enddate);
       int totalRecord = list.size();
       PageBean<UnitAndKtxx> pd = new PageBean<UnitAndKtxx>(pageNum, pageSize, totalRecord);
       int startIndex = pd.getStartIndex();
       pd.setList(ktd.findKtxxBytime(startIndex, pageSize, startdate, enddate));
       return pd;
   }
    @Override
    public List<UnitAndKtxx> getSomeKtxx(Date startdate, Date enddate) {
        List<UnitAndKtxx> list=ktd.findKtxxBytimeAll(startdate,enddate);
        return list;
    }

    @Override
    public List<Statistics> getByYear(Date start,Date end) {
        List<Statistics> list=ktd.countByYear(start,end);
        return list;
    }

    @Override
    public Ktxx getKtxxInfo(int ajxh,int sqbh){
        Ktxx kt=ktd.getKtxxInfo(ajxh,sqbh);
        return kt;
    }


    @Override
    public List<KtxxModel> getKtxxAll() {
        List<Ktxx> list = ktd.getKtxxAll();
        List<KtxxModel> ktmList=new ArrayList<KtxxModel>();
        for(int i=0;i<list.size();i++){
            Ktxx ktxx=list.get(i);
            KtxxModel ktxxmodel=new KtxxModel();
            try{
                MyBeanUtils.copyBean2Bean(ktxxmodel,ktxx);
            }catch(Exception e){
                e.printStackTrace();
            }
            ktmList.add(ktxxmodel);
        }
        return ktmList;
    }

    @Override
    public List<KtxxModel> getSomeKtxxAll(Date startdate, Date enddate) {
        List<Ktxx> list=ktd.getKtxxSomeInfo(startdate,enddate);
        List<KtxxModel> ktmList=new ArrayList<KtxxModel>();
        for(int i=0;i<list.size();i++){
            Ktxx ktxx=list.get(i);
            KtxxModel ktxxmodel=new KtxxModel();
            try{
                MyBeanUtils.copyBean2Bean(ktxxmodel,ktxx);
            }catch(Exception e){
                e.printStackTrace();
            }
            ktmList.add(ktxxmodel);
        }
        return ktmList;
    }





    @Override
    public void deleteKtxx(int ajxh,int sqbh) {
       ktd.deleteKtxxInfo(ajxh,sqbh);

    }

    @Override
    public void updateKtxx(int ajxh,int sqbh, String unit, int men_count, Date sq_date, String yg_men, Date rq, String case_reason, String bg_men, String sjft, String lx_men, int fz) {
        ktd.updateKtxxInfo(ajxh,sqbh,unit,men_count,sq_date,yg_men,rq, case_reason, bg_men,sjft,lx_men,fz);
    }

    @Override
    public void insertKtxx(Ktxx kt,Date d1,Date d2,String bumen) {
        ktd.addKtxx(kt,d1,d2,bumen);
    }









}
