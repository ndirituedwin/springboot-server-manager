package com.ndirituedwin.Service;

import com.ndirituedwin.Enum.Status;
import com.ndirituedwin.Exceptions.ServerNotFoundException;
import com.ndirituedwin.Repository.ServerRepository;
import com.ndirituedwin.model.Server;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.jmx.MBeanServerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static com.ndirituedwin.Enum.Status.SERVER_DOWN;
import static com.ndirituedwin.Enum.Status.SERVER_UP;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ServerServiceimpl implements ServerService{

    private final ServerRepository serverRepository;
    @Override
    public Server server(Server server) {
        log.info("saving server {}",server.getName());
        server.setImageurl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server {}",ipAddress);
        Server server=serverRepository.findByIpAddress(ipAddress).orElseThrow(() -> new ServerNotFoundException("Server wih ip address "+ipAddress+" could not be found"));
        InetAddress  address=InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP:SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers ");
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server findById(Long id) {
        log.info("fetching server by id {}",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server updateserver(Server server) {
        log.info("Updating server {}",server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteById(Long id) {
        log.info("Deleting server server {}",id);
        serverRepository.deleteById(id);

        return true;
    }

    private String setServerImageUrl() {
        String[] serverimages={ "serverone.png","servertwo.png","serverthree.png","serverfour.png"};
//String url="C:\\Users\\ndirituedwin\\IdeaProjects\\NEWSPRINGBOOT\\SPRING PROJECTS\\server_system_with_spring_and_angular\\server-system\\server-system\\src\\main\\resources\\images";
 String url="/api/server/image/";
return ServletUriComponentsBuilder.fromCurrentContextPath().path(url+serverimages[new Random().nextInt(4)]).toUriString();

    }
}
