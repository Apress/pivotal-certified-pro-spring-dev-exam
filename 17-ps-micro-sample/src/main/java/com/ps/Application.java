package com.ps;

import com.ps.pet.PetServer;
import com.ps.server.DiscoveryServer;
import com.ps.user.UserServer;
import com.ps.web.WebServer;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
public class Application {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Specify application to start! (Options: reg, user, pet, web)");
		} else {
			switch (args[0]) {
			case "reg":
				DiscoveryServer.main(args);
				break;
			case "user":
				UserServer.main(args);
				break;
			case "pet":
				if (args.length == 2) {
					System.setProperty("server.port", args[1]);
				}
				PetServer.main(args);
				break;
			case "web":
				WebServer.main(args);
				break;
			default:
				System.out.println("Specify application to start! (Options:" +
						"reg, user, pet, web)");
			}
		}
	}
}
