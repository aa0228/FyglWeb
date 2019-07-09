package nju.software.fygl.web.controller;

import com.google.gson.Gson;
import nju.software.fygl.Impl.FtxxServiceImpl;
import nju.software.fygl.dao.KtxxDao;
import nju.software.fygl.dynamic.DataSourceMap;
import nju.software.fygl.dynamic.DataSourceRouter;
import nju.software.fygl.dynamic.FYDataSourceEnum;
import nju.software.fygl.entity.Ktxx;
import nju.software.fygl.entity.Statistics;
import nju.software.fygl.entity.Unit;
import nju.software.fygl.entity.UnitAndKtxx;
import nju.software.fygl.model.FtxxModel;
import nju.software.fygl.model.KtxxModel;
import nju.software.fygl.model.PageBean;
import nju.software.fygl.model.YhModel;
import nju.software.fygl.service.FtxxService;
import nju.software.fygl.service.YhService;
import nju.software.fygl.util.DateConvert;
import nju.software.fygl.util.FileOperateUtil;
import nju.software.fygl.util.ResponseBuilder;
import nju.software.fygl.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import nju.software.fygl.service.KtxxService;

@Controller
public class KtxxController {
    Log logger = LogFactory.getLog(KtxxController.class);

    @Resource
    public KtxxService ktsl;
    @Resource
    public KtxxDao ktxd;
    @Resource
    public FtxxService ftxsl;
    @Resource
    private YhService yhService;

  //用于初始化数据的时候，进行数据类型转换，把String类型转为Date类型
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


    /*
    新增界面弹出部门和法庭的下拉列表
     */
    @RequestMapping(value="/selectinsert.do")
    public String selectUnitAndFt(HttpServletRequest request,HttpServletResponse response){
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
        List<FtxxModel> ftlist=ftxsl.getFtxxAll();//得到所有法庭
        request.setAttribute("ftlist", ftlist);
        List<Unit>  bumenlist=ktxd.selectUnit();//得到部门
        request.setAttribute("bumenlist", bumenlist);
        return "NewKt";
    }

    /**
     *
     *接口进入
     */

    @RequestMapping(value = "/logint.do")
    public String check(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,@RequestParam(value="fydm") String fydm) {
        //切换到相应数据库
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
            DataSourceRouter.routerTo(fydm);
        }
        String datasource = DataSourceMap.getDataSourceKey(fydm);
        String fymc= FYDataSourceEnum.getFymcByFydm(fydm);

