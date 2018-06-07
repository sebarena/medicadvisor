package com.quantris.medicadvisor;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantris.medicadvisor.models.DrugResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_search, null);

        SearchView searchView =  layout.findViewById(R.id.searchView1);
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    new DrugSearchRequestAsync().execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    new DrugSearchRequestAsync().execute(newText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        lv = layout.findViewById(R.id.listView1);
        return layout;

    }

    DrugResponse r;
    ListView lv;
    View layout;



    class DrugSearchRequestAsync extends AsyncTask<String, Void, DrugResponse> {

        private Exception exception;

        protected DrugResponse doInBackground(String... params) {
            // Request serverResponse
            try {
                URL url = new URL("http://medicadvisor.quantris.com/" + params[0]);
                URLConnection urlc = url.openConnection();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                String line;
                StringBuilder JSONResult = new StringBuilder();

                while ((line = bfr.readLine()) != null){
                    Log.d("JSON_RESULT", JSONResult.append(line).toString());
                    Log.d("JSON_RESULT_LENGTH", Integer.toString(JSONResult.length()));

                }
                //Map Json to Object
                ObjectMapper mapper = new ObjectMapper();
                r = mapper.readValue(JSONResult.toString(), DrugResponse.class);


                //Display Result in ListView
                class ListAdapter extends ArrayAdapter<DrugResponse.Drug> {

                    List<DrugResponse.Drug> list = new ArrayList<>();

                    public ListAdapter(Context context, int textViewResourceId, List<DrugResponse.Drug> objects) {
                        super(context, textViewResourceId, objects);
                        list = objects;
                    }

                    @Override
                    public int getCount() {
                        return super.getCount();
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View v;
                        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        v = inflater.inflate(R.layout.drug_response_item, null);
                        TextView textView = v.findViewById(R.id.textViewMedi);
                        textView.setText(list.get(position).name);
                        return v;
                    }
                }

                final ListAdapter adapter = new ListAdapter(getActivity(), R.layout.drug_response_item, r.items);
                Executors.newSingleThreadExecutor().submit(new Runnable()
                {
                    @Override public void run()
                    {
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                                DrugResponse.Drug drug = (DrugResponse.Drug) adapter.getItemAtPosition(position);
                                //displayDetails(drug);
                            }
                        });
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return r;
        }

        protected void onPostExecute(DrugResponse response) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }


    private DrugResponse doSearch(final String searchString) {

        DrugResponse r = new DrugResponse();
        try {
            URL url = new URL("http://medicadvisor.quantris.com/" + searchString);
            URLConnection urlc = url.openConnection();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String line;
            StringBuilder JSONResult = new StringBuilder();

            while ((line = bfr.readLine()) != null){
                Log.d("JSON_RESULT", JSONResult.append(line).toString());
                Log.d("JSON_RESULT_LENGTH", Integer.toString(JSONResult.length()));

            }
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(JSONResult.toString(), DrugResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;

        /*JsonTask asyncTask = new JsonTask();
        asyncTask.execute("http://medicadvisor.quantris.com/" + searchString);
        ObjectMapper mapper = new ObjectMapper();
        String test = asyncTask.JsonString;
        //DrugResponse response = mapper.readValue("{}", DrugResponse.class);
        //return response;
        return new DrugResponse();
    }

        protected class JsonTask extends AsyncTask<String, String, String> {

            public String JsonString;

            protected void onPreExecute() {
                super.onPreExecute();

*//*                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait");
                pd.setCancelable(false);
                pd.show();*//*
            }

            protected String doInBackground(String... params) {


                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(params[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();


                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                        Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                    }

                    return buffer.toString();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                JsonString = result;
            }
*/
    }

}

