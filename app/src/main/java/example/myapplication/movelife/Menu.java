package example.myapplication.movelife;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * Created by Thomas on 15-5-2014.
 */
public class Menu extends ListActivity {

    String classes[] = {"MainActivity", "AddCompany", "example2", "example3", "example4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
<<<<<<< HEAD
=======

    }
>>>>>>> 0986e548c9948eec08167fff1f262b4466cd9f45

    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String cheese = classes[position];
        super.onListItemClick(l, v, position, id);
        try {
            Class ourClass = Class.forName("example.myapplication.movelife." + cheese);
            Intent ourIntent = new Intent(Menu.this, ourClass);

            startActivity(ourIntent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }



}
