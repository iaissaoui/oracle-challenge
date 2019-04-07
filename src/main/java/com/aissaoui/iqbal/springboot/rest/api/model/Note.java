/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aissaoui.iqbal.springboot.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;

/**
 * Representation of the Zeppelin Note
 * 
 * @author Iqbal AISSAOUI <aissaoui.iqbal@gmail.com>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {

	private String id;
	private String name;

	public Note() {
		this.name = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Note{" + "id=" + id + ", name=" + name + '}';
	}

}
