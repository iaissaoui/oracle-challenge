/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.util;

import com.aissaoui.iqbal.springboot.rest.api.model.Note;
import com.aissaoui.iqbal.springboot.rest.api.model.Paragraph;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinRequest;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinResult;
import com.github.wnameless.json.flattener.JsonFlattener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aissa
 */
public class ZeppelinUtils {
    
    private static String baseNoteURL;
    private static HashMap<String,Note> hmSessionNotes;
    
    static {
        hmSessionNotes = new HashMap<>();
        baseNoteURL = "http://localhost:8080/api/notebook/";
    }
    
    
    public static Note getSessionNote(ZeppelinRequest zRequest) {
        
        if(hmSessionNotes.get(zRequest.getSessionid()) != null){

            return hmSessionNotes.get(zRequest.getSessionid());
            
        }else{

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Note> noteEntity = new HttpEntity<>(new Note());
            String response = restTemplate.postForObject(baseNoteURL, noteEntity, String.class);
            
            Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(response);
            
            Note n = noteEntity.getBody();
            n.setId(flattenJson.get("body").toString());
            n.setName(UUID.randomUUID().toString());
            hmSessionNotes.put(zRequest.getSessionid(), n);
            
            return n;
        }

   
    }

    public static Paragraph getParagraph(ZeppelinRequest zRequest) {
        Note sessionNote = getSessionNote(zRequest);
        String baseParagraphURL = baseNoteURL + sessionNote.getId() + "/paragraph";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Paragraph> paragraphEntity = new HttpEntity<>(new Paragraph(sessionNote, zRequest.getCode()));
        String response = restTemplate.postForObject(baseParagraphURL, paragraphEntity, String.class);
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(response);

        Paragraph p = paragraphEntity.getBody();
        p.setId(flattenJson.get("body").toString());
        return p;
    }
    
    public static Paragraph runParagraph(ZeppelinRequest zRequest) {
        
        Note sessionNote = getSessionNote(zRequest);
        String baseParagraphURL = baseNoteURL + sessionNote.getId() + "/paragraph";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Paragraph> paragraphEntity = new HttpEntity<>(new Paragraph(sessionNote, zRequest.getCode()));
        String response = restTemplate.postForObject(baseParagraphURL, paragraphEntity, String.class);
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(response);

        Paragraph p = paragraphEntity.getBody();
        p.setId(flattenJson.get("body").toString());
        return p;
    }

        

    public static ZeppelinResult execRequest(ZeppelinRequest zr) {
        
        Paragraph p = getParagraph(zr);
        RestTemplate restTemplate = new RestTemplate();
        String baseParagraphURL = baseNoteURL+ "/run/" + p.getParentNote().getId() + "/" + p.getId();
        HttpEntity<Paragraph> paragraphEntity = new HttpEntity<>(p);
        String response = restTemplate.postForObject(baseParagraphURL, paragraphEntity, String.class);
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(response);
        if ("OK".equals(flattenJson.get("status").toString())) {

            if (flattenJson.get("body.msg[0].data") != null) {
                p.setData(flattenJson.get("body.msg[0].data").toString());
            } else {
                p.setData("");
            }

        }

        System.out.println("#NOTE# " + p.getParentNote().getId());
        ZeppelinResult zres = new ZeppelinResult();
        zres.setResult(p.getData());
        return zres;

    }

}
