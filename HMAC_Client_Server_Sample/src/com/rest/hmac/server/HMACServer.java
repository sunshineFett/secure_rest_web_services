/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.hmac.server;

import com.webservices.common.Signature;
import java.security.SignatureException;

/**
 *
 * @author Sunny
 */
public class HMACServer {

    private int checksum;

    public static void main(String[] args) {

        String receivedHmac;

        //simulate receiving of token from Authorization Header
        receivedHmac = new String("wnG3+WPyq5UECAdI83ugXKb5lKtr7e3wf7xx5DD9orA=");

        //Pre-shared Key (rotated with salt), shared out-of-band
        String preSharedKey = "SuperSecretPassword1337";

        //Receive these values as Headers or in Body of Post message in client Request
        String clientId = "myClientAcctNumber";
        String clientTimestamp = "25dec201512:59:24";  //if timestamp is used
        // instead of expiration
        // date then receiver
        // can calculate valid
        // time for expiration
        String clientAuthZApp = "EBTGovServices";

        String nonce = "ValueCreatedOnServerSide";      // need client callable
        // nonce service OR
        // pre-defined parameters
        // known between client
        // and server

        try {

            String digest = Signature.calculateRFC2104HMAC(clientId + clientAuthZApp + clientTimestamp + nonce, preSharedKey);
            System.out.println("Serialized Encrypted HMAC: " + digest);

            int calculatedCheckSumServerSide = digest.hashCode();
            int receivedCheckSumClientSide = receivedHmac.hashCode();

            System.out.println(calculatedCheckSumServerSide);
            System.out.println(receivedCheckSumClientSide);
   
            System.out.println("Verfication that received HMAC matches calculated HMAC: " + digest.equals(receivedHmac));

        } catch (SignatureException se) {
            se.getStackTrace();
        }

    }
}
