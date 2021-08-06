package com.jd.helpcode.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 表模板,其他表将以此为模板生成
 */
@Data
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@TableName(value = "code")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @TableId(value = "id", type = IdType.AUTO)
    @Column(isAutoIncrement = true)
    private Long id;

    @Column(length = 80, comment = "活动code")
    @Index(value = "index_a_s",columns = {"activity_code","share_code"})
    private String activityCode;


    @Column(length = 200, comment = "助力码")
    private String shareCode;

    @Column(comment = "查询次数",defaultValue = "0")
    private Integer queryNum;

    @Column
    private LocalDate createTime;

    @Column
    private LocalDate updateTime;
}
