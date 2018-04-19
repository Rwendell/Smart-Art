package com.smartart.utils;

import java.io.*;

/**
 * @author rwendell
 *
 * This Class is used to manipulate the image in order to change it from an image to a byte array and back
 *
 */
public class ImgUtils {

    /**
     *  This method takes an image and converts it into a byte array
     * @param path the path to the image file
     * @return returns a byte array of the image
     */
    public static byte[] fileToByteArr(String path){
        File file = new File(path);

        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
        catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        return b;

    }

    /**
     *This method takes a byte array and converts it to an image which is saved
     * @param b byte array of the image
     * @param path location to save the file
     */
    public static void byteArrtoFile(byte[] b, String path){

        try {
            FileOutputStream fos = new FileOutputStream(path);
            //String strContent = "Write File using Java ";

            fos.write(b);
            fos.close();
        }
        catch(FileNotFoundException ex)   {
            System.out.println("FileNotFoundException : " + ex);
        }
        catch(IOException ioe)  {
            System.out.println("IOException : " + ioe);
        }

    }








}






