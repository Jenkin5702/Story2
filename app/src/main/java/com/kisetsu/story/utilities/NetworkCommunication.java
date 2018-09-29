package com.kisetsu.story.utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kisetsu on 18-9-22.
 * For network.
 */

public class NetworkCommunication {

    public static String send(String param){
        StringBuilder result= new StringBuilder();
        try {
            URL url = new URL("http://127.0.0.1:8080");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);//Whether write data to connection.
            connection.setDoOutput(true);//Whether read data from connection.
            connection.setUseCaches(false);//Whether use cache.
            connection.setInstanceFollowRedirects(true);//Whether automatically execute http redirect.
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//Set request property.

            DataOutputStream out=new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStreamReader in=new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(in);
                String inputLine="";
                while((inputLine=bufferedReader.readLine())!=null){
                    result.append(inputLine).append('\n');
                }
                in.close();
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
