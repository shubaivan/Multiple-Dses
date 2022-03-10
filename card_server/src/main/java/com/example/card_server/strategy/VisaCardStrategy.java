package com.example.card_server.strategy;

import com.example.card_server.dto.CardDTOInterface;
import com.example.card_server.dto.VisaCardDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class VisaCardStrategy implements MainStrategy {

    @Value("${visa_gate.path}")
    private String visaCardGatePath;

    @Override
    public CardDTOInterface fetchData(String acctNumber) throws IOException {
        URL url = new URL(visaCardGatePath);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", "Bearer token-from-application-config");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        String jsonInputString = "{\"acctNumber\": \"{{acctNumber}}}\"".replace("{{acctNumber}}", acctNumber);

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

        ObjectMapper mapper = new ObjectMapper();
        VisaCardDTO masterCardDTO = mapper.readValue(content.toString(), VisaCardDTO.class);

        return masterCardDTO;
    }
}
