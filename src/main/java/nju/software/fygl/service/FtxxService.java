package nju.software.fygl.service;

import java.util.List;

import nju.software.fygl.entity.Ftxx;
import nju.software.fygl.model.FtxxModel;


public interface FtxxService {
	public List<FtxxModel> getFtxxAll();
	public void insert(Ftxx fx);
	public void update(int bmbh,String bmmc,String bz);
	public void delete(int bmbh);
	public Ftxx getFtxxInfo(int bmbh);
}
