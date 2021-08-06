package com.jd.helpcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.helpcode.model.Code;
import org.apache.ibatis.annotations.Update;

/**
 *
 */
public interface CodeMapper extends BaseMapper<Code> {

    @Update("update code set query_uum=query_uum+1 where activity_code=${activityCode} and share_code=${shareCode}")
    public void addNum(String activityCode,String shareCode);

}
