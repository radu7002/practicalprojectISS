package sda.practical;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sda.practical.app.Menu;
import sda.practical.util.Haversine;

import java.io.IOException;
import java.util.Scanner;

public class IssInterface {

    // TODO: Tasks >
    // Task1 - Initial comit/Api Integration

    // TODO: Task ISS Speed Calculation

    // Folosim formula Speed = Distanta/Timp > Dupa ce salvam datele in MySql
    // Calculam distanta dintre ultimele doua puncte folosindu ne de longitudine/latitudine >
    // Diferenta dintre ultimele doua puncte trebuie aflata folosind formula Haversine >
    // Timpul ne este dat

    // TODO: Get a list of upcoming ISS runs for a specific location
    // Api has been removed / Putem scrie noi un tabel cu locatii si trasee pe care sa-l afisam din MySql

    // TODO: Report the number of people in space within the ISS
    //Importam tabelul din MySql





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

        //System.out.println(apiResponse);

        //Request pentru numarul de oameni in ISS
        Request request2 = new Request.Builder()
                .url("http://api.open-notify.org/astros.json")
                .get()
                .build();
        Response response2 = client.newCall(request2).execute();

        String responseJson2 = response2.body().string();
        System.out.println(responseJson2);

        Gson gson2 = new Gson();
        IssApiResponse apiResponse2 = gson.fromJson(responseJson2, IssApiResponse.class);


        //System.out.println(apiResponse);

        // ---> Introducere manuala a coordonatelor pt. calcularea distantei'


        double startLat = apiResponse.getIss().getLatitude();
        double startLong = apiResponse.getIss().getLongitude();
        double endLat = 0;
        double endLong = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter ending latitude: ");
        double doubleReader = in.nextDouble();
        endLat = doubleReader;
        System.out.println("Enter ending longitude: ");
        doubleReader = in.nextDouble();
        endLong = doubleReader;


        // Apelare distanta intre cele doua coordonate;

        double doubledistance = Haversine.distance(startLat,startLong,endLat,endLong);
        float floatdistance = (float)doubledistance;
        System.out.println("Distanta dintre coordonatele introduse este de " + floatdistance + " km.");




        float timestampPerHour = (apiResponse.getTimestamp()/60)/60;
        float speed = floatdistance/apiResponse.getTimestamp();


        System.out.println("Viteza: " + speed +"Km/sec." );

        // https://www.movable-type.co.uk/scripts/latlong.html < Distance simulator for test



        //HibernateUtil.shutdown();


    }

}
