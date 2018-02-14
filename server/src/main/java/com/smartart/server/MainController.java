package com.smartart.server;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/API") // This means URL's start with /API (after Application path)

public class MainController {

    @Autowired

    private UserRepository UserRepository;

    @PostMapping(path= "/adduser") //Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String username
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request



        try {
            UserRepository.findByUsername(username).getUsername();


        } catch (NullPointerException ex){
            User n = new User();
            n.setUsername(username);  //sets Username
            n.setAdmin(0);      //default to 0 -> false
            String salt_string = RandomStringUtils.random(8);
            n.setSalt(salt_string);
            String passSalt = salt_string + password;
            String hashed = DigestUtils.sha256Hex(passSalt);
            n.setHash(hashed);
            UserRepository.save(n);
            return "Saved New User";
        }


        return "Username Taken";



        /*
            HOW TO RETRIEVE A PASSWORD

            Retrieve the user's salt and hash from the database.
            Prepend the salt to the given password and hash it using the same hash function.
            Compare the hash of the given password with the hash from the database.
            If they match, the password is correct.
            Otherwise, the password is incorrect.
         */
    }


    @Autowired

    private ArtboardRepository artBoardRepository;

    @PostMapping(path= "/addboard") //Map ONLY POST Requests
    public @ResponseBody String addNewArtboard (@RequestParam String artboardName
            , @RequestParam String UserId) {



        try{artBoardRepository.findByArtboardName(artboardName).getArtboardName();}
        catch(NullPointerException ex){
            Artboard n = new Artboard();
            n.setArtboardName(artboardName);
            n.setUserId(UserId);
            artBoardRepository.save(n);
            return "Saved New Board";
        }





        return "Board name taken";
    }










}
