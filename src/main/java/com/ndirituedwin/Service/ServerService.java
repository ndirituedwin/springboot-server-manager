package com.ndirituedwin.Service;

import com.ndirituedwin.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    Server server(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server findById(Long id);
    Server updateserver(Server server);
    Boolean deleteById(Long id);
}
