package com.example.card_server.strategy;

import com.example.card_server.dto.CardDTOInterface;
import com.example.card_server.dto.MasterCardDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service()
public class MasterCardStrategy extends ParentMainStrategy {

    public static final String CARD_ID_ACCT_NUMBER = "{\"cardId\": \"{{acctNumber}}\"";
    @Value("${master_card_gate.path}")
    private String masterCardGatePath;

    @Override
    public CardDTOInterface fetchData(String acctNumber) throws IOException {
        URL url = new URL(masterCardGatePath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        String jsonInputString = CARD_ID_ACCT_NUMBER.replace("{{acctNumber}}", acctNumber);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(
                streamReader
        );
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        return this.objectHandler.handleObject(content.toString(), MasterCardDTO.class);
    }
}
