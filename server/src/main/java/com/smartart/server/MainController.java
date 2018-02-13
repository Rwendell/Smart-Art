package com.smartart.server;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.SchemaOutputResolver;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/API") // This means URL's start with /API (after Application path)

public class MainController {

    @Autowired

    private usersRepository usersRepository;

    @GetMapping(path= "/adduser") //Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String username
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request


        //TODO: find out how to fix this error!!!


        users n = new users();

        n.setAdmin(0);
        n.setUsername(username);  //sets Username



        SecureRandom random = new SecureRandom(); //sets Salt
        byte salt[] = new byte[10];
        random.nextBytes(salt);

        n.setSalt(new String(salt));
        System.out.println(Arrays.toString(salt));
        String salt_ret = n.getSalt();
        System.out.println(Arrays.toString(salt_ret.toCharArray()));



        String saltString = new String(salt); //appends salt to beginning of password and hashes
        String passSalt = saltString + password;
        String hashed = DigestUtils.sha256Hex(passSalt);
        n.setHash(hashed);

        usersRepository.save(n);
        return "Saved New User";

        /*
            HOW TO RETRIEVE A PASSWORD

            Retrieve the user's salt and hash from the database.
            Prepend the salt to the given password and hash it using the same hash function.
            Compare the hash of the given password with the hash from the database.
            If they match, the password is correct.
            Otherwise, the password is incorrect.
         */
    }

/*
    @Autowired

    private artboardRepository artBoardRepository;

    @GetMapping(path= "/addboard") //Map ONLY GET Requests
    public @ResponseBody String addNewArtboard (@RequestParam String artboardName
            , @RequestParam String UserId) {

        artboard n = new artboard();

        n.setArtboardName(artboardName);
        n.setUserId(UserId);

        String artboardUUID = UUID.randomUUID().toString(); //sets UserId
        artboardUUID = artboardUUID.replaceAll("-", "");

        n.setUserId(artboardUUID);

        artBoardRepository.save(n);
        return "Saved New Board";

    }



*/






}
