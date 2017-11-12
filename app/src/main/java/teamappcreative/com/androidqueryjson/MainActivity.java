package teamappcreative.com.androidqueryjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import teamappcreative.com.androidqueryjson.model.Product;
import teamappcreative.com.androidqueryjson.util.ProductsAdapter;

public class MainActivity extends AppCompatActivity {


    private ListView mProductsListView;
    private List<Product> mProductsList;
    private ProductsAdapter mProductsAdapter;

    private OkHttpClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //http client init
        client = new OkHttpClient();
        mProductsListView = (ListView)findViewById(R.id.homeListView);
        mProductsList = new ArrayList<>();

        //run the background task of fetching the products from url
        new DownloadFilesFromUrlTask().execute();


    }


    //http client method for reading the json url
    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //Async task class to fetch url data on background
    private class DownloadFilesFromUrlTask extends AsyncTask<URL, Integer, Long> {

        private static  final String URL = "http://www.recipepuppy.com/api/"; //CHANGE ACCORDINGLY

        protected Long doInBackground(URL... urls) {

            try {

                String jsonData = run(URL);//this return a json object as i've already visited the url
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray resultsJsonArr = jsonObject.getJSONArray("results");

                //loop through the json array to get json object
                for(int i = 0; i < resultsJsonArr.length(); i++){
                    JSONObject foodProduct = resultsJsonArr.getJSONObject(i); //this is getting each json object in json array

                    String productImage = foodProduct.getString("thumbnail");
                    String productTitle = foodProduct.getString("title");
                    String productDescription = foodProduct.getString("ingredients");

                    //create a new product from the above fetched data
                    Product newProduct = new Product(productImage, productTitle, productDescription);
                    //add this newly created product to the products list
                    mProductsList.add(newProduct);

                }





            }catch (final Exception e){

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            //attach progress listener if needed
        }

        protected void onPostExecute(Long result) {

            mProductsAdapter = new ProductsAdapter(getApplicationContext(), mProductsList);
            mProductsListView.setAdapter(mProductsAdapter);


        }
    }


}
