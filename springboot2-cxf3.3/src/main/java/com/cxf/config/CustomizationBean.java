package com.cxf.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cxf.service.HelloService;
import com.cxf.service.HelloServiceImpl;

@Configuration
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Override
	public void customize(ConfigurableServletWebServerFactory server) {
		server.setPort(9090);
	}
	/**
	 * @return
	 * 使用此方法和上面的功能一致，只不过可配置项增多,只要其中一个即可
	 */
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.setPort(9090);
		return factory;
	}
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*");
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus()
    {
        return  new SpringBus();
    }

    @Bean
    public HelloService helloService()
    {
        return  new HelloServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), helloService());//绑定要发布的服务
        endpoint.publish("/hello"); //显示要发布的名称
        return endpoint;
    }
    //本地输入http://localhost:9090/services/hello?wsdl就可以了

}