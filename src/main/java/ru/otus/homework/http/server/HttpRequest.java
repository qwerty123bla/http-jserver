package ru.otus.homework.http.server;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String rawRequest;
    private String method;
    private String uri;
    private Map<String, String> parameters;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.parameters = new HashMap<>();
        parse();
    }

    public String getRawRequest(String key) {
        return this.parameters.get(key);
    }
    
    private void parse() {
        int startIndex = rawRequest.indexOf(' ');
        int endIndex = rawRequest.indexOf(' ', startIndex + 1);
        method = rawRequest.substring(0, startIndex);
        uri = rawRequest.substring(startIndex + 1, endIndex);


        if (this.uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            this.uri = tokens[0];
            String[] paramsPairs = tokens[1].split("[&]");

            for (String o: paramsPairs) {
                String[] keyValue = o.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public void info(boolean showRawRequest) {
        System.out.println("METHOD: " + method);
        System.out.println("URI: " + uri);
        if (showRawRequest) {
            System.out.println(rawRequest);
        }
    }

    public String getUri() {
        return uri;
    }
}
