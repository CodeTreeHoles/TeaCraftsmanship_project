package cn.edu.chzc.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String personalProfile;
    private String password;
    private Long phone;
    private String salt;
}
