package com.github.greengerong;

/**
* ***************************************
* *
* Auth: green gerong                     *
* Date: 2015                             *
* blog: http://greengerong.github.io/    *
* github: https://github.com/greengerong *
* *
* ****************************************
*/
public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
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
