package vn.devpro.adr22_lesson23_daluong;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //UI thread: tương tác được với UI
                //Mở màn hình chính ra, đóng màn hình này lại
                Intent main = new Intent(SplashActivity.this,HandlerActivity.class);
                startActivity(main);

                //Xóa màn hình khỏi stack
                finish();
            }
        },3000);
    }
}
