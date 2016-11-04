package fernanda.githubAPITest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fernanda.githubAPITest.App;
import fernanda.githubAPITest.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ((App) getApplication()).getNetComponent().inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new FirstPageFragment())
                    .commit();
        }
    }
}
