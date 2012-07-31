package com.dianping.searchQA.IndexServiceTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.dianping.ir.index.servicetest.initDataBase.initDataBase;
import com.dianping.search.intf.Iface;
import com.dianping.searchQA.IndexServiceBase.IndexTestBase;
import com.dianping.searchQA.IndexTaskDao.IndexTaskListDao;
import com.dianping.searchQA.util.RemoteIP;
import com.dp.ir.common.bean.IndexTask;
import com.dp.ir.common.bean.IndexTaskOperation;
import com.dp.ir.common.communication.DpInternalIndexService;

/**
 *功能说明： 修改服务器状态使其为1时，another index is running
 * 
 * @author：
 *@version：20122012-7-31上午10:13:02
 */
public class IndexIsRunningTest extends IndexTestBase {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IndexCreateTest.class);

	@Autowired
	private IndexTaskListDao indexTaskListDao;

	@BeforeTest
	public void setup() throws IOException {
		new initDataBase().changeRunstatus();

	}

	@Test
	public void CreateManualIndex() throws MalformedURLException,
			ClassNotFoundException

	{

		List<IndexTask> taskList = new ArrayList<IndexTask>();

		RemoteIP remoteIP = new RemoteIP();
		String IP = remoteIP.getIP();

		IndexTask indexTask = indexTaskListDao.getTask(1);

		taskList.add(indexTask);

		IndexTaskOperation indexTaskOperation = new IndexTaskOperation();

		indexTaskOperation.setOperator(IndexTaskOperation.MANUAL);

		indexTaskOperation.setPublishIndexMessage(false);

		indexTaskOperation.setReplicateIndex(false);

		indexTaskOperation.setSwitchIndex(false);

		indexTaskOperation.setSyncIndex(false);

		String indexServerUrl = "http://" + IP
				+ "/dp-indexer/remoting/InternalIndexService";

		HessianProxyFactory factory = new HessianProxyFactory();

		DpInternalIndexService indexService = (DpInternalIndexService) factory
				.create(indexServerUrl);

		int status = indexService.createManualIndex(taskList,
				indexTaskOperation);
	
		Assert.assertFalse("the index run success", status == 0);
		
		Assert.assertFalse("the index run fail", status == -1);
		
		if (status != 0 && status != -1) {
			
			LOGGER.info("another task is running, test is success");
			
		}
	}

	@AfterTest
	public void tearDown() throws IOException {
		
		new initDataBase().recoverRunstatus();
		
	}
}
