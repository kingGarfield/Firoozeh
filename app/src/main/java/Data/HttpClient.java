package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Util.Utils;

public class HttpClient {

    HttpURLConnection connection = null;
    InputStream inputStream = null;

    public String getInformation(String URL,String token) {
        try {
            connection = (HttpURLConnection) (new URL
                    (URL + token)).openConnection();
            inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\r\n");
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
