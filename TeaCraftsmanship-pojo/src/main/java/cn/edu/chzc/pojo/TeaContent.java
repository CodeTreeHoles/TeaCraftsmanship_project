package cn.edu.chzc.pojo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TeaContent {
    @Id
    private Long id;
    private String name;
    private String title;
    private String history;
    private Long makeId;
}
