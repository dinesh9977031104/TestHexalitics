package com.example.testhexalitics;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testhexalitics.adapter.AdapterAsGrid;
import com.example.testhexalitics.adapter.AdapterAsList;
import com.example.testhexalitics.model.DataModel;
import com.example.testhexalitics.network.ApiClient;
import com.example.testhexalitics.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AdapterAsList adapter;
    private List<DataModel> helpLineList = new ArrayList<>();
    private RecyclerView recyclerViewList, recyclerViewGrid;
    private ProgressBar progressBar;

    private AdapterAsGrid gridAdapter;

    private Button btnGrid, btnList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Item List");

        progressBar = findViewById(R.id.progress_bar);
        recyclerViewGrid = findViewById(R.id.recycler_view_grid);
        recyclerViewList = findViewById(R.id.recycler_view_list);

        btnGrid = findViewById(R.id.btn_grid);
        btnList = findViewById(R.id.btn_list);

        getHelplineList();

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnList.setVisibility(View.VISIBLE);
                btnGrid.setVisibility(View.GONE);

                recyclerViewList.setVisibility(View.VISIBLE);
                recyclerViewGrid.setVisibility(View.GONE);

              //  initGridView();
                getHelplineList();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnList.setVisibility(View.GONE);
                btnGrid.setVisibility(View.VISIBLE);

                recyclerViewList.setVisibility(View.GONE);
                recyclerViewGrid.setVisibility(View.VISIBLE);

                //initRecyclerview();
                getHelplineList();

            }
        });


    }

    private void initGridView() {

        gridAdapter = new AdapterAsGrid(MainActivity.this, helpLineList);
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewGrid.setHasFixedSize(true);
        recyclerViewGrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGrid.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
    }


    private void initRecyclerview() {
        adapter = new AdapterAsList(MainActivity.this, helpLineList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getHelplineList() {

        helpLineList.clear();
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DataModel>> call = apiInterface.getHelpLineList();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        if (response.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            initRecyclerview();
                            initGridView();
                            helpLineList.addAll(response.body());
                        }

                        break;


                    default:
                        progressBar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(MainActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                        break;


                }

            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(MainActivity.this, "failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
