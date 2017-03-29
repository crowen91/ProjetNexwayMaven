package com.quentin.services.BookDerby;


import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quentin.book.gestionderby.Book2;
import com.quentin.book.gestionderby.BookInterface;

@RestController
@RequestMapping("/home/derby")
public class BookDerbyControler
{
    private static final Logger LOG = getLogger(BookDerbyControler.class);  
    private BookInterface bookInt;
    
    @Autowired
    public void setProductService(BookInterface bookInterface) {
        this.bookInt = bookInterface;
    }
   
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String bookGetAll(
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="email", required=false, defaultValue="null") String email,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname)
    {   
 
        if( firstname.equals("null") && email.equals("null") && lastname.equals("null")){
            LOG.info("GET ALL");
            List<Book2> lb2 =  bookInt.listAll();
            String affichage = "";
            for (Book2 bk2 : lb2)
            {
                affichage = affichage + bk2.toString();
            }
            return affichage;  
        } else {
            LOG.info("GET PARAM " +email + " " +firstname + " " + lastname);
            List<Book2> lb2 =  bookInt.findByFirstnameOrLastname(firstname, lastname);
            String affichage = "";
            for (Book2 bk2 : lb2)
            {
                affichage = affichage + bk2.toString();
            }
            return affichage;
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public  String bookPost (
        @RequestParam(value="email", required=true) String email,
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname) throws IOException
    {
        LOG.info("POST " +email + " " +firstname + " " + lastname);
        
        Book2 bk2 = bookInt.getByEmail(email);
        if(bk2 == null ){
            return "impossible de modifier l'email : "+ email +". Il n'est pas présent";
        } else {
            bk2.setFirstname(firstname);
            bk2.setLastname(lastname);  
            bookInt.saveOrUpdate(bk2);
            return "Modification de " + bk2.toString() +" effectué";
        }
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody String bookPut(
        @RequestParam(value="email", required=true) String email,
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname) throws IOException
    {
        LOG.info("PUT " +email + " " +firstname + " " + lastname );
        Book2 bk2 = bookInt.getByEmail(email);
        if(bk2 != null ){
            return " impossible d'ajouter l'email : "+ email +". Il est déjà présent";
        } else {
             bk2 = new Book2(email,firstname,lastname);
             bookInt.saveOrUpdate(bk2);
             return "Ajout de " + bk2.toString() +" effectué";
            
        }
    }
    
    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody String bookDelete(@RequestParam(value="email", required=true) String email) throws IOException
    {
     
        LOG.info("TODO DELETE " + email);
        Book2 bk2 = bookInt.getByEmail(email);
        if(bk2 != null){
            bookInt.delete(bk2);
            return "l'enregistrement avec l'identifiant " + email +" a bien été supprimé" ;
        } else {
            return "l'email "+email+" n'est pas présent dans notre base de données";
        }
    }
}
