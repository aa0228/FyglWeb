package nju.software.fygl.service;

import java.util.Date;
import java.util.List;

import nju.software.fygl.entity.Ktxx;
import nju.software.fygl.entity.Statistics;
import nju.software.fygl.entity.UnitAndKtxx;
import nju.software.fygl.model.KtxxModel;
import nju.software.fygl.model.PageBean;


public interface KtxxService {
	 public PageBean<Ktxx> findKtxxWithPage(int pageNum,int pageSize);
	// public PageBean<Ktxx> findKtxxWithPageBytime(int pageNum,int pageSize,Date startdate,Date enddate);
	 public PageBean<UnitAndKtxx> findKtxxWithPageBytime(int pageNum, int pageSize, Date startdate, Date enddate);
	 public List<KtxxModel> getKtxxAll();
	 public Ktxx getKtxxInfo(int ajxh,int sqbh);
	 public void deleteKtxx(int ajxh,int sqbh);
	 public void updateKtxx(int ajxh,int sqbh, String unit, int men_count, Date sq_date, String yg_men, Date rq, String case_reason, String bg_men, String sqft, String lx_men, int fz);
	 public void insertKtxx(Ktxx kt,Date d1,Date d2,String bumen);
	 public List<KtxxModel> getSomeKtxxAll(Date startdate,Date enddate);
	 public List<UnitAndKtxx> getSomeKtxx(Date startdate, Date enddate);
	 public List<Statistics> getByYear(Date start,Date end);
}