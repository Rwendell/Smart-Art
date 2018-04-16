package com.smartart.server;

import com.smartart.utils.ImgUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author rwendell
 */
@SpringBootApplication
public class ServerApplication {


    public static void main(String[] args) {

        ImgUtils.byteArrtoFile(ImgUtils.fileToByteArr(
                "/resources/board/1.png"),"/resources/board/test.png");

        SpringApplication.run(ServerApplication.class, args);
    }

}
