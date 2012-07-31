package com.dianping.searchQA.IndexServiceTest;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import com.caucho.hessian.client.HessianProxyFactory;
import com.dianping.searchQA.IndexServiceBase.IndexTestBase;
import com.dianping.searchQA.IndexTaskDao.IndexTaskListDao;
import com.dianping.searchQA.util.RemoteIP;
import com.dp.ir.common.bean.IndexTask;
import com.dp.ir.common.bean.IndexTaskOperation;
import com.dp.ir.common.communication.DpInternalIndexService;

/**
 *功能说明：
 * 
 * @author：
 *@version：20122012-7-23上午09:51:34
 */
public class IndexCreateTest extends IndexTestBase {

	/**
	 * @param args
	 */
	private static final Logger LOGGER = LoggerFactory
	
			.getLogger(IndexCreateTest.class);

	@Autowired
	private IndexTaskListDao indexTaskListDao;
	
	@Test
	public  void CreateManualIndex() throws MalformedURLException, ClassNotFoundException 

	{

		List<IndexTask> taskList = new ArrayList<IndexTask>();

		// // 索引的task，这里硬编码在代码里，实际使用中可以从数据库读取
		//
		// IndexTask task = new IndexTask();
		//
		// task.setTaskId(26);
		//
		// task.setAppId(5);
		//
		// task.setDevideField1Value("35");
		//
		// task.setDevideField2Value("");
		//
		// task.setCreaterNo("A");
		//
		// task.setErrorPercent(10);
		//
		// task.setWarnPercent(5);
		//
		// task.setCircleTime(360);
		//
		// task.setUpdateDetailTime(false);
		//
		// task.setPriority(192);
		//
		// task.setValidationType(1);
		//
		// task.setMemo("新商户搜索[城市35]");
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(
//				"beans/db.xml");
//
//		IndexTaskListDao indexTaskListDao = (IndexTaskListDao) ctx
//				.getBean("IndexTaskListDao");
		
		RemoteIP remoteIP=new RemoteIP();
		String IP=remoteIP.getIP();
		
		IndexTask indexTask = indexTaskListDao.getTask(2);

		taskList.add(indexTask);

		IndexTaskOperation indexTaskOperation = new IndexTaskOperation();

		indexTaskOperation.setOperator(IndexTaskOperation.MANUAL);

		indexTaskOperation.setPublishIndexMessage(false);

		indexTaskOperation.setReplicateIndex(false);

		indexTaskOperation.setSwitchIndex(false);

		indexTaskOperation.setSyncIndex(false);
		
		

		String indexServerUrl = "http://"+IP+"/dp-indexer/remoting/InternalIndexService";

		HessianProxyFactory factory = new HessianProxyFactory();

		DpInternalIndexService indexService = (DpInternalIndexService) factory
				.create(indexServerUrl);
		

		int status = indexService.createManualIndex(taskList,
				indexTaskOperation);

		if (status == -1)

		{

			System.out.println("fail");
			LOGGER.error("IndexTask {} Appid {} fail", indexTask.getTaskId(),indexTask.getAppId());

		}

		else if (status == 0)

		{

			LOGGER.info("IndexTask {} Appid {} success", indexTask.getTaskId(),indexTask.getAppId());

		}

		else

		{

			LOGGER.info("Another task is running");

		}

	}
}
