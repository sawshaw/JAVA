package com.cxf.service;

import javax.jws.WebService;
//备注说明：接口实现类名称前的注解targetNamespace是当前类实现接口所在包名称的反序（PS：加上反斜线），endpointInterface是当前需要实现接口的全称；
@WebService(targetNamespace="http://service.cxf.com/",endpointInterface="com.cxf.service.HelloService",serviceName="HelloService")
public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello(String name) {
		System.out.println("your name is:"+name);
		return "hello "+ name;
	}

}
