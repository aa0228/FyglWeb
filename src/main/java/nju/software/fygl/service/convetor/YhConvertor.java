package nju.software.fygl.service.convetor;

import java.util.ArrayList;
import java.util.List;

import nju.software.fygl.model.YhModel;
import nju.software.fygl.entity.Yhb;
import nju.software.fygl.util.MyBeanUtils;

public class YhConvertor {

	public static YhModel doConvertorModel(Yhb yh) {
		YhModel user = new YhModel();
		try {
			MyBeanUtils.copyBean2Bean(user, yh);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	

	public static List<YhModel> dosConvertorModels(List<Yhb> yhList) {
		List<YhModel> userList = new ArrayList<YhModel>();
		for (int i = 0; i < yhList.size(); i++) {
			Yhb yh = yhList.get(i);
			userList.add(doConvertorModel(yh));
		}
		return userList;
	}

}
