/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Paragraph {
    
    
    private String id;
    private String title;
    private String text;
    private String data;




    
    public Paragraph(String text) {
        this(UUID.randomUUID().toString(),text); 
    }
    
    public Paragraph(String title, String text) {
        
        this.title = title;
        this.text = text;
    }

    public Paragraph() {
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
     
    public String getData() {
        return data;
    }

    public void setData(String data) {
        data = data.replace("\n", "");  
        this.data = data;
    }

    @Override
    public String toString() {
        return "Paragraph{" + "id=" + id + ", title=" + title + ", text=" + text + ", data=" + data + '}';
    }
    
    
    
}
