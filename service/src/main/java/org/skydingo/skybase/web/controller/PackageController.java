/* 
 * PackageController.java
 * 
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.skydingo.skybase.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skydingo.skybase.exception.DuplicateEntityException;
import org.skydingo.skybase.model.Package;
import org.skydingo.skybase.repository.PackageRepository;
import org.skydingo.skybase.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Package controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/packages")
public class PackageController extends AbstractEntityController<Package> {
	private static final Logger log = LoggerFactory.getLogger(PackageController.class);
	
	@Inject private PackageRepository packageRepo;
	@Inject private PackageService packageService;
	
	@Value("#{config['app.baseUrl']}")
	private String appBaseUrl;
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.controller.AbstractEntityController#getRepository()
	 */
	@Override
	public GraphRepository<Package> getRepository() { return packageRepo; }
	
	/* (non-Javadoc)
	 * @see org.skydingo.skybase.web.AbstractController#doInitBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@Override
	protected void doInitBinder(WebDataBinder binder) {
		binder.setAllowedFields("groupId", "packageId", "version");
	}
	
	
	// =================================================================================================================
	// Create
	// =================================================================================================================
	
	// consumes : Spring 3.1
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/xml")
	public void postPackage(@RequestBody Package pkg, HttpServletRequest req, HttpServletResponse res) {
		log.debug("Posting package: {}", pkg);
		
		try {
			packageService.createPackage(pkg);
			res.setHeader("Location", appBaseUrl + "/packages/" + pkg.getId());
		} catch (DuplicateEntityException e) {
			log.info("Package already exists; ignoring: {}", pkg);
			
			// http://stackoverflow.com/questions/3825990/http-response-code-for-post-when-resource-already-exists
//			log.warn("Package already exists: {}", pkg);
//			res.setStatus(HttpServletResponse.SC_CONFLICT);
			
			// This seems better:
			// http://stackoverflow.com/questions/283957/rest-correct-http-response-code-for-a-post-which-is-ignored
			res.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
