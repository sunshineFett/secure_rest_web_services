/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.jwt.server;

import java.security.Key;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Sunny
 */
public class JWTServer {

    public static void main(String[] args) {
        
        //simulate receiving of token from Authorization Header
        String serializedJwe;
        
        /**
        serializedJwe = new String("eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDL"
                + "UhTMjU2In0.EnJXtwAtg9xYjO01BJNSRRex21r37p1b"
                + "RT0wlOzjxxtvenuA7VHbyg.Lycc7ZffdmbPHFl0xFHd"
                + "Lg.Y8b9LIahPnKDSpT7NEQ05M_9eJbcvRX5nkNOK6kB"
                + "jm19Cl2dK9KxtGJ636dnBba5kb-eT16p8w_47S43cl3"
                + "Lxw.Jvknl5gG6L95AqfHld2AOA");
**/
        serializedJwe = new String("eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.3Zvsx0MdcpZeKnHSv0B7f46qmuFC-x3uZe7IFnbL84Oa3gbP8LSOMA.S616sGyzKSLoBI7h0QFBVw.q0i-KwFRxgsIJYWkWV_8Zxg-g2ObQysWNe66Y0kj7zSOCa91-EwWZd_ZPPQ9Qra_i-n5qoKLa7xgHw4FN511iz9ZYA91wZPOLPsux1cljMd2nIpoDEB1pG2TE0V5CE1QJFdbXkwaPLzJJWfSIOqPdA.4eAwrw7hyTGbDdH7-qMuHA\n");
        //Key is known/shared between client and server sides
        Key key;
        key = new AesKey("aR3llyG00dSecr3t".getBytes());

        //server-side
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setKey(key);
        
        try {
            jwe.setCompactSerialization(serializedJwe);
            System.out.println("Payload: " + jwe.getPayload());

        } catch (JoseException je) {
            System.out.println(je.getMessage());
        }
    }

}
