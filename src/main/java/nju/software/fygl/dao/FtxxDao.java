package nju.software.fygl.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.hibernate.HibernateUtil;
import nju.software.fygl.entity.Ftxx;
import nju.software.fygl.util.CollectionUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class FtxxDao extends HibernateDaoSupport {

	private static final Logger log = LoggerFactory.getLogger(FtxxDao.class);
//得到对应法院的所有法庭
	public List<Ftxx> getFtxxAll() {
		List<Ftxx> result1 = new ArrayList<Ftxx>();
		String sql = "from Ftxx ";
		result1 = getHibernateTemplate().find(sql);
		if (null == result1 || result1.isEmpty()) {
			return null;
		}
		return result1;
	}

    //得到某一法庭
		public Ftxx getFtxxInfo (int bmbh){
			List<Ftxx> result1 = new ArrayList<Ftxx>();
			String sql = "from Ftxx where BMBH = ?";
			result1 = getHibernateTemplate().find(sql,bmbh);
			if (null == result1 || result1.isEmpty()) {
				return null;
			}
			return result1.get(0);
		}




      //删除相应的法庭
		public boolean deleteFtxxInfo(int bmbh){
			Ftxx ft = getFtxxInfo(bmbh);
			if (ft == null) {
				return false;
			}
			try {
				getHibernateTemplate().delete(ft);
				getHibernateTemplate().flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
	/**
	 */
		public boolean updateFtxxInfo(int bmbh,String bmmc,String bz){
			Ftxx ft = getFtxxInfo(bmbh);
			ft.setBMMC(bmmc);
			ft.setBZ(bz);
			System.out.println(ft);
			try {
			    this.getHibernateTemplate().update(ft);
			    getHibernateTemplate().flush();
				System.out.println(ft);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

	/**
	 * 新增法院信息
	 * @param ft
	 * @return
	 */
       public boolean addFtxx(Ftxx ft){
		   try {
		    	int Id=GetmaxId();
		    	ft.setBMBH(Id);
			   getHibernateTemplate().save(ft);
			   getHibernateTemplate().flush();

		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		   return false;
	   }

	/**
	 * 得到最大主键,并生成新主键
	 *
	 */
	public int  GetmaxId(){
		List<Ftxx> result1 = new ArrayList<Ftxx>();
		String sql = "from Ftxx where BMBH=(select max(BMBH) from Ftxx)";
		result1=getHibernateTemplate().find(sql);
		int maxId = result1.get(0).getBMBH();
		maxId=maxId+1;
		return maxId;
	}
}
