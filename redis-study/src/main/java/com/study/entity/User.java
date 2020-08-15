package com.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String name; // 名称
    private int uid; // uid
    private int age; // 年龄


}
