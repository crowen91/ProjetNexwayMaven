package com.quentin.book.gestionderby;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


@Service
public class BookInterfaceImpl implements BookInterface
{

    private BookRepo bookRepo;
    
    @Autowired
    public BookInterfaceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    
    @Override
    public List<Book2> listAll()
    {
        List<Book2> books = new ArrayList<>();
        for (Book2 book2 : bookRepo.findAll())
        {
            books.add(book2);
        }
        return books;
    }

    @Override
    public Book2 getById(Long id)
    {
        return bookRepo.findOne(id);    
    }

    @Override
    public Book2 saveOrUpdate(Book2 book)
    {
        bookRepo.save(book);
        return book;   
    }

    @Override
    public void delete(Book2 book)
    {
        bookRepo.delete(book.getId());
        
    }

    @Override
    @Query("SELECT * FROM Book2 b where b.Firstname = ?1 or b.Lastname = ?2 ")
    public List<Book2> findByFirstnameOrLastname(String Firstname, String Lastname)
    {
       return bookRepo.findByFirstnameOrLastname(Firstname,Lastname);
        
    }
    

    @Override
    public Book2 getByEmail(String email)
    {
        return bookRepo.findByEmail(email);
        
    }

}
