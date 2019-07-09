package nju.software.fygl.service;

import nju.software.fygl.model.YhModel;

public interface YhService {
	public boolean checkIn(YhModel Yh, String password);

	public YhModel getYhByYhdm(String yhdm);
}

