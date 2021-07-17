package sda.practical;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sda.practical.app.Menu;
import sda.practical.util.Haversine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IssInterface {

    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {


//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getSession();

        //Request pentru latitudine, longitudine, mesaj si timestamp
        Request request = new Request.Builder()
                .url("http://api.open-notify.org/iss-now.json")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();
        System.out.println(responseJson);

        Gson gson = new Gson();
        IssApiResponse apiResponse = gson.fromJson(responseJson, IssApiResponse.class);

        int readInt;
        do {
            System.out.println("------- Please enter an option -------");
            System.out.println(">Type 1 for speed and distance between two coordinates...");
            System.out.println(">Type 2 to show people on the ISS...");
            System.out.println(">Type 3 to exit...");
            Scanner in = new Scanner(System.in);
            readInt = in.nextInt();

            if(readInt == 1)
            {
                // ---> Introducere manuala a coordonatelor pt. calcularea distantei'
                double startLat = apiResponse.getIss().getLatitude();
                double startLong = apiResponse.getIss().getLongitude();
                double endLat = 0;
                double endLong = 0;

                System.out.println("Enter ending latitude: ");
                double doubleReader = in.nextDouble();
                endLat = doubleReader;
                System.out.println("Enter ending longitude: ");
                doubleReader = in.nextDouble();
                endLong = doubleReader;

                // Apelare distanta intre cele doua coordonate;
                double doubledistance = Haversine.distance(startLat,startLong,endLat,endLong);
                float floatdistance = (float)doubledistance;
                float speed = floatdistance/apiResponse.getTimestamp();
                System.out.println("Distanta dintre coordonatele introduse este de " + floatdistance + " km. Viteza: " + speed + " km/sec...");
            }else
                if(readInt == 2){

                    Request request2 = new Request.Builder()
                            .url("http://api.open-notify.org/astros.json")
                            .get()
                            .build();
                    Response response2 = client.newCall(request2).execute();

                    String responseJson2 = response2.body().string();
                    System.out.println(responseJson2);

                    //Gson gson2 = new Gson();
                    //InSpaceResponse inSpaceResponse = gson.fromJson(responseJson2, InSpaceResponse.class);

                }
                else
                    if (readInt == 3)
                    {
                        System.out.println("Exiting...");
                        break;}
                    else
                        if(readInt != 1 && readInt != 2 && readInt != 3){
                            System.out.println("Wrong command...");
                    }
        }while(readInt == 1 || readInt == 2 || readInt == 3);

        //HibernateUtil.shutdown();
    }

}