package com.quentin.book.gestionfichierplat;

public class Book
{

    private String email;
    private String firstname;
    private String lastname;
    
    public Book(String email, String firstname, String lastname)
    {
        super();
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
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
        return "[" + email + ";" + firstname + ";" + lastname + "]";
    }
    
    
    public String toString2()
    {
        return email + ";" + firstname + ";" + lastname;
    }
    
    
  }
