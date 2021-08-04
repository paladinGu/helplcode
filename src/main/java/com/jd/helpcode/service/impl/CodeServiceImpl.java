package com.jd.helpcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.helpcode.mapper.CodeMapper;
import com.jd.helpcode.model.Code;
import com.jd.helpcode.service.CodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 * @author paladin
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {
    /**
     *  按查询次数的倒序查助力码
     * @param activityCode
     * @param pageNo
     * @param pageSize
     */
    public IPage<Code> getCode(String activityCode, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<Code> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Code::getActivityCode, activityCode);
        wrapper.orderByDesc(Code::getQueryNum);
        Page<Code> page = new Page<>(pageNo, pageSize == 0 ? 10 : pageSize);
        IPage<Code> pageResult = this.page(page, wrapper);
        //异步查询次数+1,尽可能的保证code都被查询到
        //TODO
        return pageResult;
    }

}