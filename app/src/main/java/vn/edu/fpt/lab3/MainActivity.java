package vn.edu.fpt.lab3;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.lab3.Model.Item;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvList;
    private List<Item> itemList;
    private String search_value = "android";
    private String url ;
    private SearchView searchView;
    private AsyncTask1 asyncTask1;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.rvList);
        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, itemList);

        url = "http://dotplays.com/wp-json/wp/v2/search?search=" +  Uri.encode(search_value) + "&_embed&fbclid=IwAR2g7wz5w7X9R5diLi-9LKQlu274KfWCDV8t8INHjabeqpmJYo_uPcOl4q0";
        asyncTask1 = new AsyncTask1(this, itemList, rvList, itemAdapter);
        asyncTask1.execute(url);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);

        MenuItem item = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) item.getActionView();


        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            // submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    //query = URLEncoder.encode("apples oranges", "utf-8");
                    search_value = query;
                    search_value = URLEncoder.encode(search_value, "utf-8");
                    url= "http://dotplays.com/wp-json/wp/v2/search?search=" +  search_value + "&_embed&fbclid=IwAR2g7wz5w7X9R5diLi-9LKQlu274KfWCDV8t8INHjabeqpmJYo_uPcOl4q0";
                    itemList = new ArrayList<>();
                    asyncTask1 = new AsyncTask1(MainActivity.this, itemList, rvList, itemAdapter);
                    asyncTask1.execute(url);
                    itemAdapter = new ItemAdapter(MainActivity.this, itemList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    rvList.setLayoutManager(linearLayoutManager);
                    rvList.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return false;
            }

            // thay đổi text
            @Override
            public boolean onQueryTextChange(String newText) {

                try {
                   // newText = URLEncoder.encode("apples oranges", "utf-8");
                    search_value = newText;
                     search_value = URLEncoder.encode(search_value, "utf-8");
//                    Log.e("eEE",newText);
//                    Log.e("AbC", Uri.encode(search_value));
                    url= "http://dotplays.com/wp-json/wp/v2/search?search=" +  search_value + "&_embed&fbclid=IwAR2g7wz5w7X9R5diLi-9LKQlu274KfWCDV8t8INHjabeqpmJYo_uPcOl4q0";
                    itemList = new ArrayList<>();
                    asyncTask1 = new AsyncTask1(MainActivity.this, itemList, rvList, itemAdapter);
                    asyncTask1.execute(url);
                    itemAdapter = new ItemAdapter(MainActivity.this, itemList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    rvList.setLayoutManager(linearLayoutManager);
                    rvList.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}