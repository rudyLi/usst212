package com.dianping.searchQA.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;

public class RemoteIP {
	
	public  String  getIP() {
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("RemotingServiceIP.properties"); 
	Properties ip=new Properties();
	try {   
		   ip.load(inputStream);  
		   
		  } catch (IOException e1) {   
		   e1.printStackTrace();   
		  }
	    return ip.getProperty("IP");   
	}
	public static void main(String[] args) {
		RemoteIP appTest=new RemoteIP();
		System.out.println(appTest.getIP());
	}
	}

