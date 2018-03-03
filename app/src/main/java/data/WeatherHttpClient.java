package data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import Util.Utils;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nmalapati on 1/24/2018.
 */
public class WeatherHttpClient {

    public String getWeatherData(String place){

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(Util.Utils.BASE_URL + place)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Reading the response

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
             while((line = bufferedReader.readLine()) !=null){

                 stringBuffer.append(line + "\r\n");
             }

            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        } catch (IOException e)
        {
          e.printStackTrace();
        }
        return null;

    }


}
