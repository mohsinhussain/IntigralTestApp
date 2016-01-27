package com.awok.moshin.intigraltestapp.NetworkLayer;

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by mohsin on 9/9/2015.
 */
public class HTTPClient{
    public static String TAG = "HTTPClient";
    SharedPreferences mSharedPrefs;
    static int TIMEOUT_VALUE = 5000;



    public HTTPClient(SharedPreferences preferences)
    {
        mSharedPrefs = preferences;
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    public String get(String reqapi) throws IOException
    {
        URL url = new URL(reqapi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setRequestMethod("GET");
        String resp = readURL(con);
        Log.v(TAG, "GET Response: " + resp);
        return resp;
    }

    public String post(String reqapi,String params) throws IOException
    {
        URL url;
        URLConnection urlConn;
        url = new URL (reqapi);
        urlConn = url.openConnection();
        urlConn.setDoInput(true);
        urlConn.setDoOutput(true);
        urlConn.setUseCaches(false);
        urlConn.setRequestProperty("Content-Type", "text/plain");
        urlConn.connect();
        DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream ());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();
        String resp = readURL(urlConn);
        Log.v(TAG, "POST Response: " + resp);
        return resp;
    }

    public String put(String reqapi,String params) throws IOException
    {
        URL url = new URL(reqapi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setRequestMethod("PUT");
        if(params!=null){
            con.setRequestProperty("Parameters", params);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream ());
            wr.writeBytes(params.toString());
            wr.flush();
            wr.close();
        }
        String resp = readURL(con);
        Log.v(TAG, "PUT Response: " + resp);
        return resp;
    }

    public String delete(String reqapi, String params) throws IOException
    {
        URL url = new URL(reqapi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Length", "0");
        con.setRequestProperty("Parameters", params);
        String resp = readURL(con);
        Log.v(TAG, "DELETE Response: " + resp);
        return resp;
    }

    private static String readURL(URLConnection connection){

        String str = new String();
        try
        {
            HttpURLConnection urlcon = (HttpURLConnection)connection;
            urlcon.setConnectTimeout(TIMEOUT_VALUE);
            urlcon.setReadTimeout(TIMEOUT_VALUE);
            InputStream is = urlcon.getInputStream();
            str = streamToString(is);
            return str;
        }
        catch (SocketTimeoutException e) {
            Log.i(TAG, "Socket More than " + TIMEOUT_VALUE + " elapsed.");
            return "";
        }
        catch (ConnectTimeoutException e){
            Log.i(TAG, "Connect More than " + TIMEOUT_VALUE + " elapsed.");
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Error: "+e.toString());
            Log.e(TAG, "Message: "+e.getMessage().toString());
        }
        return str;
    }

    // Reads an InputStream and converts it to a String.
    private static String streamToString(InputStream stream) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
