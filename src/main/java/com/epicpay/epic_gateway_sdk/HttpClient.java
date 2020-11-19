package com.epicpay.epic_gateway_sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import javax.net.ssl.HttpsURLConnection;

class HttpClient {
    static ExecutorService pool = Executors.newCachedThreadPool();

    private String apikeyid;
    private String apikeypasswd;
    private String apiurl;
    HttpClient (String apikeyid, String apikeypasswd, String apiurl) {
        this.apikeyid = apikeyid;
        this.apikeypasswd = apikeypasswd;
        this.apiurl = apiurl;
    }

    public CompletableFuture<String> getAsyncHttp(final String urlmethod) {
        URL url;
        try {
            URI uri = URI.create(apiurl);
            uri = uri.resolve(urlmethod);
            url = uri.toURL();
        }
        catch (MalformedURLException e) {
            System.err.println("EpicGatewaySDK: Invalid URL: " + apikeyid + urlmethod);
            e.printStackTrace();
            throw new RuntimeException("An invalid URL was generated within the EpicPay SDK. This request has failed.");
        }
        Supplier<String> httpFetch = new Supplier<String>() {
            public String get() {
                try {
                    // Careful here. Using HTTPSUrl on HTTP or HTTPUrl on HTTPS will hang.
                    HttpURLConnection conn;
                    if (url.getProtocol().equals("https")) {
                        conn = (HttpsURLConnection)url.openConnection();
                        
                    } else {
                        System.err.println("WARN: Using HTTP instead of HTTPS on URL: " + url.toString());
                        conn = (HttpURLConnection)url.openConnection();
                    }
                    conn.setDoOutput(false);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent", "EpicPay Java SDK 1.0");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer buf = new StringBuffer();
                    int ch;
                    // pull data out of the input stream
                    while ((ch = in.read()) != -1) {
                        buf.append((char)ch);
                    }

                    in.close(); // does this need to be in a FINALLY block?
                    conn.disconnect();
                    return buf.toString();
                    
                } catch (IOException e) {
                    System.out.println("Fetch Failed");
                    // What's our failure handling strategy? Throw all the way up to the SDK call?
                    throw new RuntimeException(e);
                }
            }
        };
        return CompletableFuture.supplyAsync(httpFetch, pool);
    }
   public CompletableFuture<String> postAsyncHttpBasicAuth(final String urlmethod, String data) {
        String b64keys = Base64.getEncoder().encodeToString( // string concats
        (apikeyid + ":" + apikeypasswd).getBytes(StandardCharsets.UTF_8));
        return postAsyncHttp(urlmethod, data, "Basic " + b64keys);
    }
    public CompletableFuture<String> postAsyncHttpBearerAuth(final String urlmethod, String data, String token) {
        String bearerString = "Bearer " + token;
        return postAsyncHttp(urlmethod, data, bearerString); 
    }
    public CompletableFuture<String> postAsyncHttp(final String urlmethod, String data, String auth) {
        URL url;
        // java stores strings as utf-16 by default.
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        try {
            URI uri = URI.create(apiurl);
            uri = uri.resolve(urlmethod);
            url = uri.toURL();
        }
        catch (MalformedURLException e) {
            System.err.println("EpicGatewaySDK: Invalid URL: " + apiurl + urlmethod);
            e.printStackTrace();
            throw new RuntimeException("An invalid URL was generated within the EpicPay SDK. This request has failed.");
        }
        Supplier<String> httpFetch = new Supplier<String>() {
            public String get() {
                try {
                    // Careful here. Using HTTPSUrl on HTTP or HTTPUrl on HTTPS will hang.
                    // (I assume we'll only be touching HTTPS though so this isn't a concern)
                    HttpURLConnection conn;
                    if (url.getProtocol().equals("https")) {
                        conn = (HttpsURLConnection)url.openConnection();
                        
                    } else {
                        System.err.println("WARN: Using HTTP instead of HTTPS on URL: " + url.toString());
                        conn = (HttpURLConnection)url.openConnection();
                    }
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Cache-Control", "no-cache");
                    conn.setRequestProperty("User-Agent", "EpicPayJavaSDK/0.0.1"); // FIXME: one var for useragent
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("charset", "utf-8");
                    conn.setRequestProperty("Content-Length", Integer.toString(dataBytes.length));
                    
                    conn.setRequestProperty("Authorization", auth);
                    // POST data is sent as part of a stream
                    try (DataOutputStream ws = new DataOutputStream(conn.getOutputStream())) {
                        ws.write(dataBytes);
                    }

                    //BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    StringBuffer buf = new StringBuffer();
                    int ch;
                    // pull data out of the input stream after the POST is sent
                    // (else waiting for more input could block indefinitely, as server waits for POST data to arrive)
                    while ((ch = in.read()) != -1) {
                        buf.append((char)ch);
                    }
                    
                    in.close();
                    conn.disconnect();
                    return buf.toString();
                    
                } catch (IOException e) {
                    System.out.println("POST Failed");
                    // What's our failure handling strategy? Throw all the way up to the SDK call?
                    throw new RuntimeException(e);
                }
            }
        };
        return CompletableFuture.supplyAsync(httpFetch, pool);
    }
}
