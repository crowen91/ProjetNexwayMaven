package com.quentin.book.gestionfichierplat;


import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ListBook 
{
    private static final Logger LOG = getLogger(ListBook.class);    
    private List<Book> liste_b;
    

    public ListBook (){
        this.liste_b = new ArrayList<Book>();
    }
    
    public ListBook (List<Book> liste_b ){
        this.liste_b = liste_b;
    }
    
    public ListBook listBookwithparam(String email, String firstname, String lastname){
        ListBook recherche = new ListBook();
            
        if( email.compareTo("null") != 0){
            //Recherche des informations avec l'email
            boolean present = false;
            for (int i = 0; i < liste_b.size() && !present; i++)
            {
                if(liste_b.get(i).getEmail().equals(email)){
                    LOG.info(liste_b.get(i).toString());
                    recherche.liste_b.add(liste_b.get(i));
                    present = true;
                }
            }
            
            return recherche;
        }else{
        // recherche liste des emails 
            
            for (int i = 0; i < liste_b.size(); i++)
            {
              
                if( liste_b.get(i).getFirstname().compareTo(firstname) == 0
                    || liste_b.get(i).getLastname().compareTo(lastname) == 0)
                    
                {
                    LOG.info(liste_b.get(i).toString());
                    recherche.liste_b.add(liste_b.get(i));
                } 
            }
            return recherche;
        }
    }
    
    public boolean add(Book book){
        
        if (listBookwithparam(book.getEmail(),null,null).getListe_b().size()<1){
                liste_b.add(book);
                return true;
        } else {
            return false;
        }
        
    }
 
    public int positionEmail(String email){
        
        boolean present = false;
        int pos = -1;
        for (int i = 0; i < liste_b.size() && !present; i++)
        {
            if(liste_b.get(i).getEmail().equals(email)){
                present = true;
                pos=i;
            }
        }
        return pos;        
    }
    
    public boolean modif(Book book){
        
        
        int pos = positionEmail(book.getEmail());
        if (pos == -1){
                 return false;
        } else {
            if(book.getFirstname().compareTo("null") !=0 ){
                liste_b.get(pos).setFirstname(book.getFirstname());
            } 
            if(book.getLastname().compareTo("null") !=0 ){
                liste_b.get(pos).setLastname(book.getLastname());
            } 

            return true;
        }
        
    }
    
    public boolean delete(String email){
        
        int pos = positionEmail(email);
        if ( pos == -1 ){
            return false;
        } else {
            liste_b.remove(pos);
            return true;
        }
        
    }
    
    public List<Book> getListe_b()
    {
        return liste_b;
    }

    public void setListe_b(List<Book> liste_b)
    {
        this.liste_b = liste_b;
    }

    public String toString() {
        
        if(liste_b.size() == 0 ){
            
            return "Aucune adresse n'est disponible";
        }
        
        String aff = new String();
        
        for (Book book : liste_b)
        {
            aff = aff + book.toString();
        }
        return aff;
    }
    
}
