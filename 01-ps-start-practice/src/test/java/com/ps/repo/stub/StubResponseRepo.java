package com.ps.repo.stub;

import com.ps.ents.Response;
import com.ps.repos.ResponseRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubResponseRepo implements ResponseRepo {

    private Map<Long, Response> responseMap = new HashMap<>();

    @Override
    public void save(Response response) {
        if (response.getId() != null) {
            responseMap.put(response.getId(), response);
        }
    }

    @Override
    public void delete(Response response) {
        responseMap.remove(response.getId());
    }

    @Override
    public void deleteById(Long responseId) {
        responseMap.remove(responseId);
    }

    @Override
    public Response findById(Long responseId) {
        return responseMap.get(responseId);
    }
}
