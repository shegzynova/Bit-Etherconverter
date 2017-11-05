package com.example.segun.bit_etherconverter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    /**
     * Url for the developers data from Github */
    private static final String URL =
            "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=NGN,USD,EUR,JPY,GBP,AUD,CAD,CHF,CNY,KES,GHS,UGX,ZAR,XAF,NZD,MYR,BND,GEL,RUB,INR";

    /** adapter for the list of developers*/
    private CurrencyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        /** Find the reference to the list {@link ListView} in the layout */
        ListView currsListView = (ListView) findViewById(R.id.list);

        /** Create a new adapter that takes an empty list of currencies as input*/
        mAdapter = new CurrencyAdapter(this, new ArrayList<Currencies>());

        /** set the adapter on the @link ListView so the list can be populated
         * in the user interface
         */

        currsListView.setAdapter(mAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        currsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current currency that was clicked on
                Currencies currentDeveloper = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)

                String Curr = null;
                if (currentDeveloper != null) {
                    Curr = (currentDeveloper.getmCurr());
                }
                Double btcVal = null;
                if (currentDeveloper != null) {
                    btcVal = currentDeveloper.getmBtc();
                }
                Double ethVal = null;
                if (currentDeveloper != null) {
                    ethVal = currentDeveloper.getmEth();
                }

                // Create a new intent to send the parameters to the next activity
                Intent profileIntent = new Intent(MainActivity.this, Converter.class);
                profileIntent.putExtra("Curr", Curr);
                profileIntent.putExtra("btcVal", btcVal);
                profileIntent.putExtra("ethVal", ethVal);

                // Send the intent to launch a new activity
                startActivity(profileIntent);
            }
        });

        CurrencyAsyncTask task = new CurrencyAsyncTask();
        task.execute(URL);
    }

    private class CurrencyAsyncTask extends AsyncTask<String, Void, List<Currencies>> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param urls The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected List<Currencies> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Currencies> result = QueryUtils.fetchCurrencyData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Currencies> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link bit-ether converter}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}