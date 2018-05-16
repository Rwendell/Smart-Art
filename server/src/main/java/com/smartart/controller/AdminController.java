package com.smartart.controller;

import com.smartart.model.Artboard;
import com.smartart.model.ArtboardRepository;
import com.smartart.model.User;
import com.smartart.model.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author rwendell
 */
@ComponentScan
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/admin") // This means URL's start with /artboard (after Application path)
public class AdminController {

    @Autowired
    private ArtboardRepository artBoardRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping(path = "/viewallboards", produces = "application/json")
    public @ResponseBody
    String viewAll(){

        List<Long> boards = new CopyOnWriteArrayList<>();
        JSONObject resp = new JSONObject();

        for(Artboard a : artBoardRepository.findAll()){
            boards.add(a.getArtboardId());
        }

        resp.put("boards",boards);
        resp.put("response","success");
        return resp.toString();
    }

    @PostMapping(path = "/deleteboard", produces = "application/json")
    public @ResponseBody
    String deleteBoard(Long boardID){

        try {
            Artboard a = artBoardRepository.removeByArtboardId(boardID);
        }
        catch (NullPointerException ex){
            JSONObject fail = new JSONObject();
            fail.put("response","no board with that board ID");
            return fail.toString();
        }

        JSONObject success = new JSONObject();
        success.put("response", "successfully removed board");
        return success.toString();

    }



    @PostMapping(path = "/deletuser", produces = "application/json")
    public @ResponseBody
    String deleteUser(Long userID){

        try {
            User u = userRepository.removeByUserId(userID);
        }
        catch (NullPointerException ex){
            JSONObject fail = new JSONObject();
            fail.put("response","no user with that user ID");
            return fail.toString();
        }

        JSONObject success = new JSONObject();
        success.put("response", "successfully removed user");
        return success.toString();

    }


    /*                         Could ban or remove user                                     */
    @PostMapping(path = "/banuser", produces = "application/json")
    public @ResponseBody
    String banUser(Long userID){

        try {
            User u = userRepository.findByUserId(userID);
            u.setBanned((long)1);
        }
        catch (NullPointerException ex){
            JSONObject fail = new JSONObject();
            fail.put("response","no user with that user ID");
            return fail.toString();
        }

        JSONObject success = new JSONObject();
        success.put("response", "successfully banned user");
        return success.toString();

    }




}
