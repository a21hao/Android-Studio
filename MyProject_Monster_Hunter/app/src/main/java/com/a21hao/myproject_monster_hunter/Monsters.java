package com.a21hao.myproject_monster_hunter;

public class Monsters {
    private int id;
    private String type;
    private String element;
    private String name;
    private String description;
//    private String ailment;
//    private String location;
//    private String resistance;

    public Monsters(){

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getElement(){
        return element;
    }
    public void setElement(String element){
        this.element = element;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}