package com.quentin.services;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
public class Helloworld
{
    private static final Logger LOG = getLogger(Helloworld.class);
    
    @RequestMapping("/home")
    public String index() {
        LOG.info("Page d'accueil du micro service");
        return "Bonjour Nexway";   
    }
}

