package com.binubalan.pluginarchi.plugin.api.services;

import com.binubalan.pluginarchi.plugin.api.daos.AdministratorUserDao;
import com.binubalan.pluginarchi.plugin.api.entities.AdministratorUser;
import org.springframework.stereotype.Service;

@Service("administratorUserService")
public class AdministratorUserService extends BaseService<AdministratorUser, String> {
    public AdministratorUserService(AdministratorUserDao administratorUserDao) {
        super(administratorUserDao);
    }
}
