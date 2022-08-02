package com.binubalan.pluginarchi.plugin.api.services;

import com.binubalan.pluginarchi.plugin.api.daos.CustomerUserDao;
import com.binubalan.pluginarchi.plugin.api.entities.CustomerUser;
import org.springframework.stereotype.Service;

@Service("customerUserService")
public class CustomerUserService extends BaseService<CustomerUser, String> {
    public CustomerUserService(CustomerUserDao dao) {
        super(dao);
    }
}
