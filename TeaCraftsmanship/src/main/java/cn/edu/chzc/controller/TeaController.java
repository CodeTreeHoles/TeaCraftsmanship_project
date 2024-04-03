package cn.edu.chzc.controller;

import cn.edu.chzc.pojo.Result;
import cn.edu.chzc.pojo.Tea;
import cn.edu.chzc.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tea/home")
public class TeaController {

    @Autowired
    private TeaService teaService;

    @GetMapping("/{id}")
//    public Tea getTeaById(@PathVariable Long id) {
//        return teaService.getTeaById(id);
//    }
    public Result<Tea> chaxun (@PathVariable Long id){
        Tea tea = teaService.getTeaById(id);
        return Result.success(tea);
    }

    @GetMapping("list")
//    public String test(){
//        return "test";
//    }
    public Result<List<Tea>> getAllTeas (){
        List<Tea> list = new ArrayList<>();
        list = teaService.getAllTeas();
        return Result.success(list);
    }
}