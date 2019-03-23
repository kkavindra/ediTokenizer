package com.securitybox.ediparser;

import com.securitybox.constants.Constants;
import com.securitybox.storage.CacheEntryObject;
import com.securitybox.tokenizer.Tokenizer;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CSV extends EdiDocument {
    String recordDelimeter,fieldDelimeter;
    public Tokenizer tokenizer = new Tokenizer();

    public CSV(String docType,String recordDelimeter,String fieldDelimeter) {
        super(Constants.DOCUMENT_TYPE_CSV);
        this.recordDelimeter=recordDelimeter;
        this.fieldDelimeter=fieldDelimeter;
    }

    @Override
    public String docuemntHandler(String method, JSONArray objectToBeTokenized, String message) throws JSONException, NoSuchAlgorithmException {
        return null;
    }


    @Override
    public String docuemntHandler(String method, JSONArray objectsToBeTokenized, String message, ArrayList<String> senderIds, ArrayList<String> receiverIds) throws JSONException, NoSuchAlgorithmException {
        String response="";
        //get line as objects
        JSONArray csvResponse = seperateElements(message,this.recordDelimeter);
        for(int i= 0 ; i < csvResponse.length(); i++ ) {
            CSVRecord csvRecord = new CSVRecord(fieldDelimeter,"",csvResponse.getString(i).toString());
            for(int j=0;j<csvRecord.getCount(); j++ ){
                for(int ja=0 ; ja < objectsToBeTokenized.length();ja++){
                    JSONObject requestedElements = objectsToBeTokenized.getJSONObject(ja);
                    if(requestedElements.getInt(Constants.CSV_DATA_ELEMENT_POSITION)== j){
                        //Create temproary JSON object to handle the current content.
                        JSONObject jsonObjTemp = new JSONObject();
                        jsonObjTemp.put("item",csvRecord.getField(j));
                        //initialize cashe object item with the data to be written
                        if(method.equalsIgnoreCase(Constants.TOKENIZER_METHOD_TOKENIZE)) {
                            CacheEntryObject cacheEntryObject = new CacheEntryObject() {
                                @Override
                                public ArrayList getSenderIds() {
                                    return super.getSenderIds();
                                }

                                @Override
                                public void setSenderIds(ArrayList senderIds) {
                                    super.setSenderIds(senderIds);
                                }

                                @Override
                                public ArrayList getReceiverIds() {
                                    return super.getReceiverIds();
                                }

                                @Override
                                public void setReceiverIds(ArrayList receiverIds) {
                                    super.setReceiverIds(receiverIds);
                                }

                                @Override
                                public void setObject(JSONObject object) {
                                    super.setObject(object);
                                }
                            };

                            //set the object to be included in the cacheEntryObject

                            cacheEntryObject.setObject(jsonObjTemp);
                            //call tokernization service with cacheObject to be tokenized
                            jsonObjTemp.put("item",tokenizer.tokenize(cacheEntryObject));

                        }else if(method.equalsIgnoreCase(Constants.TOKENIZER_METHOD_DETOKENIZE)) {
                            //get hte key to be retrived from current message
                            System.out.println("current key to be de-tokenized " + csvRecord.getField(j));
                            //retreive the cacheentry object from the cache
                            CacheEntryObject tmpCacheEntryObject = tokenizer.detokenize(Integer.valueOf(csvRecord.getField(j)));
                            //retierve the values from the object stored in the cache object.
                            if(tmpCacheEntryObject==null)
                                jsonObjTemp.put("item",csvRecord.getField(j));
                            else
                                jsonObjTemp.put("item",tmpCacheEntryObject.getObject().get("item"));

                        }else{
                            //DO nothing
                        }
                        //put back the retrieved values from the cached object/key received from tokenization to element position back
                        csvRecord.setFiled(j,jsonObjTemp.get("item").toString());
                    }

                }
            }

            response = response +  csvRecord.getRecord() + recordDelimeter;

        }


        return response ;
    }

    public JSONArray seperateElements(String input, String delimeter) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(input.split(delimeter)));
        JSONArray jsonArray= new JSONArray();

        String response="";
        int currentPos=0;
        //break the segment
        while(arrayList.size() > currentPos){
            jsonArray.put(currentPos,arrayList.get(currentPos));
            currentPos++;
        }

        //rebuild the response to be returned
        for(int i = 0;i<jsonArray.length();i++ ){
            if(i > 0)
                response +=  delimeter + jsonArray.get(i).toString();
            else
                response +=  jsonArray.get(i).toString();
        }
        return jsonArray;
    }

}

