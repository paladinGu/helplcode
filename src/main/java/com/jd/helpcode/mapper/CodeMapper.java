package com.jd.helpcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.helpcode.model.Code;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *
 */
public interface CodeMapper extends BaseMapper<Code> {

    @Update("update code set query_num=query_num+1 where activity_code='${activityCode}' and share_code='${shareCode}'")
    void addNum(String activityCode, String shareCode);


    @Update("DROP TABLE IF EXISTS code_bak;" +
            "CREATE TABLE code_bak LIKE code;" +
            "INSERT INTO code_bak SELECT * FROM code;")
    void creatBack();

    @Update("truncate table code")
    void clearTable();

//    @Select()
//    void getBack(){}
}
