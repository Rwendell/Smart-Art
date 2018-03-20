package com.smartart.server;



import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/artboard") // This means URL's start with /artboard (after Application path)

public class ArtboardController {

    @Autowired
    private ArtboardRepository artBoardRepository;


    //TODO: Dynamically add more handlers



    /*
    A bit confused with this

    @Autowired
    private WebSocketConfig config;
    */



    @PostMapping(path= "/add", produces = "application/json") //Map ONLY POST Requests
    public @ResponseBody
    String addNewArtboard (@RequestParam String artboardName
            , @RequestParam String userId) {



        try{
            //noinspection ResultOfMethodCallIgnored    This makes sure the warning is suppresed
            artBoardRepository.findByArtboardName(artboardName).getArtboardName();
        }
        catch(NullPointerException ex)
        {
            Artboard n = new Artboard();
            n.setArtboardName(artboardName);
            n.setUserId(userId);
            artBoardRepository.save(n);

            JSONObject success = new JSONObject();
            success.put("response","successfully added new board");
            success.put("artboard name",n.getArtboardName());
            success.put("userId",n.getUserId());

            //should add a new handler on creation of board


            //not sure what i'm doing here
            /*WebSocketHandlerRegistry registry = new WebSocketHandlerRegistry() {
                @Override
                public WebSocketHandlerRegistration addHandler(WebSocketHandler webSocketHandler, String... paths) {
                    return null;
                }
            };

            */
            //I don't think this gets added
            //registry.addHandler(new SocketHandler(), "/" + n.getArtboardId());


            return success.toString();
        }

        JSONObject fail = new JSONObject();
        fail.put("response", "board name taken");

        return fail.toString();
    }
}
