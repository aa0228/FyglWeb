package nju.software.fygl.web.controller;
import nju.software.fygl.Impl.FtxxServiceImpl;
import nju.software.fygl.dynamic.DataSourceRouter;
import nju.software.fygl.entity.Ftxx;
import nju.software.fygl.model.FtxxModel;
import nju.software.fygl.service.FtxxService;
import nju.software.fygl.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class FtxxController {
    Log logger = LogFactory.getLog(FtxxController.class);
    @Resource
    public FtxxServiceImpl ftxsl;

    /**保存新增数据
     * @param ft
     */
    @RequestMapping(value="/insertFtxx.do")
   public String insertFtxx(HttpServletRequest request, HttpServletResponse response,Ftxx ft){
         /*
        切换数据库
         */
        String fydm=(String) request.getSession().getAttribute("fydm");
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
            DataSourceRouter.routerTo(fydm);
        }
        System.out.println(fydm);
        ftxsl.insert(ft);
        List<FtxxModel> list=ftxsl.getFtxxAll();
        System.out.println("新增");
        return "index";
   }
    /**删除数据
     *
     */
    @RequestMapping(value="/deleteFtxx.do")
   public String DeleteFtxx(HttpServletRequest request, HttpServletResponse response){
         /*
        切换数据库
         */
        String fydm=(String) request.getSession().getAttribute("fydm");
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
            DataSourceRouter.routerTo(fydm);
        }
        System.out.println(fydm);
        int bmbh=Integer.parseInt(request.getParameter("BMBH"));
        System.out.println(bmbh);
        ftxsl.delete(bmbh);
   //     List<FtxxModel> list=ftxsl.getFtxxAll();
        System.out.println("删除");
  //      request.setAttribute("list", list);
        JOptionPane.showMessageDialog(null,"删除成功!");
        return "index";
   }

    /**保存新增数据

     */
    @ResponseBody
    @RequestMapping(value="/updateFtxx.do",method = RequestMethod.POST)
   public String updateFtxx(HttpServletRequest request, HttpServletResponse response){
         /*
        切换数据库
         */
        String fydm=(String) request.getSession().getAttribute("fydm");
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
            DataSourceRouter.routerTo(fydm);
        }
        System.out.println(fydm);
        int bmbh=Integer.parseInt(request.getParameter("BH"));
        String bmmc=request.getParameter("BMMC");
        String bz=request.getParameter("BZ");
        System.out.println(bmbh);
        System.out.println(bmmc);
        System.out.println(bz);
        ftxsl.update(bmbh,bmmc,bz);
        System.out.println("更新");
        JOptionPane.showMessageDialog(null,"修改成功!");
        return "index";
   }




   @RequestMapping(value="/showFtxx.do")
   public String showFtxx(HttpServletRequest request, HttpServletResponse response){
        /*
        切换数据库
         */
       String fydm=(String) request.getSession().getAttribute("fydm");
       if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
           DataSourceRouter.routerToDefault();
       }else{
           DataSourceRouter.routerTo(fydm);
       }
       System.out.println(fydm);
        List<FtxxModel> list=ftxsl.getFtxxAll();
        request.setAttribute("list", list);
        return "showall";
   }


}
