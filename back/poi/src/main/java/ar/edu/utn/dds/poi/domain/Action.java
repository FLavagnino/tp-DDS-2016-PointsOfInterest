package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings("serial")
//@NamedQueries({
//	@NamedQuery(
//	name = "actionsByUserID",
//	query = "from Action a where a.User.Id = :user_id"
//	)
//})
@Entity
public class Action implements Serializable
{	
	private Long id;
    private String name;
    private User user;
    
    public Action()
    {}

    public Action(String name, User user)
    {
        this.name = name;
        this.user = user;
    }
   
    @Id @GeneratedValue
    public Long getId() { return id; }
    public String getName() { return name; }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    public User getUser() { return user; }
    
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUser(User user) { this.user = user; }
}
