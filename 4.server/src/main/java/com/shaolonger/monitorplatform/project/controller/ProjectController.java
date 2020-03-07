package com.shaolonger.monitorplatform.project.controller;

import com.shaolonger.monitorplatform.project.entity.ProjectEntity;
import com.shaolonger.monitorplatform.project.service.ProjectService;
import com.shaolonger.monitorplatform.utils.ResponseResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Object add(@Valid ProjectEntity projectEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                stringBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            throw new ValidationException(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        } else {
            return ResponseResultBase.getResponseResultBase(projectService.add(projectEntity));
        }
    }
}