package com.timemachine.springcloud.entity;

/**
 * Role Entity
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午2:36
 */

public class Role {



    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
