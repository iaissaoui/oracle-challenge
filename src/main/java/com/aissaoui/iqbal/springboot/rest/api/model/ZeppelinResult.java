/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Rest mapping of the result
 * @author Iqbal AISSAOUI <aissaoui.iqbal@gmail.com>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZeppelinResult {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
