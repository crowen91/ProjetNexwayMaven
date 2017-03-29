package com.quentin.book.gestionderby;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book2, Long> 
{

   List<Book2> findByFirstnameOrLastname(String firstname, String lastname);
   Book2 findByEmail(String email);
    
}
