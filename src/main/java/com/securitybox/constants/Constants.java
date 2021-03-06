package com.securitybox.constants;

public class Constants {
    public final static String DOCUMENT_TYPE_EDIFACT="EDIFACT";
    public final static String EDIFACT_SEGMENT_NUMBER =  "segmentNumber";
    public final static String EDIFACT_DATA_ELEMENT_NUMBER = "dataElementNumber";
    public final static String EDIFACT_DATA_ELEMENT_POSITION = "dataElementPosition";
    public final static String EDIFACT_DATA_ELEMENT_LENGTH = "dataElementLength";
    public final static String DOCUMENT_TYPE_SINGLE_VALUE_TOKEN = "singleValueToken" ;
    public final static String EDIFACT_SEGMENT_NAME = "segmentName";
    public final static String EDIFACT_SEGMENT_QUALIFIER = "segmentQualifier";
    public static final String IGNITE_DEFAULT_VALUE_DELETED ="DELETED" ;
    public static final String ERROR_TOKEN_RETRIEVE = "Error retrieving token due to access restriction/token not available.";
    public static final String ERROR_LOGS_RETRIEVE = "Error retrieving audit logs.";

    public final static String EDIFACT_COMPONENT_DATA_ELEMENT_SEPERATOR=":";
    public final static String EDIFACT_DATA_ELEMENT_SEPERATOR="+";
    public final static String EDIFACT_SEGMENT_TERMINATOR="'";
    public final static String EDIFACT_DECIMAL_SEPERATOR=".";

    public final static String DOCUMENT_TYPE_CSV="CSV";
    public final static String CSV_DATA_ELEMENT_POSITION = "dataElementPosition";
    public final static String CSV_DATA_ELEMENT_LENGTH = "dataElementLength";

    public final static String DOCUMENT_TYPE_ALL="ALL";

    public final static String TOKENIZER_METHOD_TOKENIZE = "tokenize";
    public final static String TOKENIZER_METHOD_DETOKENIZE = "detokenize";


    public final static String IGNITE_DEFAULT_CACHE_NAME="securityBoxCache";
    public final static String IGNITE_DEFAULT_CACHE_OBJECT_STORE_NAME="item";

    public final static String DATE_FORMAT="MM/dd/yyyy hh:mm:ss";

    public final static String CACHE_ENTRY_OBJECT_NAME="CacheEntryObject";
    public final static String DATA_STORE_ACTION_ACCESSED="Client has access the token entry.";
    public final static String DATA_STORE_ACTION_DELETED="Client has access deleted token content.";
    public final static String DATA_STORE_ACTION_UPDATE="Client has updated the token entry";
    public final static String DATA_STORE_ACTION_ACCESED_LOGS="Client has accessed the log entry/entries";
    public final static String DATA_STORE_ACTION_DETOKENIZED="Client has detokenized the token.";
    public final static String DATA_ACTION_REMOVED_TOKEN_ENTRY_DATA="Client has removed the token entry details.";
    public final static String DATA_STORE_ACTION_CREATED ="Client has created the token entry.";
    public final static String DATA_STORE_ACTION_CREATTION_FAILURE ="Error storing token value.";
    public final static String DATA_STORE_ACTION_DENIED ="Client access denied due to access restrictions for the client.";

    public final static int DATA_STORE_CACHE_OBJECT_DEFAULT_VALID_DAYS=365;


    public static final String DOCUMENT_TYPE_JSON = "JSON";
}
