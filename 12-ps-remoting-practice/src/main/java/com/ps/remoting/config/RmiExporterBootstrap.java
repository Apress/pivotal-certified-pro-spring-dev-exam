package com.ps.remoting.config;

import com.ps.config.ServiceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RmiExporterBootstrap {

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(RmiServerConfig.class, ServiceConfig.class);
		System.out.println("RMI reward network server started.");
		System.in.read();
		ctx.close();
	}
}
