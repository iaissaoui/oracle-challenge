package com.aissaoui.iqbal.springboot.rest.api.controllers;

import com.aissaoui.iqbal.springboot.rest.api.model.Paragraph;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinRequest;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinResult;
import com.aissaoui.iqbal.springboot.rest.api.util.ZeppelinUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ZeppelinController {
    
    

    /**
     * create a paragraph in apache Zeppelin, run it, and return the result
     */
    @RequestMapping(value = "/execute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ZeppelinResult execPython(@RequestBody ZeppelinRequest request) {
        
 
        ZeppelinResult zr = ZeppelinUtils.execRequest(request);

        
        
        

        return zr;
    }
}
