/**
 * 404 Studio
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.prj.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prj.common.util.DataWrapper;
import com.prj.common.util.RequiredRole;
import com.prj.entity.Account;
import com.prj.entity.Account.Role;

/**
 * 
 * @author yiliang.gyl
 * @version $Id: AccountController.java, v 0.1 Jun 21, 2015 11:01:21 AM yiliang.gyl Exp $
 */
@RestController
public class AccountController extends BaseController {

    // ~ Private Account Management
    // ===================================================================================

    /**
     * PUT /account/{id}
     * <p>
     * BODY Account
     *
     * @param id
     * @param data
     * @return
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    DataWrapper updateProfile(@PathVariable long id, @RequestBody Account data) {
        if (!isAdmin())
            assertOwner(id);
        return as.update(id, data);
    }

    /**
     * PUT /account/{id}/password
     * <p>
     * BODY {oldPassword, newPassword}
     * <p>
     * Reset password by oneself or administrator
     *
     * @param id
     * @param data
     * @return
     */
    @RequestMapping(value = "/account/{id}/password", method = RequestMethod.PUT)
    DataWrapper updatePassword(@PathVariable long id, @RequestBody Map<String, String> data) {
        if (!isAdmin())
            assertOwner(id);
        return as.updatePassword(id, data.get("oldPassword"), data.get("newPassword"));
    }

    /**
     * PUT /account/{id}/passwordByAdmin
     * <p>
     * BODY { newPassword }
     *
     * @param id
     * @param data
     * @return
     */
    @RequestMapping(value = "/account/{id}/passwordByAdmin", method = RequestMethod.PUT)
    @RequiredRole(Role.ADMINISTRATOR)
    DataWrapper updatePasswordByAdmin(@PathVariable long id, @RequestBody Map<String, String> data) {
        return as.updatePasswordByAdmin(id, data.get("newPassword"));
    }

    /**
     * POST /account
     * <p>
     * BODY Account
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @RequiredRole(Role.ADMINISTRATOR)
    DataWrapper add(@RequestBody Account data) {
        return as.create(data);
    }

    /**
     * DELETE /account/{id}
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    @RequiredRole(Role.ADMINISTRATOR)
    DataWrapper delete(@PathVariable long id) {
        return as.delete(id);
    }

    /**
     * GET /accounts/{id}
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    @RequiredRole(Role.ADMINISTRATOR)
    DataWrapper get(@PathVariable long id) {
        return as.read(id);
    }

    /**
     * GET /accounts?number={number}
     *
     * @param number
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @RequiredRole(Role.ADMINISTRATOR)
    DataWrapper search(@RequestParam String account) {
        return as.findByAccount(account);
    }

}
