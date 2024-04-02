package cn.edu.chzc.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Tea {
    @Id
    private Long id;
    private String title;
    private String place;
    private String text;

    //test


    // Getters and Setters
}
