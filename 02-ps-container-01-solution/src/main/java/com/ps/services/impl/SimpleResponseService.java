package com.ps.services.impl;

import com.ps.ents.Response;
import com.ps.repos.ResponseRepo;
import com.ps.services.ResponseService;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleResponseService extends SimpleAbstractService<Response> implements ResponseService {

    private ResponseRepo repo;

    public SimpleResponseService(ResponseRepo responseRepo) {
        this.repo = responseRepo;
    }

    //                setters & getters
    @Override
    public ResponseRepo getRepo() {
        return repo;
    }
}
