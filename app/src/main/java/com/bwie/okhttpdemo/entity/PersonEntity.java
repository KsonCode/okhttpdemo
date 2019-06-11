package com.bwie.okhttpdemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class PersonEntity {
    @Id(autoincrement = true)
    @Unique
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String age;

    @Transient
    private boolean isBoy;

    @Generated(hash = 1622801737)
    public PersonEntity(long id, @NotNull String name, @NotNull String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 69356185)
    public PersonEntity() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
