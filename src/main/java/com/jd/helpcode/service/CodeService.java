package com.jd.helpcode.service;

import com.jd.helpcode.model.Code;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jd.helpcode.vo.CodeVO;

import java.util.List;

/**
 *
 */
public interface CodeService extends IService<Code> {

    void uploadCode(List<CodeVO> vos);
}
