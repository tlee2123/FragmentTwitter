package com.codepath.apps.mytwitterapp;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "T9HBVTKsM2MOjKbbA1l6MQ";       // Change this
    public static final String REST_CONSUMER_SECRET = "c9Xrz5HtII6jge88ufvD12C07FWm2J5B029eu1R7Et0"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://mytwitterapp"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeLine(RequestParams params, AsyncHttpResponseHandler handler) {
      String url = getApiUrl("statuses/home_timeline.json");
      client.get(url,  params, handler);
    }
    
    public void getMentions(RequestParams params, AsyncHttpResponseHandler handler) {
        String url = getApiUrl("statuses/mentions_timeline.json");
        client.post(url, params, handler);
      }
    
    public void postTweet(RequestParams params, AsyncHttpResponseHandler handler) {
      String url = getApiUrl("statuses/update.json");
      client.post(url, params, handler);
    }
    
    public void getProfileInfo(RequestParams params, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("account/verify_credentials.json");
    	client.get(url, params, handler);
    }
    
    public void getUserTimeline(RequestParams params, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/user_timeline.json");
    	Log.i("URL", url + "?" + params.toString());
    	client.get(url, params, handler);
    }
    
    public void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
    	client.get(getApiUrl(url), params, handler);
    }
    
    public void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
    	client.post(url, params, handler);
    }
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}