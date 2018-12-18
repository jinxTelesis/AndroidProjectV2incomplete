package app.calcounter.com.projectversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdMob extends AppCompatActivity {

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mob);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.banner_add);

        //Note the line below is how you normally build it
        //AdRequest adRequest = new AdRequest.Builder().build();

        //This line with addTestDevice is for testing with an emulator, can change to the phones id if testing with an actual phone
        //you would remove the add test device and use the above when not testing
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

    }
}
