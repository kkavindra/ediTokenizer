package com.securitybox.tokenizer;

import com.securitybox.constants.Constants;
import com.securitybox.models.AccessEntry;
import com.securitybox.models.CacheEntryObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TokenizerTest {


    public static void main(String args[]) throws JSONException {
        Tokenizer tokenizer = new Tokenizer();
        final String sender = "clientA";
        final ArrayList<String> receiver = new ArrayList<String>();
        receiver.add("clientB");
        CacheEntryObject newCaObj_1,newCaObj_2,newCaObj_3,newCaObj_4;
        final String cacheValue="My Value";
        JSONObject jsonObjTemp = new JSONObject();
        jsonObjTemp.put(Constants.IGNITE_DEFAULT_CACHE_OBJECT_STORE_NAME,cacheValue);

        try {
            //token based on integer type
            String token = tokenizer.tokenize(cacheValue,15,sender,receiver);
            newCaObj_1 = tokenizer.deTokenize(token,"C1");
            assertEquals(cacheValue, newCaObj_1.getJsonObject().get(Constants.IGNITE_DEFAULT_CACHE_OBJECT_STORE_NAME).toString());

            //token based on String type
            String tokenStr_1 = tokenizer.tokenize(cacheValue,127,sender,receiver);

            newCaObj_2 = tokenizer.deTokenize(tokenStr_1,"C1");
            assertEquals(cacheValue, newCaObj_2.getJsonObject().get(Constants.IGNITE_DEFAULT_CACHE_OBJECT_STORE_NAME).toString());

            //tokenizer test mixed values, insert as a cache object as integer, retireve as a string key
            String tokenStr_2 = tokenizer.tokenize(cacheValue,45,sender,receiver);
            newCaObj_3 = tokenizer.deTokenize(tokenStr_2,"C1");
            assertEquals(cacheValue, newCaObj_3.getJsonObject().get(Constants.IGNITE_DEFAULT_CACHE_OBJECT_STORE_NAME).toString());
            //tokeizer test with client access log

            String tokenStr_4 = tokenizer.tokenize(cacheValue,67,sender,receiver);
            tokenizer.deTokenize(tokenStr_4, "clientId_1");
            tokenizer.deTokenize(tokenStr_4, "clientId_2");
            CacheEntryObject newCaObj_6 = tokenizer.deTokenize(tokenStr_4,"new clinet");

            AccessEntry accessEntry =(AccessEntry) newCaObj_6.getAccessLogs().get(0);
            System.out.println("Access Log " + accessEntry.toString());


        }catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

    }

}