package com.awok.moshin.intigraltestapp.NetworkLayer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.awok.moshin.intigraltestapp.Utilities.Constants;


/**
 * Created by mohsin on 9/9/2015.
 */
public class APIClient {
    private String TAG = "APIClient";
    private AsyncTaskWithDialog mTask;
    private Context mContext;
    private AsyncCallback mCallback;
    private int CONTEXT_INDEX = 0;
    private int URI_INDEX = 1;
    private int METHOD_INDEX = 2;
    private int PARAMS_INDEX = 3;

    public APIClient(Context context, AsyncCallback callback) {
        mContext = context;
        mCallback = callback;

    }

    public void GetMoviesByCategory(String category) {
        mTask = new AsyncTaskWithDialog();
        mTask.execute(mContext, Constants.API_SERVER_URL+category+"/?api_key="+Constants.API_KEY, "GET", null);
    }

    public class AsyncTaskWithDialog extends AsyncTask<Object, Void, String> {

        @Override
        protected void onPreExecute() {
            mCallback.onPreExecute();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected String doInBackground(Object... parameters) {
            String postResponse = "";
            try {
                Context context = (Context) parameters[CONTEXT_INDEX];
                String url = (String) parameters[URI_INDEX];
//                url = URLEncoder.encode(url);
                HTTPClient client = new HTTPClient(
                        context.getSharedPreferences(Constants.PREFS_NAME, 0));

                String params = (String) parameters[PARAMS_INDEX];
                System.out.println("URL: "+url);

                if (parameters[METHOD_INDEX] == "POST") {
                        postResponse = client.post(url, params);
                    return postResponse;

                } else if (parameters[METHOD_INDEX] == "GET")
                {
                    String resp = client.get(url);
                    Log.v(TAG, "resp: "+resp);
                    return resp;
                }

                else if (parameters[METHOD_INDEX] == "PUT")
                    return client.put(url, params);
                else if (parameters[METHOD_INDEX] == "DELETE") {
                    System.out.println("DELETE");
                    return client.delete(url, params);
                }
                return Constants.Success.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "EXCEPTION: " + e.toString());
            }
            return Constants.Exception.toString();
        }

        @Override
        protected void onPostExecute(final String response) {
            mTask = null;
            mCallback.onTaskComplete(response);
        }

        @Override
        protected void onCancelled() {
            mTask = null;
            mCallback.onTaskCancelled();
        }
    }

}
