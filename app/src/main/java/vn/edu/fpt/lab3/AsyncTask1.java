package vn.edu.fpt.lab3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import vn.edu.fpt.lab3.Model.Item;
import vn.edu.fpt.lab3.Pojo_mot.Pojo;


public class AsyncTask1 extends AsyncTask<String, Void, String> {

    private Context context;
    private List<Item> itemList;
    private Pojo pojo;
    private Item item;
    private RecyclerView rvList;
    private ItemAdapter itemAdapter;

    public AsyncTask1(Context context, List<Item> itemList, RecyclerView rvList,ItemAdapter itemAdapter) {
        this.context = context;
        this.itemList = itemList;
        this.rvList = rvList;
        this.itemAdapter=itemAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder = new StringBuilder();
        String link = strings[0];
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) builder.append(scanner.nextLine());
            scanner.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            Gson gson = new Gson();

            for (int i = 0; i < jsonArray.length(); i++) {
                pojo = gson.fromJson(String.valueOf(jsonArray.get(i)), Pojo.class);
                item = new Item();
                item.setTitle(pojo.getTitle());
                itemList.add(item);
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            rvList.setLayoutManager(layoutManager);
            rvList.setAdapter(itemAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    SearchView searchView;
//
//    public void setSearchView(SearchView searchView) {
//        this.searchView = searchView;
//    }

}
