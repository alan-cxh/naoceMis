package edu.sjtu.naocemis.authentication.permission.entity;

public class Right {

    private String id ;

    private String name;

    private String code;

    private  String  description;

    private Right parent;

//    private Client client;
//
//    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Right getParent() {
        return parent;
    }

    public void setParent(Right parent) {
        this.parent = parent;
    }


}
