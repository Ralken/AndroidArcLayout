package cn.ralken.arclayout.main.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cn.ralken.library.arclayout.CircleLayout;
import cn.ralken.library.arclayout.CircleLayoutAdapter;

public class MainActivity extends AppCompatActivity {
    CircleLayout mCircleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CircleLayoutAdapter adapter = new CircleLayoutAdapter();
        adapter.add(R.drawable.test_image);
        adapter.add(R.drawable.test_image);
        adapter.add(R.drawable.test_image);
        adapter.add(R.drawable.test_image);

        mCircleLayout = (CircleLayout) findViewById(R.id.mCircleLayout);
        mCircleLayout.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
