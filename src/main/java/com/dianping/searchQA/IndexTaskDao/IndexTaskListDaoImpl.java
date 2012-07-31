package com.dianping.searchQA.IndexTaskDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.dp.ir.common.bean.IndexTask;

/**
 *功能说明：
 * 
 * @author：
 *@version：20122012-7-23下午01:10:18
 */
public class IndexTaskListDaoImpl extends SimpleJdbcDaoSupport implements
		IndexTaskListDao {
	private static final String INDEXTASK_SELECT = "select * from SE_IndexTask where TaskID=?";

	public IndexTask getTask(int taskID) {
		// TODO Auto-generated method stub
		List<IndexTask> indexTasks = getSimpleJdbcTemplate().query(
				INDEXTASK_SELECT, new ParameterizedRowMapper<IndexTask>() {

					public IndexTask mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						IndexTask indexTask = new IndexTask();
						indexTask.setTaskId(rs.getInt(1));
						indexTask.setAppId(rs.getInt(2));
						indexTask.setDevideField1Value(rs.getString(3));
						indexTask.setDevideField2Value(rs.getString(4));
						indexTask.setCircleTime(rs.getLong(5));
						indexTask.setUpdateDetailTime(rs.getBoolean(6));
						indexTask.setPriority(rs.getInt(7));
						indexTask.setCreaterNo(rs.getString(8));
						indexTask.setErrorPercent(rs.getInt("ErrorPercent"));
						indexTask.setWarnPercent(rs.getInt("WarnPercent"));
						indexTask.setMemo(rs.getString("Memo"));
						indexTask
								.setValidationType(rs.getInt("validationType"));
						return indexTask;
					}
				}, taskID);
		return indexTasks.size() > 0 ? indexTasks.get(0) : null;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"beans/db.xml");

		IndexTaskListDao indexTaskListDao = (IndexTaskListDao) ctx
				.getBean("IndexTaskListDao");
		IndexTask indexTask = indexTaskListDao.getTask(1);
		System.out.println(indexTask.getAppId());
	}
}
