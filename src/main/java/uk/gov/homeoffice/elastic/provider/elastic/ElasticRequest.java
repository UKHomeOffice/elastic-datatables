package uk.gov.homeoffice.elastic.provider.elastic;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;

public class ElasticRequest {

    @Value( "${searchUrl}" )
    private String searchUrl;

    public String buildPostJson(String search){
        String matchAll = json("matchAll.json");
        if(StringUtils.isEmpty(search)){
            return matchAll;
        }
        else{
            String matchTerm = json("matchTerm.json");
            matchTerm = String.format(matchTerm, search);
            return matchTerm;
        }
    }

    private String json(String fileName) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream(fileName), writer);
            String theString = writer.toString();
            return theString;
        } catch (Exception e) {
            return "";
        }
    }

    public String makeRequest(String json)throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(searchUrl);
        request.setEntity(new StringEntity(json));
        request.addHeader("content-type", "application/json");
        request.addHeader("accept", "application/json");
        HttpResponse response = client.execute(request);
        return IOUtils.toString(response.getEntity().getContent());
    }

}
