package net.mskurt.neveremptylistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import net.mskurt.neveremptylistviewlibrary.NeverEmptyListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an empty adapter
        String[] values={};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);

        //Set NeverEmptyListView's adapter
        final NeverEmptyListView neverEmptyListView=(NeverEmptyListView)findViewById(R.id.listview);
        neverEmptyListView.setAdapter(adapter);

        //Set a click listener to holder
        neverEmptyListView.setHolderClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For example, toast message is shown
                Toast.makeText(MainActivity.this,getResources().getString(R.string.toast_message_text),Toast.LENGTH_LONG).show();
            }
        });


        // You can set a new adapter
        // String[] newValues={"Item 1","Item 2","Item 3"};
        // ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, newValues);
        // neverEmptyListView.setAdapter(newAdapter);


        // If you use your_adapter.notifyDataSetChange() method, you must use below instead
        // neverEmptyListView.notifyDataSetChanged(your_adapter);


        // You can set a completely different view to holder
        // neverEmptyListView.addCustomViewToHolder(your_view);


        // You can get listview to make some changes at runtime
        // ListView listView=neverEmptyListView.getListview();


        // You can also customize you holder view at runtime
        // neverEmptyListView.setHolderBackgroundColor(color);
        // neverEmptyListView.setHolderTextTopMargin(margin);
        // neverEmptyListView.setHolderText(text);
        // neverEmptyListView.setHolderImageBackground(drawable);
        // ...
        // ...

    }
}
