/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.util;

import com.aissaoui.iqbal.springboot.rest.api.model.Paragraph;
import com.github.wnameless.json.flattener.JsonFlattener;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aissa
 */
public class ZeppelinUtils {

    public static Paragraph execParagraph(String code) {

        /*
            hardcoded note
         */
        String note = "2E6JBGVHF";

        String URL_CREATE_PARAGRAPH = "http://localhost:8080/api/notebook/" + note + "/paragraph";
        RestTemplate restTemplate = new RestTemplate();
        Paragraph p = new Paragraph(code);
        HttpEntity<Paragraph> paragraphEntity = new HttpEntity<>(p);
        String response = restTemplate.postForObject(URL_CREATE_PARAGRAPH, paragraphEntity, String.class);
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(response);
        p.setId(flattenJson.get("body").toString());

        String URL_RUN_PARAGRAPH = "http://localhost:8080/api/notebook/run/" + note + "/" + p.getId();
        response = restTemplate.postForObject(URL_RUN_PARAGRAPH, paragraphEntity, String.class);
        flattenJson = JsonFlattener.flattenAsMap(response);

        if ("OK".equals(flattenJson.get("status").toString())) {

            if (flattenJson.get("body.msg[0].data") != null) {
                p.setData(flattenJson.get("body.msg[0].data").toString());
            } else {
                p.setData("");
            }

        }

        return p;

    }

}
