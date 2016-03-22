/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.jwt.client;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Sunny
 */
public class JWTClient {
    
    public static void main(String[] args){
        
    //Set Expiration, current time + 2 minutes (120,000 milliseconds) 
    long nowMillis = System.currentTimeMillis();
    Date current = new Date(nowMillis);
    long expMillis = nowMillis + 120000;
    Date exp = new Date(expMillis);
    System.out.println("current is " + current);
    System.out.println("exp is " + exp + " as milliseconds " + expMillis );
    
        
    //Key needs to be known and rotated when exchanged between clients   
    Key key;
        key = new AesKey("aR3llyG00dSecr3t".getBytes());
    
    JsonWebEncryption jwe = new JsonWebEncryption();
    //Header
    //{"typ":"JWT",
    //"alg":"HS256"}
    jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
    
    //Claims
    //{"iss":"myCompany",
    //"exp":1300819380,
    //"http://Easy_Rest_JWT/is_programmer":true}
    jwe.setPayload("{\"typ\":\"JWT\",\"alg\":\"HS256\"}{\"iss\":\"myCompany\",\"exp\":"+expMillis+"\",\"http://Easy_Rest_JWT/is_programmer\":true}");
    
    //Signature algorithm we will be using to sign the token
    jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
    jwe.setKey(key);
    
    try {
    String serializedJwe = jwe.getCompactSerialization();
    System.out.println("Serialized Encrypted JWE: " + serializedJwe);  

    } catch (JoseException je){
        System.out.println(je.getMessage());
    }
    
    }
}
