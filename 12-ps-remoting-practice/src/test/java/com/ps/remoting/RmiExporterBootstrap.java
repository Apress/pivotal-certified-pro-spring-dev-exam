package com.ps.remoting;

import com.ps.config.ServiceConfig;
import com.ps.remoting.config.RmiServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiExporterBootstrap {

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(RmiServerConfig.class, ServiceConfig.class);
		System.out.println("RMI reward network server started.");
		System.in.read();
		ctx.close();
	}
}
