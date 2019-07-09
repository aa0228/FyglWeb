package nju.software.fygl.Impl;

import nju.software.fygl.dao.FtxxDao;
import nju.software.fygl.entity.Ftxx;
import nju.software.fygl.model.FtxxModel;
import nju.software.fygl.service.FtxxService;
import nju.software.fygl.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FtxxServiceImpl implements FtxxService {
    @Autowired
    private  FtxxDao ft;

    @Override
    public List<FtxxModel> getFtxxAll() {
        List<Ftxx> list = ft.getFtxxAll();
        List<FtxxModel> ftmList =new ArrayList<FtxxModel>();
        for(int i=0;i<list.size();i++){
            Ftxx ftxx=list.get(i);
            FtxxModel ftxxmodel=new FtxxModel();
            try{
                MyBeanUtils.copyBean2Bean(ftxxmodel,ftxx);
            }catch(Exception e){
                e.printStackTrace();
            }
            ftmList.add(ftxxmodel);
        }
       return ftmList;
    }

    @Override
    public void insert(Ftxx fx){
         ft.addFtxx(fx);
    }


    @Override
    public void update(int bmbh,String bmmc,String bz) {
         ft.updateFtxxInfo(bmbh,bmmc,bz);
    }

    @Override
    public void delete(int bmbh) {
      ft.deleteFtxxInfo(bmbh);
    }

    @Override
    public Ftxx getFtxxInfo(int bmbh) {
        Ftxx tx=ft.getFtxxInfo(bmbh);
        return tx;
    }
}
