package fernanda.githubAPITest;

import android.app.Application;

import fernanda.githubAPITest.injections.component.DaggerNetComponent;
import fernanda.githubAPITest.injections.component.NetComponent;
import fernanda.githubAPITest.injections.module.AppModule;
import fernanda.githubAPITest.injections.module.NetModule;

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
