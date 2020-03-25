/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fis.com.vn.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ADMIN
 */
public class SendSMS {
    public static void main(String[] args) throws Exception {
        smsFpt("0984707337", "Test message");
    }
    
    public static void smsFpt(String phone, String message) throws Exception {
        String url = "http://app.sms.fpt.net/";
        String clientID = "70330a4f93fd0d832855f9a50f96355D56d5cdb7";
        String clientSecret = "f207ba78ba5791Fd91223A5ba3494f9c8ceeb7Cd4c038dA68b8f999a90a8817b3D5d03eb";
        String brandname = "FPT-CA";

        Client client = Client.create();
        client.getProperties().put(ClientConfig.PROPERTY_CHUNKED_ENCODING_SIZE, null);
        WebResource webResource = client.resource(url + "oauth2/token");

        String sessionID = getSessionID();

        Token token = new Token();
        token.setClient_id(clientID);
        token.setClient_secret(clientSecret);
        token.setGrant_type("client_credentials");
        token.setScope("send_brandname_otp");
        token.setSession_id(sessionID);

        ObjectMapper mapper = new ObjectMapper();
        String request = mapper.writeValueAsString(token);

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, request);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getEntity(String.class));
        }
        String output = response.getEntity(String.class);
        System.out.println(output);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(output);
        String accessToken = rootNode.path("access_token").asText();

        client = Client.create();
        webResource = client.resource(url + "api/push-brandname-otp");

        PushBrandnameOTP otp = new PushBrandnameOTP();
        otp.setAccess_token(accessToken);
        otp.setBrandName(brandname);
        otp.setMessage(DatatypeConverter.printBase64Binary(message.getBytes()));
        otp.setPhone(phone);
        otp.setSession_id(sessionID);

        request = objectMapper.writeValueAsString(otp);
        response = webResource.type("application/json").post(ClientResponse.class, request);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getEntity(String.class));
        }
        output = response.getEntity(String.class);
        System.out.println(output);
    }
    
    private static String getSessionID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return DigestUtils.sha256Hex(uniqueValue() + "#" + sdf.format(Calendar.getInstance().getTime()));
    }
    
    private static String uniqueValue() {
        SecureRandom numberGenerator = null;
        long MSB = 0x8000000000000000L;
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }
}
