package com.weikun.json;

import java.io.Serializable;

public class User implements Serializable {  
    
    private static final long serialVersionUID = 1L;  
      
    private String id;  
    private String name;  
    private int age;  
    private Address address;  
      
    public User() {  
        super();  
    }  
  
    public User(String id, String name, int age) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.age = age;  
    }  
      
    public User(String id, String name, int age, Address address)  
    {  
        super();  
        this.id = id;  
        this.name = name;  
        this.age = age;  
        this.address = address;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
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
  
    public Address getAddress()  
    {  
        return address;  
    }  
  
    public void setAddress(Address address)  
    {  
        this.address = address;  
    }  
      
}  