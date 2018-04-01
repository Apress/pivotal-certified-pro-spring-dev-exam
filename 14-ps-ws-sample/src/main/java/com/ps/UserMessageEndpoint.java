package com.ps;

import com.ps.ws.GetUserRequest;
import com.ps.ws.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by iuliana.cosmina on 10/16/16.
 */
@Endpoint
public class UserMessageEndpoint {

    public static final String NAMESPACE_URI = "http://ws-boot.com/schemas/um";

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    public UserMessageEndpoint(UserMessageRepository userMessageRepository) {
        this.userMessageRepository = userMessageRepository;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUserType(userMessageRepository.findUserType(request.getEmail()));
        return response;
    }
}
