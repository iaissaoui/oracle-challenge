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
 * Zeppelin utility class to handle various calls to the rest api
 * @author Iqbal AISSAOUI <aissaoui.iqbal@gmail.com>
 */
public class ZeppelinUtils {
    
    private static String baseNoteURL;
    private static HashMap<String,Note> hmSessionNotes;
    
    static {
        hmSessionNotes = new HashMap<>();
        baseNoteURL = "http://localhost:8080/api/notebook/";
    }
    
    /**
     * get or create the note where the paragraph is executed
     * if the call is using an existing session, return the note object of that session
     * otherwise create a new note for this session and save the mapping in the hashmap hmSessionNotes
     * @param zRequest
     * @return Note object of the session defined in the request
     * 
     */
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
/**
 * searchs for the note mapped to the current session and creates a paragraph in that note
 * @param zRequest
 * @return 
 */
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
    /**
     * instructs Apache Zeppelin to run a specific paragraph
     * @param zRequest
     * @return 
     */
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

        
    /**
     * executes the incoming request
     * @param zr
     * @return
     * @throws Exception 
     */
    public static ZeppelinResult execRequest(ZeppelinRequest zr) throws Exception{
        
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
