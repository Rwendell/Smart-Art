package com.smartart.server;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.SecureRandom;

import com.smartart.server.ArtBoard;
import com.smartart.server.ArtBoardRepository;
import com.smartart.server.Users;
import com.smartart.server.UsersRepository;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/API") // This means URL's start with /API (after Application path)

public class MainController {

    @Autowired

    private UsersRepository usersRepository;

    @GetMapping(path= "/add") //Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String username
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Users n = new Users();
        n.setUsername(username);


        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[10];
        random.nextBytes(bytes);

        n.setSalt(new String(bytes));

        String salt = new String(bytes);
        String passSalt = salt + password;

        String hashed = DigestUtils.sha256Hex(passSalt);

        n.setHash(hashed);






        //TODO: Finish up with the hashing and salting








        usersRepository.save(n);
        return "Saved";
    }



}
