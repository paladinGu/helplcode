package com.jd.helpcode.controller;


import com.jd.helpcode.common.Response;
import com.jd.helpcode.service.CodeService;
import com.jd.helpcode.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("getCode")
    public Response getCode(@RequestParam(name = "activityCode") String activityCode,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return Response.ofSuccess(codeService.getCode(activityCode, pageNo, pageSize));

    }


    @PostMapping("uploadCode")
    public Response putCode(@RequestBody List<CodeVO> vos) {
        codeService.uploadCode(vos);
        return Response.ofSuccess();
    }
}
