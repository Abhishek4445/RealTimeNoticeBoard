package tk.developeramit.realtimenoticeboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.attr.start;

/**
 * Created by developer.amit on 10/3/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;
    String responseString = "";
    AlertDialog dialog;

    public BackgroundWorker(Context context) {
        this.context = context;
    }

    protected String doInBackground(String... params) {

        String server_URL = "http://bigcommerce.ml/register.php";
        String server_login_URL = "http://bigcommerce.ml/login.php";
        String server_message_URL = "http://bigcommerce.ml/message_insert.php";
        String type = params[0];
        String line = "";

        //For Registration
        if (type.equals("registration")) {

            Log.e("ENTRY Reg", "yes");////
            String first_name = params[1];
            String last_name = params[2];
            String username = params[3];
            String password = params[4];
            Log.e("Data taken", "yes");////

            try {
                //create URL
                URL url = new URL(server_URL);

                //create URL connection via Http
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //For Sending Request
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                String postingData = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(first_name, "UTF-8") + "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(last_name, "UTF-8") + "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                Log.e("URLTOSEND: ", server_URL + "" + postingData);

                bufferedWriter.write(postingData);
                Log.e("Data Sent to SERVER", "Yes");////
                bufferedWriter.flush();
                bufferedWriter.close();

                //For Reading Response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    responseString += line;
                }
                Log.e("DATA RECEIVED", "Yes");
                bufferedReader.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException ex) {
                Log.e("ERROR MURLE: ", "" + ex);
                ex.printStackTrace();
            } catch (IOException e) {
                Log.e("ERROR IOE: ", "" + e);
                e.printStackTrace();
            }
        }
        //End of Registration

        //For Login
        if (type.equals("login")) {
            String username_login = params[1];
            String password_login = params[2];

            try {
                //create URL
                URL url = new URL(server_login_URL);

                //create URL connection via Http
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                //For Sending Request
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                String postingData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username_login, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password_login, "UTF-8");

                Log.e("URLTOSEND: ", server_URL + "" + postingData);

                bufferedWriter.write(postingData);
                Log.e("Data Sent to SERVER", "Yes");////
                bufferedWriter.flush();
                bufferedWriter.close();

                //For Reading Response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    responseString += line;
                }
                Log.e("DATA RECEIVED", "Yes");
                bufferedReader.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException ex) {
                Log.e("ERROR MURLE: ", "" + ex);
                ex.printStackTrace();
            } catch (IOException e) {
                Log.e("ERROR IOE: ", "" + e);
                e.printStackTrace();
            }
        }
        //End of Login

        //For Message
        if(type.equals("message")){
            String messagge = params[1];

            try {
                //create URL
                URL url = new URL(server_message_URL);

                //create URL connection via Http
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                //For Sending Request
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                String postingData = URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(messagge, "UTF-8");

                Log.e("URLTOSEND: ", server_URL + "" + postingData);

                bufferedWriter.write(postingData);
                Log.e("Data Sent to SERVER", "Yes");////
                bufferedWriter.flush();
                bufferedWriter.close();

                //For Reading Response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "iso-8859-1"));


                while ((line = bufferedReader.readLine()) != null) {
                    responseString += line;
                }
                Log.e("DATA RECEIVED", "Yes");
                bufferedReader.close();
                httpURLConnection.disconnect();


            }catch (MalformedURLException ex) {
                Log.e("ERROR MURLE: ", "" + ex);
                ex.printStackTrace();
            } catch (IOException e) {
                Log.e("ERROR IOE: ", "" + e);
                e.printStackTrace();
            }

        }
        //End of Message
        return null;
    }

    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Status");

    }

    protected void onPostExecute(String aVoid) {
        dialog.setMessage(responseString);
        dialog.show();
        if (responseString.contains("Registered")) {
            Toast.makeText(context, "Successfully Registered", Toast.LENGTH_SHORT).show();
            Intent toLoginIntent = new Intent(context, LoginActivity.class);
            context.startActivity(toLoginIntent);
            dialog.dismiss();
        } else if (responseString.contains("Logged")) {
            Toast.makeText(context, "Sucessfully Logged In", Toast.LENGTH_SHORT).show();
            Intent toMessageIntent = new Intent(context, MessageActivity.class);
            context.startActivity(toMessageIntent);
            dialog.dismiss();
        }
        else if(responseString.contains("Message")){
            Toast.makeText(context, "Message Sucessfully Inserted", Toast.LENGTH_SHORT).show();
            /*Intent toMessageIntent = new Intent(context, MessageActivity.class);
            context.startActivity(toMessageIntent);*/
            dialog.dismiss();
        }
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}