package com.quentin.book.gestionfichierplat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;



public class BookFichierCsv 
{

    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String BOOK_FILE_NAME = "book.csv";
    private final static char SEPARATOR = ';' ;

    public static List<Book> findBook() {
        try {
            File file = new File(RESOURCES_PATH + BOOK_FILE_NAME);
            FileReader FileR = new FileReader(file);
            @SuppressWarnings("resource")
            CSVReader csvReader = new CSVReader(FileR, SEPARATOR);

            List<String[]> data = new ArrayList<String[]>();


            String[] nextLine = null;
            while ((nextLine = csvReader.readNext()) != null) {
                int size = nextLine.length;

                // ligne vide
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }

                data.add(nextLine);
            }

            List<Book> books = new ArrayList<Book>();
            for(String[] bdata : data){
                String email = bdata[0];
                String firstname = bdata[1];
                String lastname = bdata[2];

                books.add(new Book(email,firstname,lastname));
            }
            return books;
        } catch (Exception e){
            return null;
        }
    }
    
   public static void sauvegarde(List<Book> book) throws IOException{
       File file = new File(RESOURCES_PATH + BOOK_FILE_NAME);
       FileWriter fw = new FileWriter(file,false); 
       
       for (Book book2 : book)
       {
           fw.append(book2.toString2()+"\n");
       }       
       fw.close();  
   }

}
