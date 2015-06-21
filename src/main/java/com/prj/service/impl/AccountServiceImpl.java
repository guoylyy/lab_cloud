/**
 * 404 Studio
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.prj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.prj.common.util.DataWrapper;
import com.prj.common.util.MD5Tool;
import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.service.AccountService;

/**
 * 
 * @author yiliang.gyl
 * @version $Id: AccountServiceImpl.java, v 0.1 Jun 21, 2015 11:55:30 AM yiliang.gyl Exp $
 */
@PropertySource(value = { "/WEB-INF/spring-security.properties" })
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {
    @Autowired
    Environment env;

    AccountDao  ad;

    @Autowired
    public AccountServiceImpl(AccountDao ad) {
        super(Account.class, ad);
        this.ad = ad;
    }

    public DataWrapper create(Account a) {
        assertUniqueNumber(a.getAccount());
        return super.createEntity(a);
    }

    public DataWrapper findByAccount(String account) {
        return findBy("account", account);
    }

    public DataWrapper updatePassword(long id, String oldPassword, String newPassword) {
        Account a = (Account) read(id).getData();
        if (MD5Tool.getMd5(oldPassword).equals(a.getPassword())) {
            a.setPassword(MD5Tool.getMd5(newPassword));
            return DataWrapper.voidSuccessRet;
        } else {
            return new DataWrapper(false);
        }
    }

    public DataWrapper updatePasswordByAdmin(long id, String newPassword) {
        Account a = (Account) read(id).getData();
        a.setPassword(MD5Tool.getMd5(newPassword));
        return DataWrapper.voidSuccessRet;
    }

    public boolean saveUserToken(long id, String token) {
        Account a = (Account) read(id).getData();
        if (a == null) {
            return false;
        }
        a.setToken(token);
        ad.update(a);
        return true;
    }

}
