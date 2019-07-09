package nju.software.fygl.Impl;


import nju.software.fygl.dao.YhbDao;
import nju.software.fygl.model.YhModel;
import nju.software.fygl.entity.Yhb;
import nju.software.fygl.service.YhService;
import nju.software.fygl.service.convetor.YhConvertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YhServiceImpl implements YhService {
	@Autowired
	private YhbDao yhbDao;

	public boolean checkIn(YhModel Yh, String password) {
		if (Yh.getYhkl().equals(password.trim())) {
			return true;
		}
		return false;
	}

	public YhModel getYhByYhdm(String yhdm) {
		Yhb yhb = yhbDao.getYhbByYhdm(yhdm);
		if (yhb == null) {
			return null;
		}
		YhModel Yh = YhConvertor.doConvertorModel(yhb);
		return Yh;
	}

}
