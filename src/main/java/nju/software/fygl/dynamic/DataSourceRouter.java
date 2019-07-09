package nju.software.fygl.dynamic;

import org.apache.log4j.Logger;

import com.mysql.jdbc.log.Log;

import nju.software.fygl.util.StringUtil;

public class DataSourceRouter {
	public static Logger log = Logger.getLogger(DataSourceRouter.class);
	/**
	 * 切换到指定Fydm数据库 CustomerContextHolder.getCustomerType()获取的是source 传入的可能是fydm 也可能是source
	 * @param fydm
	 */
	public static void routerTo(String fydm) {
		if(CustomerContextHolder.getCustomerType() == null ||
				(!CustomerContextHolder.getCustomerType().equals(DataSourceMap.getDataSourceKey(fydm)) &&
						!CustomerContextHolder.getCustomerType().equals(fydm))){
			//传入的fydm可能是：fydm-120000200 source-Default
			String datasource =DataSourceMap.getDataSourceKey(fydm);
			String datafydm = DataSourceEnum.getFydmBySource(fydm);
			if(!StringUtil.isBlank(datasource)) {
				CustomerContextHolder.setCustomerType(datasource);
				log.info("切换法院数据库至："+ datasource);
			}
			else if(!StringUtil.isBlank(datafydm)) {
				CustomerContextHolder.setCustomerType(fydm);
				log.info("切换法院数据库至："+ fydm);
			}
			else{
				log.debug("切换法院数据库失败，法院代码或者法院数据源为："+fydm);
			}
		}
	}


	public static void routerToDefault() {
		//切换回本院数据源
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.DEFAULT.getFydm()));
		log.info("切换至默认数据库default");
	}

	public static void routerToTjgy() {
		//切换回本院数据源
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGY.getFydm()));
		log.info("切换至天津高院数据库tjgy");
	}

	/**
	 * 切换到高院集中库
	 */
	public static void routerToJzk() {
		//切换回高院集中库
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYJZK.getFydm()));
		log.info("切换至天津高院集中库jzk");
	}

	/**
	 * 切换到高院集中融合库
	 */
	public static void routerToJzrh() {
		//切换回高院集中库
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYJZRH.getFydm()));
		log.info("切换至天津高院集中融合库jzrh");
	}

	/**
	 * 切换到高院院长决策集中库
	 */
	public static void routerToYzjcJzk() {
		//切换回高院集中库
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYYZJCJZK.getFydm()));
		log.info("切换至天津高院院长决策库yzjc");
	}

	/**
	 * 切换到高院信访库
	 */
	public static void routerToXfk() {
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYXFK.getFydm()));
		log.info("切换至天津高院信访库gyxf");
	}

	/**
	 * 切换到人事库
	 */
	public static void routerToFyrs(){
		//切换到系统人事库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.FYRS.getFydm()));
		log.info("切换至法院人事库fyrs");
	}

	/**
	 * 切换到陪审员库
	 */
	public static void routerToPsy(){
		//切换到陪审员人事库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.PSY.getFydm()));
		log.info("切换至人民陪审员人事库rmpsyrs");
	}

	/**
	 * 切换到司法统计库
	 */
	public static void routerToSftj(){
		//切换到系统司法统计库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.SFTJ.getFydm()));
		log.info("切换至司法统计库sftj");
	}
}
