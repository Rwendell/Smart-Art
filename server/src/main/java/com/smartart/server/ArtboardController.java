package com.smartart.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




@Controller    // This means that this class is a Controller
@RequestMapping(path="/artboard") // This means URL's start with /API (after Application path)

public class ArtboardController {

    @Autowired

    private ArtboardRepository artBoardRepository;

    @PostMapping(path= "/add") //Map ONLY POST Requests
    public @ResponseBody
    String addNewArtboard (@RequestParam String artboardName
            , @RequestParam String UserId) {



        try{artBoardRepository.findByArtboardName(artboardName).getArtboardName();}
        catch(NullPointerException ex)
        {

            Artboard n = new Artboard();
            n.setArtboardName(artboardName);
            n.setUserId(UserId);
            artBoardRepository.save(n);
            return "Saved New Board";
        }





        return "Board name taken";
    }
}
