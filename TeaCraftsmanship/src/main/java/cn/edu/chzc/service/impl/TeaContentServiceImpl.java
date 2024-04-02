package cn.edu.chzc.service.impl;

import cn.edu.chzc.mapper.TeaContentMapper;
import cn.edu.chzc.mapper.TeaMakeMapper;
import cn.edu.chzc.pojo.TeaContent;
import cn.edu.chzc.pojo.TeaMake;
import cn.edu.chzc.service.TeaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeaContentServiceImpl implements TeaContentService {

    @Autowired
    private TeaContentMapper teaContentMapper;

    @Autowired
    private TeaMakeMapper teaMakeMapper;

    @Override
    public TeaContent getTeaContent(Long id) {
        return teaContentMapper.getContent(id);
    }

    @Override
    public TeaMake getTeaMake(Long id) {
        return teaMakeMapper.getTeaMake(id);
    }
}
