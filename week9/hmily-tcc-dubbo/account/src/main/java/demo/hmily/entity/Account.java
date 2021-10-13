package demo.hmily.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    private Long id;
    private String name;
    private Long cnyNum;
    private Long usdNum;
}
