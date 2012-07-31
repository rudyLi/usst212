package com.dianping.ir.index.servicetest.initDataBase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.dianping.plugins.Dct;
import com.dp.ir.common.conf.Configuration;

/**
 * Hello world!
 * 
 */
public class initDataBase {
	private String luceneDBIp;
	private String luceneDBDatabase;
	private String luceneDBUserName;
	private String luceneDBPassword;

	// private String MySQLMainDBIp;
	// private String MySQLMainDBDatabase;
	// private String MySQLMainDBUserName ;
	// private String MySQLMainDBPassword;
	public initDataBase() {
		String confPath = "Initbase/configuration.xml";
		Configuration conf = new Configuration(confPath);
		conf.init();
		luceneDBIp = conf.getString("Database.LuceneDB.ConnectionIP", null);
		luceneDBDatabase = conf.getString("Database.LuceneDB.databaseName",
				null);
		luceneDBUserName = conf.getString("Database.LuceneDB.UserName", null);
		luceneDBPassword = conf.getString("Database.LuceneDB.Password", null);
		// MySQLMainDBIp = conf.getString(
		// "Database.MySQLMainDB.ConnectionIP", null);
		// MySQLMainDBDatabase = conf.getString(
		// "Database.MySQLMainDB.databaseName", null);
		// MySQLMainDBUserName = conf.getString(
		// "Database.MySQLMainDB.UserName", null);
		// MySQLMainDBPassword = conf.getString(
		// "Database.MySQLMainDB.Password", null);
	}

	public void QasearchInitDatabase() throws IOException {
		// // 全局动态初始化dianping数据库
		// SimpleDateFormat sdf = new SimpleDateFormat("",
		// Locale.SIMPLIFIED_CHINESE);
		// sdf.applyPattern("yyyy-MM-dd");
		// String timeStr = sdf.format(new Date());
		// String AutoFullIndexSQL = "INSERT INTO  MON_JobStatus VALUES ('"
		// + timeStr + "','" + timeStr + " 03:00:01',2)";
		// Dct.sqlexecute(MySQLMainDBIp, MySQLMainDBDatabase,
		// MySQLMainDBUserName,
		// MySQLMainDBPassword, AutoFullIndexSQL);

		// 索引服务器运行状态,LUCENEDB数据库
		String updateIndexServerSQL = "UPDATE SE_IndexRunStatus SET IsRun=0 WHERE CreaterNo='A'";
		Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
				luceneDBPassword, updateIndexServerSQL);

		// // 自动动态索引任务
		// String updateInctaskListSQL =
		// "UPDATE SE_IncTaskList SET STATUS=1 WHERE CreaterNo='A' and AppID="
		// + appId;
		// Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
		// luceneDBPassword, updateInctaskListSQL);
		// String updateRunstatusSQL =
		// "UPDATE SE_IncRunStatus SET IsRun=0 WHERE CreaterNo='A'";
		// Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
		// luceneDBPassword, updateRunstatusSQL);

	}

	public void changeRunstatus() throws IOException {
		String changeStatuseSQL = "UPDATE SE_IndexRunStatus SET IsRun=1 WHERE CreaterNo='A'";
		Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
				luceneDBPassword, changeStatuseSQL);

	}

	public void recoverRunstatus() throws IOException {

		String recoverStatuseSQL = "UPDATE SE_IndexRunStatus SET IsRun=0 WHERE CreaterNo='A'";
		Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
				luceneDBPassword, recoverStatuseSQL);
	}

	public void changeErrorPercent() throws IOException {
		String ErrorPercentSQL = "UPDATE SE_IndexTask SET ErrorPercent=0 where TaskID=293 ";
		Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
				luceneDBPassword, ErrorPercentSQL);
	}

	public void recoverErrorPercent() throws IOException {
		String recoverErrorPercentSQL = "UPDATE SE_IndexTask SET ErrorPercent=10 where TaskID=293 ";
		Dct.sqlexecute(luceneDBIp, luceneDBDatabase, luceneDBUserName,
				luceneDBPassword, recoverErrorPercentSQL);
	}
}
