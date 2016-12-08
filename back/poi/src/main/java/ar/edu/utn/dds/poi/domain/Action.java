package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Action implements Serializable
{
    private Long id;
    private String name;
    private User user;
    
    public Action()
    {}
   
    @Id @GeneratedValue
    public Long getId() { return id; }
    public String getName() { return name; }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    public User getUser() { return user; }
    
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUser(User user) { this.user = user; }
}
