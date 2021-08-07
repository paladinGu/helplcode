package com.jd.helpcode.common.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.helpcode.model.Code;
import com.jd.helpcode.vo.CodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AbstractCodeConverter {

    public static AbstractCodeConverter INSTANCE = Mappers.getMapper(AbstractCodeConverter.class);

    public abstract Page<CodeVO> doPageListVoPage(IPage<Code> doPage);


}
