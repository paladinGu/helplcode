package com.jd.helpcode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jd.helpcode.model.Code;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jd.helpcode.vo.CodeVO;

import java.util.List;

/**
 *
 */
public interface CodeService extends IService<Code> {

    IPage<CodeVO> getCode(String activityCode, Integer pageNo, Integer pageSize);

    void uploadCode(List<CodeVO> vos);
}
