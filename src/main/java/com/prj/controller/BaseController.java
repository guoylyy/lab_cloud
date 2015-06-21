/**
 * 404 Studio
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.prj.controller;

import com.prj.common.exception.AppException;
import com.prj.config.security.AccountUserDetails;
import com.prj.entity.Account;
import com.prj.entity.Account.Role;
import com.prj.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author yiliang.gyl
 * @version $Id: BaseController.java, v 0.1 Jun 21, 2015 11:01:25 AM yiliang.gyl Exp $
 */
@RequestMapping("/api")
public class BaseController {
    /**
     * for convince of dependency configuration change to full name and move to
     * inherited controller finally
     */
    @Autowired
    AccountService as;

    protected void assertOwner(long id) {
        Account a = currentAccount();
        if (id != a.getId()) {
            throw AppException.newInstanceOfForbiddenException(a.getName());
        }
    }

    protected boolean isAdmin() {
        return currentAccount().getRole() == Role.ADMINISTRATOR;
    }

    /**
     * Get current account fetch from token.
     * <p/>
     * Note that password property is already erased.
     *
     * @return current account logged in
     */
    protected Account currentAccount() {
        return ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal()).getAccount();
    }

}
