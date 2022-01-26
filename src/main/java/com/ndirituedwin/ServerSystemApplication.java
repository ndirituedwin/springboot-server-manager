package com.ndirituedwin;

import com.ndirituedwin.Repository.ServerRepository;
import com.ndirituedwin.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.ndirituedwin.Enum.Status.SERVER_UP;

@SpringBootApplication
public class ServerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSystemApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(ServerRepository serverRepository){
//		return args -> {
//			serverRepository.save(new Server(null,"192.168.0.108","ubuntu ","16 GB","Personal pc","http://localhost:8080/api/server/image/serverone.png",SERVER_UP));
//			serverRepository.save(new Server(null,"192.168.0.253","linux","6 GB","tablet pc","http://localhost:8080/api/server/image/servertwo.png",SERVER_UP));
//			serverRepository.save(new Server(null,"197.136.131.1","mac os","8 GB","workstation","http://localhost:8080/api/server/image/serverthree.png",SERVER_UP));
//			serverRepository.save(new Server(null,"197.136.131.255","windows","20 GB","desktop pc","http://localhost:8080/api/server/image/serverfour.png",SERVER_UP));
//		};
//	}

}
