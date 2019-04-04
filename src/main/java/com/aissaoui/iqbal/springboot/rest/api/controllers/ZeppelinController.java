package com.aissaoui.iqbal.springboot.rest.api.controllers;

import com.aissaoui.iqbal.springboot.rest.api.model.Paragraph;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinGenericResponse;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinRequest;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinResult;
import com.aissaoui.iqbal.springboot.rest.api.util.ZeppelinUtils;
import com.github.wnameless.json.flattener.JsonFlattener;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ZeppelinController {
    
    

    /**
     * create a paragraph in apache Zeppelin, run it, and return the result
     */
    @RequestMapping(value = "/execute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ZeppelinResult execPython(@RequestBody ZeppelinRequest request) {
        
 
        Paragraph p = ZeppelinUtils.execParagraph(request.getCode());
        ZeppelinResult zr = new ZeppelinResult();
        zr.setResult(p.getData());
        
        

        return zr;
    }
    
    @RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ZeppelinResult execPythonState(@RequestBody ZeppelinRequest request) {
        
 
        Paragraph p = ZeppelinUtils.execParagraphTest();
        ZeppelinResult zr = new ZeppelinResult(); 
        zr.setResult(p.getData());
        
        

        return zr;
    }
    
    
    
    
//    @RequestMapping(value = "/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Paragraph ZeppelinPost() {
//         
//        
//
// 
//    }
    
    
    @RequestMapping(value = "/b", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ZeppelinGenericResponse ZeppelinTest() {
        
        
        RestTemplate restTemplate = new RestTemplate();
        ZeppelinGenericResponse zs = restTemplate.getForObject("http://localhost:8080/api/notebook", ZeppelinGenericResponse.class);
        System.out.println(zs.toString());
          
        return zs;

    }
}
