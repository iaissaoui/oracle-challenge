/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.model;

/**
 * Rest mapping of the request
 * @author Iqbal AISSAOUI <aissaoui.iqbal@gmail.com>
 */
public class ZeppelinRequest {
    private String code;
    private String sessionid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }


    
    
}
