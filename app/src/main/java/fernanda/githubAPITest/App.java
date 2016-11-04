package fernanda.githubAPITest;

import android.app.Application;

import fernanda.githubAPITest.dagger.component.DaggerNetComponent;
import fernanda.githubAPITest.dagger.component.NetComponent;
import fernanda.githubAPITest.dagger.module.AppModule;
import fernanda.githubAPITest.dagger.module.NetModule;

/**
 * Created by fernanda on 03/11/16.
 */

public class App extends Application {

    String API_BASE_URL = "https://api.github.com/";

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(API_BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }


}
