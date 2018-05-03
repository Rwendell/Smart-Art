package com.smartart.controller;


import com.smartart.model.Artboard;
import com.smartart.model.ArtboardRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author rwendell
 *
 *
 * This Class controls the Artboards
 */

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/artboard") // This means URL's start with /artboard (after Application path)

public class ArtboardController {

    @Autowired
    private ArtboardRepository artBoardRepository;


    /**
     * Creates a new Artboard
     * @param artboardName name of board
     * @param userId userID from board being created
     * @return JSON response fail or success
     */
    @PostMapping(path = "/add", produces = "application/json") //Map ONLY POST Requests
    public @ResponseBody
    String addNewArtboard(@RequestParam String artboardName
            , @RequestParam Long userId) {


        try {
            //noinspection ResultOfMethodCallIgnored    This makes sure the warning is suppresed
            artBoardRepository.findByArtboardName(artboardName).getArtboardName();
        } catch (NullPointerException ex) {
            Artboard n = new Artboard();
            n.setArtboardName(artboardName);
            n.setUserId(userId);
            artBoardRepository.save(n);

            JSONObject success = new JSONObject();
            success.put("response", "successfully added new board");
            success.put("artboard name", n.getArtboardName());
            success.put("userId", n.getUserId());

            return success.toString();
        }

        JSONObject fail = new JSONObject();
        fail.put("response", "board name taken");

        return fail.toString();
    }




    @GetMapping(path = "/getboards", produces = "application/json")
    public @ResponseBody
    String getBoards(@RequestParam Long artboardID){

        JSONObject response = new JSONObject();

        response.put("board name",  artBoardRepository.findByArtboardId(artboardID));
        response.put("response", "Success");

        return response.toString();


    }

    @GetMapping(path = "/getpublic", produces = "application/json")
    public @ResponseBody
    String getPublic(){

        List<Long> boards = new CopyOnWriteArrayList<>();

        for (Artboard a : artBoardRepository.findAll()){

            if (a.getIsPrivate() == (long) 0){
                boards.add(a.getArtboardId());
            }

        }

        JSONObject resp = new JSONObject();

        resp.put("boards", boards);
        resp.put("response", "success");




        return resp.toString();
    }

    @PostMapping(path = "/makeprivate", produces = "application/json")
    public @ResponseBody
    String makePrivate(@RequestParam Long artboardID){

        Artboard a = artBoardRepository.findByArtboardId(artboardID);
        a.setIsPrivate((long)1);

        JSONObject resp = new JSONObject();

        resp.put("response", "successful made board:" + a.getArtboardId() + " private");
        return resp.toString();
    }

    @PostMapping(path = "/makepublic", produces = "application/json")
    public @ResponseBody
    String makePublic(@RequestParam Long artboardID){

        Artboard a = artBoardRepository.findByArtboardId(artboardID);
        a.setIsPrivate((long)0);

        JSONObject resp = new JSONObject();

        resp.put("response", "successful made board:" + a.getArtboardId() + " public");
        return resp.toString();
    }

    @GetMapping(path = "/search", produces = "application/json")
    public @ResponseBody
    String search(@RequestParam String artboardName){

        List<String> boards = new CopyOnWriteArrayList<>();

        for (Artboard a : artBoardRepository.findAll()){

            if (a.getArtboardName().equals(artboardName)){
                boards.add(a.getArtboardName());
            }

        }

        JSONObject resp = new JSONObject();

        resp.put("results", boards);
        return resp.toString();


    }

    @GetMapping(path = "/share", produces = "application/json")
    public @ResponseBody
    String share(@RequestParam Long artboardID, Long sharedUserID){

        Artboard a = artBoardRepository.findByArtboardId(artboardID);

        Artboard n = new Artboard();
        n.setArtboardName(a.getArtboardName());
        n.setArtboardId(a.getArtboardId());
        n.setUserId(sharedUserID);
        n.setIsPrivate((long)0);


        JSONObject resp = new JSONObject();

        resp.put("response", "successfully shared board");

        return resp.toString();
    }


}

