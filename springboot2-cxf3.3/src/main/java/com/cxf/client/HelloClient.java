package com.cxf.client;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class HelloClient {
	public static void main(String args[]) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:9090/services/hello?wsdl");
		Object[] objects = client.invoke("sayHello", "wangsong");
		// 输出调用结果  *****hello wangsong
		System.out.println("*****" + objects[0].toString());
	}

}
