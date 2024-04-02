package cn.edu.chzc.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TeaMake {
    @Id
    private Long id;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
    private String step5;
    private String step6;
    private String step7;
    private String step8;
    private String step9;
    private String step10;

    public List<String> getStepAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<String> list = new ArrayList<>();

        Class<? extends TeaMake> TC = this.getClass();
        for (int i = 1; i < 10; i++) {
            String info = (String) TC.getMethod("getStep" + i).invoke(this);
            if(info != null){
                list.add(info);
            }
        }
        return list;
    }
}
