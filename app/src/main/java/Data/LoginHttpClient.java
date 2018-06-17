package Data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Util.Utils;

public class LoginHttpClient {


    public String getLoginData(String userName, String password) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) (new URL(Utils.LOGIN_URL1+userName+
                                                    Utils.LOGIN_URL2+password+
                                                    Utils.LOGIN_URL3)).openConnection();

            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\r\n");
            }
            inputStream.close();
            bufferedReader.close();

            Log.d("amir",stringBuffer.toString());
            return stringBuffer.toString();
        } catch (IOException e) {
            Log.d("amirr","inja");
            return null;
        }
    }

}
