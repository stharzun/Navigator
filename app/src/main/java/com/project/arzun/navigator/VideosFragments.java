package com.project.arzun.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arzun on 3/29/17.
 */
public class VideosFragments extends Fragment {

    String YOUTUBEURL="https://www.googleapis.com/youtube/v3/search?order=date&part=snippet&channelid=UCD1Em4q90ZUK2R5HKesszJg&maxResults=50&key=AIzaSyAvH4NbYoIPsKjOyogywvLj1Dqvg4J9ItM";
    RecyclerView recyclerView;
    ArrayList<Module> mydata=new ArrayList<>();
    RequestQueue requestQueue;
    Module m=new Module();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.videoslayout,container,false);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestQueue= Volley.newRequestQueue(getActivity());



        StringRequest stringRequest=new StringRequest(Request.Method.GET, YOUTUBEURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject first=new JSONObject(response);
                    JSONArray itemArray=first.getJSONArray("items");
                    for (int i=0;i<itemArray.length();i++){
                        JSONObject second=itemArray.getJSONObject(i);
                        JSONObject id=second.getJSONObject("id");
                        String videoid=id.getString("videoId");

                        JSONObject snippet=second.getJSONObject("snippet");
                        String date=snippet.getString("publishedAt");
                        String title=snippet.getString("title");

                        JSONObject thumbnails=snippet.getJSONObject("thumbnails");
                        JSONObject medium=thumbnails.getJSONObject("medium");
                        String mediumurl=medium.getString("url");

                        m.videoid=videoid;
                        m.date=date;
                        m.title=title;
                        m.url=mediumurl;

                        mydata.add(m);
                    }displayResult();

                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void displayResult() {
        recyclerView.setAdapter(new MyCustomAdapter(getActivity(),mydata));
    }
}
