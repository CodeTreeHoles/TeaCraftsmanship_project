package cn.edu.chzc.mapper;

import cn.edu.chzc.pojo.TeaContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeaContentMapper {
    @Select("SELECT * FROM tea_content where id=#{id}")
    TeaContent getContent(Long id);
}
