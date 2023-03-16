package slfdemo.sidecar.springboot;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Utility {

    public static String writePrettyJson(String data){

        String result = "";

        try{
            ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
            result = writer.writeValueAsString(data);

        }catch(Exception ex){
            System.out.println(ex.getMessage().toString());
        }

        return result;
    }



}
