/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.hmac.client;

import com.webservices.common.Signature;
import java.security.SignatureException;

/**
 *
 * @author Sunny
 */
public class HMACClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Pre-shared Key (rotated with salt), shared out-of-band
        String preSharedKey = "SuperSecretPassword1337";
        
        //Send as Headers or in Body of Post message
        String clientId = "myClientAcctNumber";
        String clientTimestamp = "25dec201512:59:24";  //if timestamp is used
                                                       // instead of expiration
                                                       // date then receiver
                                                       // can calculate valid
                                                       // time for expiration
        String clientAuthZApp = "SalesServices";
        
        String nonce = "ValueCreatedOnServerSide";      // need client callable
                                                        // nonce service OR
                                                        // pre-defined parameters
                                                        // known between client
                                                        // and server
        
        try {
        
        String digest = Signature.calculateRFC2104HMAC(clientId+clientAuthZApp+clientTimestamp+nonce,preSharedKey );
        System.out.println("Serialized Encrypted HMAC: " + digest);
        
 
        } catch (SignatureException se){
            se.getStackTrace();
        } 
    }
    
}