        YhModel yh = yhService.getYhByYhdm(username);
        request.getSession().setAttribute("username",yh.getYhmc());
        request.getSession().setAttribute("fymc", fymc);
        request.getSession().setAttribute("fydm", fydm);
        return "index";
    }

    /**
     *新增保存
     * @param kt
     * @return
     */
    @RequestMapping(value="/insertKtxx.do")
    public String insertKtxxInfo(HttpServletRequest request, HttpServletResponse response, Ktxx kt) throws ParseException {
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
        String sq1=request.getParameter("SQ_DATE");
        String sq2=request.getParameter("RQ");
        int sj=Integer.parseInt(request.getParameter("FZ"));
        String bumen=request.getParameter("UNIT");
        String fating=request.getParameter("SJFT");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date SQdate = fmt.parse(sq1);
        Date Rq = fmt.parse(sq2);
        Date nel=new Date();
        nel.setTime(Rq.getTime()+(long)sj*60*1000);
        if(ktxd.insertOrNot(fating,Rq,nel)==true)
        {
        ktsl.insertKtxx(kt,SQdate,Rq,bumen);
            JOptionPane.showMessageDialog(null,"新增成功!");
            System.out.println("新增");
            return "index";
        }
        else{
            JOptionPane.showMessageDialog(null,"该法庭已被占用，请重新选择!");

            List<FtxxModel> ftlist=ftxsl.getFtxxAll();//得到所有法庭
            request.setAttribute("ftlist", ftlist);
            List<Unit>  bumenlist=ktxd.selectUnit();//得到部门
            request.setAttribute("bumenlist", bumenlist);
            return "NewKt";
        }

    }

    /**
     *删除数据
     * @return
     */
    @RequestMapping(value="/deleteKtxx.do")
    public String deleteKtxxInfo(HttpServletRequest request, HttpServletResponse response){
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
        String yhmc=(String) request.getSession().getAttribute("username");//得到当前的登录用户名
        int ajxh=Integer.parseInt(request.getParameter("AJXH"));
        int sqbh=Integer.parseInt(request.getParameter("SQBH"));
        String lxren=ktxd.FindLxRen(ajxh,sqbh);
        if(StringUtil.equals(yhmc,lxren)){
            ktsl.deleteKtxx(ajxh,sqbh);
            System.out.println("删除"+sqbh);
            JOptionPane.showMessageDialog(null,"删除成功!");
            Date ba=new Date(System.currentTimeMillis());
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=fmt.format(ba);
            ktxd.insertLogDelete(ajxh,yhmc,time);
        }
       else{
            JOptionPane.showMessageDialog(null,"您不是该条信息的联系人，不能删除！");
        }
        return "index";
    }

    /**
     * 更新数据
     * @param request
     * @return
     * @throws ParseException
     */

    @RequestMapping(value="/updateKtxx.do",method = RequestMethod.POST)
    public String updateKtxxInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException {
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

        int ajxh=Integer.parseInt(request.getParameter("AJXH"));
        int sqbh=Integer.parseInt(request.getParameter("SQBH"));
        System.out.println(ajxh);
        System.out.println(sqbh);
        String yhmc=(String) request.getSession().getAttribute("username");//得到当前的登录用户名
        System.out.println(yhmc);
        String lxren=ktxd.FindLxRen(ajxh,sqbh);
        if(StringUtil.equals(yhmc,lxren)) {
            String unit = request.getParameter("UNIT");
            String dmbh = ktxd.getDMBHbyDMMS(unit);
            int men_count = Integer.parseInt(request.getParameter("MEN_COUNT"));
            String sq_date1 = request.getParameter("SQ_DATE");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sq_date = fmt.parse(sq_date1);
            String yg_men = request.getParameter("YG_MEN");
            String bg_men = request.getParameter("BG_MEN");
            String case_reason = request.getParameter("CASE_REASON");
            String sjft = request.getParameter("SJFT");
            String lx_men = request.getParameter("LX_MEN");
            int fz = Integer.parseInt(request.getParameter("FZ"));
            String rq1 = request.getParameter("RQ");
            Date rq = fmt.parse(rq1);
            ktsl.updateKtxx(ajxh, sqbh, dmbh, men_count, sq_date, yg_men, rq, case_reason, bg_men, sjft, lx_men, fz);
            JOptionPane.showMessageDialog(null, "修改成功!");
            Date ba=new Date(System.currentTimeMillis());
            String time=fmt.format(ba);
            ktxd.insertLogUpdate(ajxh,yhmc,time);
            System.out.println("更新");
        }
        else{
            JOptionPane.showMessageDialog(null,"您不是该条信息的联系人，不能修改！");
        }
        return "index";
    }

    @RequestMapping(value = "/loadInfoDialog.json",method = {RequestMethod.POST,RequestMethod.GET})
    public void loadPxbInfoDialog(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(value = "AJXH", required = false) int ajxh,
                                          @RequestParam(value = "SQBH", required = false) int sqbh)throws Exception
    {

        UnitAndKtxx p=ktxd.findKtinfo(ajxh,sqbh);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(p.getRQ());
        ResponseBuilder builder = new ResponseBuilder();
        builder.writeJsonResponse(response,p);
    }

    /**
     *
     *显示所有数据
     * @return
     */
    @RequestMapping("/findAllKtxxWithPage.do")
    public ModelAndView showKtxxAll(HttpServletRequest request, HttpServletResponse response){
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
        System.out.println("成功");
        String pageNum=request.getParameter("pageNum");
        System.out.println(pageNum);
        if(pageNum==null){
            pageNum="1";
        }
        PageBean<Ktxx> pb=ktsl.findKtxxWithPage(Integer.parseInt(pageNum),12);
        ModelAndView model = new ModelAndView();
        model.addObject("pageBean", pb);
        model.setViewName("showKt");
        return model;
    }

    /**
     *
     *显示筛选后的所有数据
     * @return
     */
    @RequestMapping(value="/showSomeKtxx.do")
    public ModelAndView showSomeKtxxAll(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        /*
        再次重复切换到响应数据库
         */
         String fydm=(String) request.getSession().getAttribute("fydm");
        if(StringUtil.equalsIgnoreWhitespace(fydm,"1111")){
            DataSourceRouter.routerToDefault();
        }else{
            DataSourceRouter.routerTo(fydm);
        }
        System.out.println(fydm);
        String sq_date1 = request.getParameter("startTime");
        String sq_date2 = request.getParameter("endTime");
        String pageNum=request.getParameter("pageNum");
        System.out.println(pageNum);
        if(sq_date1==null&sq_date2==null){
            Calendar c = Calendar.getInstance();//获取当前年份
            int year = c.get(Calendar.YEAR);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date daa=new Date();
            String tll=fmt.format(daa);
            sq_date1=year+"-01-01 00:00:00";
            sq_date2=tll;
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse(sq_date1);
        Date endTime = fmt.parse(sq_date2);
        System.out.println(startTime);
        System.out.println(endTime);
        request.setAttribute("startTime", sq_date1);
        request.setAttribute("endTime", sq_date2);

        if(pageNum==null){
            pageNum="1";
        }
        PageBean<UnitAndKtxx> pb=ktsl.findKtxxWithPageBytime(Integer.parseInt(pageNum),18,startTime,endTime);
        ModelAndView model = new ModelAndView();
        model.addObject("pageBean", pb);

        model.setViewName("showKt");
        if (pb.getList().size()==0) {
            JOptionPane.showMessageDialog(null, "没有记录，请修改检索日期!");
        }
        return model;
    }


    @SuppressWarnings("deprecation")
    private static void myCreateCell(int cellnum, String value, HSSFRow row, HSSFCell cell, HSSFCellStyle style)
    {
        cell = row.createCell((short) cellnum);
        cell.setCellValue(new HSSFRichTextString(value));
        cell.setCellStyle(style);
    }

    public static HSSFCellStyle getStyle(HSSFWorkbook workbook)
    {
        // 设置字体;
        HSSFFont font = workbook.createFont();
        // 设置字体大小;
        font.setFontHeightInPoints((short) 10);
        // 设置字体名字;
        font.setFontName("宋体");
        // font.setItalic(true);
        // font.setStrikeout(true);
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 导出excel
     * @param request
     * @param response
     * @throws ParseException
     */
    @RequestMapping(value="/exportKtxx.do",method = RequestMethod.GET)
    public void exportKtxx(HttpServletRequest request, HttpServletResponse response) throws ParseException {
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
        String sq_date1 = request.getParameter("startTime");
        String sq_date2 = request.getParameter("endTime");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse(sq_date1);
        Date endTime = fmt.parse(sq_date2);
      //  List<KtxxModel> ktList = ktsl.getSomeKtxxAll(startTime, endTime);
        List<UnitAndKtxx> ktList=ktsl.getSomeKtxx(startTime,endTime);
        System.out.println(ktList.size());
        request.setAttribute("ktList", ktList);
        request.setAttribute("startTime", sq_date1);
        request.setAttribute("endTime", sq_date2);
        if (ktList.size() == 0) {
            JOptionPane.showMessageDialog(null, "没有记录，请修改检索日期!");
        } else {
            String resourcePath =null;
            try{
                resourcePath =KtxxController.class.getResource("/").toURI().getPath();
            }catch(URISyntaxException e1){
                e1.printStackTrace();
            }
            File inputfile = new File(resourcePath + "template.xls");
            File outputfile = new File(System.currentTimeMillis() + ".xls");
            try {
                FileOutputStream os = new FileOutputStream(outputfile);
                HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputfile)); // 读取excel模板
                HSSFSheet sheet = workbook.getSheetAt(0); // 读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                row = sheet.getRow(0);
                String titleStr = " 法庭使用情况登记表";
                /* 设置标题 */
                HSSFCellStyle style1 = getStyle(workbook);
                style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
                style1.setBorderLeft(HSSFCellStyle.BORDER_NONE);// 左边框
                style1.setBorderTop(HSSFCellStyle.BORDER_NONE);// 上边框
                style1.setBorderRight(HSSFCellStyle.BORDER_NONE);// 右边框
                HSSFFont f = workbook.createFont();
                f.setFontName("宋体");
                f.setFontHeightInPoints((short) 16);// 字号
                f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                style1.setFont(f);
                myCreateCell(0, titleStr, row, cell, style1);
                int rownum = 2; // 添加的起始行
                HSSFCellStyle style = getStyle(workbook);
                style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
                style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

                for(UnitAndKtxx model : ktList) {
                    row = sheet.getRow(rownum);
                    if(row == null) {
                        sheet.createRow(rownum);
                        row = sheet.getRow(rownum);
                    }
                    row.setHeight((short) (25 * 15));
                    myCreateCell(0, model.getDMMS()+ "", row, cell, style);
                    myCreateCell(1, model.getYG_MEN() + "", row, cell, style);
                    myCreateCell(2, model.getCASE_REASON() + "", row, cell, style);
                    myCreateCell(3, model.getBG_MEN() + "", row, cell, style);
                    myCreateCell(4, "是", row, cell, style);
                    myCreateCell(5, model.getLX_MEN() + "", row, cell, style);
                    myCreateCell(6, model.getSQ_DATE() + "", row, cell, style);
                    myCreateCell(7, model.getRQ()+ "", row, cell, style);
                //    myCreateCell(8, model.getSJTJS()+ "", row, cell, style);
                    myCreateCell(8, model.getSJFT() + "", row, cell, style);
                    myCreateCell(9, model.getFZ() + "", row, cell, style);
                    rownum++;
                }
                workbook.write(os);
                os.flush();
                os.close();

                FileOperateUtil.download(request, response, outputfile.getAbsolutePath(), "",
                        "法庭使用情况表" + System.currentTimeMillis() + ".xls");
                File tmpFile = new File(outputfile.getAbsolutePath());
                if(tmpFile.exists()) {
                    System.out.println("临时文件：" + tmpFile.getAbsolutePath() + "删除成功");
                    tmpFile.delete();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 生成开庭公告
     *
     */

    @RequestMapping(value="/noticeKtxx.do")
    public void generateNoticeKtxx(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
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
        String sq_date1 = request.getParameter("startTime");
        String sq_date2 = request.getParameter("endTime");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse(sq_date1);
        Date endTime = fmt.parse(sq_date2);
        List<KtxxModel> ktList = ktsl.getSomeKtxxAll(startTime, endTime);

        DateConvert dac=new DateConvert();
        Date ba=new Date(System.currentTimeMillis());
        String nal=dac.Dateconverter(ba);
    //    System.out.println(nal);


        if (ktList.size() == 0) {
            JOptionPane.showMessageDialog(null, "没有记录，请修改检索日期!");
        } else {
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream outputstream = null;
            BufferedOutputStream buffoutputstream=null;
            String txt_name="开庭公告"+System.currentTimeMillis()+".txt";
            try {
                response.reset();//清空输出流
                response.setContentType("text/plain;charset=utf-8");
                //设置txt文件名称编码，防止中文乱码
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(txt_name, "UTF-8"));
                StringBuffer write = new StringBuffer();
                outputstream = response.getOutputStream();
                buffoutputstream = new BufferedOutputStream(outputstream);
                String content = "";
                Date currentDate = new Date(System.currentTimeMillis());
                String st=dac.DateconverterRq(currentDate);


                for (int i = 0; i <ktList.size(); i++) {
                    content = "\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"开庭公告" + "\r\n";
                    write.append(content);
                    String resul=dac.Dateconverter(ktList.get(i).getRQ());
                    String sqft=" ";
                    String bg_gao=" ";
                    String yg_gao=" ";
                    String case_reason=" ";


                    if(StringUtil.isNotEmpty(ktList.get(i).getSQFT())||StringUtil.isNotBlank(ktList.get(i).getSQFT())){
                        sqft= ktList.get(i).getSQFT().toString();
                    }
                    if(StringUtil.isNotEmpty( ktList.get(i).getBG_MEN())||StringUtil.isNotBlank( ktList.get(i).getBG_MEN())){
                        bg_gao=ktList.get(i).getBG_MEN().toString();
                    }
                    if(StringUtil.isNotEmpty( ktList.get(i).getYG_MEN())||StringUtil.isNotBlank( ktList.get(i).getYG_MEN())){
                        yg_gao=ktList.get(i).getYG_MEN().toString();
                    }
                    if(StringUtil.isNotEmpty( ktList.get(i).getCASE_REASON())||StringUtil.isNotBlank( ktList.get(i).getCASE_REASON())){
                        case_reason=ktList.get(i).getCASE_REASON().toString();
                    }
                    content = "\t\n"+"法院定于" + resul+ "在" + sqft + "公开开庭审理" + bg_gao + "及" +yg_gao + "关于" + case_reason + "一案" + "\r\n";
                    write.append(content);
                    content = "\t\n"+"特此公告" + "\r\n";
                    write.append(content);
                    content = "\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+"\t\n"+st +"\r\n";
                    write.append(content);
                }
                String str = new String(write.toString().getBytes(),"gbk");
                buffoutputstream.write(str.toString().getBytes("gbk"));
                buffoutputstream.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if (outputstream != null)
                    try {
                        outputstream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (buffoutputstream != null)
                    try {
                        buffoutputstream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }

    /**
     *
     *返回主页面
     */
    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
    /**
     *
     *依据部门统计法庭的使用次数
     */
    @RequestMapping(value="/StatisticsData.do")
    public String countByYear(HttpServletRequest request, HttpServletResponse response) throws ParseException {
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
        String years = request.getParameter("year");
        request.setAttribute("year", years);
        String sq_date1 = years+"-01-01";
        String sq_date2 = years+"-12-31";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = fmt.parse(sq_date1);
        Date endTime = fmt.parse(sq_date2);
        List<Statistics> st=ktsl.getByYear(startTime,endTime);
        request.setAttribute("st", st);
        if (st.size()==0) {
            JOptionPane.showMessageDialog(null, "没有记录，请修改检索年份!");
        }
        return "statistics";
    }

}
