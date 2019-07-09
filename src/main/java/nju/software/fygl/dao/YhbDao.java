package nju.software.fygl.dao;
import java.util.ArrayList;
import java.util.List;
import nju.software.fygl.entity.Yhb;
import nju.software.fygl.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class YhbDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(YhbDao.class);

	public Yhb getYhInfo(String yhdm, String yhkl) {
		List<Yhb> result = new ArrayList<Yhb>();
		String sql = "from Yhb where yhdm= '" + yhdm + " ' and yhkl= '" + yhkl + "'";
		result = getHibernateTemplate().find(sql);
		if (null == result || result.isEmpty())
			return null;
		return result.get(0);
	}


	public List<Yhb> getAllYh() {
		List<Yhb> result = new ArrayList<Yhb>();
		String sql = "from Yhb";
		result = getHibernateTemplate().find(sql);
		if (null == result || result.isEmpty()) {
			return null;
		}
		return result;
	}


	public boolean deleteYhByYhbh(String yhbh) {
		Yhb yh = getYhByYhbh(yhbh);
		if (yh == null) {
			return false;
		}
		try {
			getHibernateTemplate().delete(yh);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	public String addYh(Yhb yh) {
		try {
			String yhbh = (String) getHibernateTemplate().save(yh);
			return yhbh;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}


	public Yhb getYhByYhbh(String yhbh) {
		List<Yhb> yhList = new ArrayList<Yhb>();
		String sql = "from Yhb where yhbh = ?";
		try {
			yhList = getHibernateTemplate().find(sql, yhbh);
			return yhList.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean updateYhb(Yhb yhb) {
		if (yhb == null) {
			return false;
		}
		try {
			getHibernateTemplate().merge(yhb);
		} catch (Exception e) {
			return false;
		}
		getSession().flush();
		return true;
	}


	public Yhb getYhbByYhdm(String yhdm) {
		String hql = "from Yhb where yhdm=?";
		List<Yhb> yhbList = getHibernateTemplate().find(hql, yhdm);
		if (CollectionUtil.listIsNull(yhbList)) {
			return null;
		}
		return yhbList.get(0);
	}

}
