package example.myapplication.movelife;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Thomas on 22-5-2014.
 */
public class AddCompany extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView;
    EditText sqlName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcompany);
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

            switch (view.getId()){

                case R.id.bSQLUpdate:
                     boolean didItWork = true;
                    try {
                        String name = sqlName.getText().toString();
                        DatabaseTable entry = new DatabaseTable(AddCompany.this);
                        entry.open();
                        entry.createEntry(name);
                        entry.close();

                    }catch (Exception e){
                            didItWork = false;
                            String error = e.toString();
                            Dialog d = new Dialog(this);
                            d.setTitle("Dang it!");
                            TextView tv = new TextView(this);
                            tv.setText(error);
                            d.setContentView(tv);
                            d.show();
                    }

                    finally {
                        if (didItWork){
                            Dialog d = new Dialog(this);
                            d.setTitle("Heck Yea!");
                            TextView tv = new TextView(this);
                            tv.setText("Success");
                            d.setContentView(tv);
                            d.show();
                        }
                    }

                     break;
                case R.id.bSQLopenView:
                    Intent i = new Intent(this, SQLView.class);
                    startActivity(i);
                    break;
            }
    }
}
