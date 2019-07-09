package nju.software.fygl.util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {

    /*
          验证输入的日期格式，提取相关的数字
           */
    public String Dateconverter(Date a){
        long longTime1 = a.getTime();
        Timestamp timestamp1 = new Timestamp(longTime1);
        SimpleDateFormat ft=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String s=ft.format(timestamp1);
        int year=s.indexOf("年");
        String subS1=s.substring(0,year);
        int month=s.indexOf("月");
        String subS2=s.substring(month-1,month);
        int day=s.indexOf("日");
        String subS3=s.substring(day-1,day);
        int hour=s.indexOf("时");
        String subS4=s.substring(hour-1,hour);
        int minute=s.indexOf("分");
        String subS5=s.substring(minute-2,minute);
        String result1=NumToChineseSpecial(subS1)+"年";
        String result2=NumToChineseSpecial(subS2)+"月";
        String result3=NumToChineseSpecial(subS3)+"日";
        String result4=NumToChineseSpecial(subS4)+"时";
        String result5=NumToChineseSpecial(subS5);
        if(result5!=""){
             result5+="分";
        }
        else{
            result5=NumToChineseSpecial(subS5);
        }
        String result=result1+result2+result3+result4+result5;
        return result;
    }

    /**
     * 年月日的转化
     * @param a
     * @return
     */
    public String DateconverterRq(Date a){
        long longTime1 = a.getTime();
        Timestamp timestamp1 = new Timestamp(longTime1);
        SimpleDateFormat ft=new SimpleDateFormat("yyyy年MM月dd日");
        String s=ft.format(timestamp1);
        int year=s.indexOf("年");
        String subS1=s.substring(0,year);
        int month=s.indexOf("月");
        String subS2=s.substring(month-1,month);
        int day=s.indexOf("日");
        String subS3=s.substring(day-1,day);
        String result1=NumToChineseSpecial(subS1)+"年";
        String result2=NumToChineseSpecial(subS2)+"月";
        String result3=NumToChineseSpecial(subS3)+"日";
        String result=result1+result2+result3;
        return result;
    }
    /*
    将单个数字转化成中文
     */
    public String NumToChinese(String s){
        String[] sl={"0","一","二","三","四","五","六","七","八","九"};
        String vq="十";
        String result="";
        for(int i=0;i<s.length();i++){
            result+=sl[Integer.parseInt(String.valueOf(s.charAt(i)))];
        }
        return result;
    }

      /*
    对特殊情况进行处理
     */
      public String NumToChineseSpecial(String s){
          String[] sl={"零","一","二","三","四","五","六","七","八","九"};
          String vq="十";
          String result="";
          if(s.length()==2){
               if(s.charAt(0)=='0'&&s.charAt(1)=='0'){
                      return "";
               }
              else if(s.charAt(0)=='1'){
                  if(s.charAt(1)=='0')
                      return vq;
                  else{
                      return vq+sl[Integer.parseInt(String.valueOf(s.charAt(1)))];
                  }
              }
               else if(s.charAt(1)=='0'){
                  return sl[Integer.parseInt(String.valueOf(s.charAt(0)))]+vq;}
               else{
                  return sl[Integer.parseInt(String.valueOf(s.charAt(0)))]+vq+sl[Integer.parseInt(String.valueOf(s.charAt(1)))];
              }
          }
          return NumToChinese(s);
      }

}
