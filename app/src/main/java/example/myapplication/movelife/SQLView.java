package example.myapplication.movelife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Thomas on 22-5-2014.
 */
public class SQLView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);

        DatabaseTable info = new DatabaseTable(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String data = info.getData();
        info.close();
        tv.setText(data);
    }
}
