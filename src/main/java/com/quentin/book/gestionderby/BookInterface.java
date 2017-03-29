package com.quentin.book.gestionderby;

import java.util.List;

public interface BookInterface
{
    List<Book2> listAll();

    Book2 getById(Long id);

    Book2 saveOrUpdate(Book2 book);

    void delete(Book2 book);

    List<Book2> findByFirstnameOrLastname(String Firstname, String Lastname);
    
    Book2 getByEmail(String email);
    
}
