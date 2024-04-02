package cn.edu.chzc.service;

import cn.edu.chzc.pojo.Tea;

import java.util.List;

public interface TeaService {
    Tea getTeaById(Long id);

    List<Tea> getAllTeas();
}
