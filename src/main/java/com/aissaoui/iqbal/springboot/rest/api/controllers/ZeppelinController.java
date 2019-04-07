package com.aissaoui.iqbal.springboot.rest.api.controllers;

import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinRequest;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinResult;
import com.aissaoui.iqbal.springboot.rest.api.util.ZeppelinUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZeppelinController {

	/**
	 * create a paragraph in apache Zeppelin, run it, and return the result
	 * 
	 * @param zRequest ZeppelinRequest, the mapping of the json request
	 * @return ZeppelinResult, the mapping of the json response
	 */
	@GetMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ZeppelinResult execPython(@RequestBody ZeppelinRequest zRequest) {

		ZeppelinResult zResult;
		try {
			zResult = ZeppelinUtils.execRequest(zRequest);
		} catch (Exception ex) {

			zResult = new ZeppelinResult();
			zResult.setResult("Zeppelin has failed!");

		}

		return zResult;
	}
}
