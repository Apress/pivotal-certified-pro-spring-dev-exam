package com.ps.remoting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiExporterBootstrap {

	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring/rmi-server-config.xml",
				"spring/app-config.xml");
		System.out.println("RMI reward network server started.");
		System.in.read();
		ctx.close();
	}
}
