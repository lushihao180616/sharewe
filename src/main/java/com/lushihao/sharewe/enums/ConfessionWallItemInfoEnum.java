package com.lushihao.sharewe.enums;

public enum ConfessionWallItemInfoEnum {

    GRADE(3, "年级"), CLASS(4, "班级"), NAME(1, "姓名");

    private int id;
    private String name;

    ConfessionWallItemInfoEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
