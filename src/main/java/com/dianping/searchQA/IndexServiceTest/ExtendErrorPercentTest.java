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
 *@version：20122012-7-31下午01:42:28
 */
public class ExtendErrorPercentTest extends IndexTestBase {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExtendErrorPercentTest.class);
	private initDataBase iDataBase = new initDataBase();

	@Autowired
	private IndexTaskListDao indexTaskListDao;

	@BeforeTest
	public void setup() throws IOException {

		iDataBase.QasearchInitDatabase();
		iDataBase.changeErrorPercent();

	}

	@Test
	public void CreateManualIndex() throws MalformedURLException,
			ClassNotFoundException

	{

		List<IndexTask> taskList = new ArrayList<IndexTask>();

		RemoteIP remoteIP = new RemoteIP();
		String IP = remoteIP.getIP();

		IndexTask indexTask = indexTaskListDao.getTask(293);

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

		Assert.assertTrue("test fail", status == -1);

		if (status == -1) {

			LOGGER.info("index is fail,errorPercent change test success");
		}
	}

	@AfterTest
	public void teardown() throws IOException {
		iDataBase.QasearchInitDatabase();
		iDataBase.recoverErrorPercent();

	}
}
