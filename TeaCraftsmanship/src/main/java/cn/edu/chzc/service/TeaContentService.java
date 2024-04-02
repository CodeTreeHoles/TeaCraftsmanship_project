package cn.edu.chzc.service;

import cn.edu.chzc.pojo.TeaContent;
import cn.edu.chzc.pojo.TeaMake;

public interface TeaContentService {

    TeaContent getTeaContent(Long id);
    TeaMake getTeaMake(Long id);
}
