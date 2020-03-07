package com.shaolonger.monitorplatform.user.controller;

import com.shaolonger.monitorplatform.user.entity.UserRegisterRecordEntity;
import com.shaolonger.monitorplatform.user.service.UserRegisterRecordService;
import com.shaolonger.monitorplatform.utils.ResponseResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/userRegisterRecord")
public class UserRegisterRecordController {
    @Autowired
    private UserRegisterRecordService userRegisterRecordService;

    /**
     * 新增
     *
     * @param userRegisterRecordEntity
     * @param bindingResult
     * @return Object Object
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Object add(@Valid UserRegisterRecordEntity userRegisterRecordEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                stringBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            throw new ValidationException(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        } else {
            return ResponseResultBase.getResponseResultBase(userRegisterRecordService.add(userRegisterRecordEntity));
        }
    }

    /**
     * 审批
     *
     * @param request request
     * @return Object
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Object audit(HttpServletRequest request) {
        try {
            return ResponseResultBase.getResponseResultBase(userRegisterRecordService.audit(request));
        } catch (Exception e) {
            return ResponseResultBase.getErrorResponseResult(e);
        }
    }

    /**
     * 多条件查询
     *
     * @param request request
     * @return Object
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get(HttpServletRequest request) {
        return ResponseResultBase.getResponseResultBase(userRegisterRecordService.get(request));
    }
}