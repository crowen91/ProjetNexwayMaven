package com.quentin.services.BookFichierPlat;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;




import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quentin.book.gestionfichierplat.Book;
import com.quentin.book.gestionfichierplat.BookFichierCsv;
import com.quentin.book.gestionfichierplat.ListBook;



@RestController
@RequestMapping("/home/plat")
public class BookFichierPlatController
{
    private static final Logger LOG = getLogger(BookFichierPlatController.class);  
    
   
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String bookGetAll(
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="email", required=false, defaultValue="null") String email,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname)
    {   
        ListBook lb = new ListBook(BookFichierCsv.findBook());
        
        if( firstname.equals("null") && email.equals("null") && lastname.equals("null")){
            LOG.info("GET ALL");
            return lb.toString();  
        } else {
            LOG.info("GET PARAM " +email + " " +firstname + " " + lastname);
            ListBook result = lb.listBookwithparam(email, firstname, lastname);
            return result.toString();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public  String bookPost (
        @RequestParam(value="email", required=true) String email,
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname) throws IOException
    {
        LOG.info("POST " +email + " " +firstname + " " + lastname);
        ListBook lb = new ListBook(BookFichierCsv.findBook());
        Book bmod = new Book(email,firstname,lastname);
        if( lb.modif(bmod)){
            BookFichierCsv.sauvegarde(lb.getListe_b());  
            return " Ajout de " + bmod.toString() +" effectué";
        } else {
            return " impossible de modifier l'email : "+ email +". Il n'est pas présent";
        }
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody String bookPut(
        @RequestParam(value="email", required=true) String email,
        @RequestParam(value="firstname", required=false, defaultValue="null") String firstname,
        @RequestParam(value="lastname", required=false, defaultValue="null") String lastname) throws IOException
    {
        LOG.info("PUT " +email + " " +firstname + " " + lastname );
        ListBook lb = new ListBook(BookFichierCsv.findBook());
        Book badd = new Book(email,firstname,lastname);
       if( lb.add(badd)){
           BookFichierCsv.sauvegarde(lb.getListe_b());  
           return " Ajout de " + badd.toString() +" effectué";
       } else {
           return " impossible d'ajouter l'email : "+ email +". Il est déjà présent";
       }
        
       
    }
    
    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody String bookDelete(@RequestParam(value="email", required=true) String email) throws IOException
    {
        
        LOG.info("TODO DELETE " + email);
        ListBook lb = new ListBook(BookFichierCsv.findBook());
        if(lb.delete(email)){
            BookFichierCsv.sauvegarde(lb.getListe_b());
            return "l'enregistrement avec l'identifiant " + email +" a bien été supprimé" ;
        } else {
            return "l'email "+email+" n'est pas présent dans notre base de données";
        }
    }

}
