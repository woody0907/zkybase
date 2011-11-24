/* 
 * ProjectController.java
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
package org.skydingo.skybase.web;

import java.util.List;

import javax.inject.Inject;

import org.skydingo.skybase.model.Project;
import org.skydingo.skybase.service.ProjectService;
import org.skydingo.skybase.web.navigation.Breadcrumb;
import org.skydingo.skybase.web.navigation.NavigationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project controller.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Inject private ProjectService projectService;
	
	/**
	 * Generates the new project form.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public String getNewProjectForm(Model model) {
		
		// TODO Create a utility method for this
		List<Breadcrumb> breadcrumbs = NavigationUtils.createBreadcrumbs(
				new Breadcrumb("New project", "/projects/new.do"));
		model.addAttribute(breadcrumbs);
		
		model.addAttribute(new Project());
		return "project/newProject";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getProject(@PathVariable String id, Model model) {
		model.addAttribute(projectService.getProject(id));
		return "project/projectDetails";
	}
}
