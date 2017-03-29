package com.quentin.book.gestionderby;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
public class Book2
{
    
    @javax.persistence.Id
    @GeneratedValue
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    
        
        
    public Book2()
    {
        super();           
    }

    public Book2(Long id, String email, String firstname, String lastname)
    {
        super();
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Book2(String email, String firstname, String lastname)
    {
        super();
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    
    @Override
    public String toString()
    {
        return "[" + this.email + ";" + this.firstname + ";" + this.lastname + "]";
    }
}
