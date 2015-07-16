/**
 * 404 Studio
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.prj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prj.common.util.DataWrapper;

/**
 * 不需要登录就可以访问的api 
 * @author globit
 * @version $Id: NoneLoginBaseController.java, v 0.1 Jul 16, 2015 9:18:26 PM globit Exp $
 */
@RequestMapping("/napi")
@RestController
public class NoneLoginController {

    /**
     * POST /account/register
     * 
     * @param
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    DataWrapper register() {
        return new DataWrapper();
    }
}
