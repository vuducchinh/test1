package vn.devpro.adr22_lesson23_daluong;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    private TextView txtCounter;
    private int counter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        txtCounter = (TextView) findViewById(R.id.txtCounter);
        threadCounter.start();
    }

    private Handler _handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //Đây là method hứng dữ liệu nếu như có _handler truyền ra
            //từ thread

            int _data = msg.getData().getInt("Counter");
            txtCounter.setText(_data+"");
        }
    };

    private Thread threadCounter = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = counter; i >= 0; i--) {

                    Message message = new Message();
                    //Data
                    Bundle bd = new Bundle();
                    bd.putInt("Counter", i);

                    //Data phải được đính kèm vào message
                    message.setData(bd);

                    //Truyền dữ liệu ra bên ngoài
                    _handler.sendMessage(message);

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


}
