package vn.devpro.adr22_lesson23_daluong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreadActivity extends AppCompatActivity {

    private TextView txtLuongCon;
    private ProgressBar progressBar;
    private ListView lvContact;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        txtLuongCon = (TextView) findViewById(R.id.txtLuongCon);
        threadLuongCon.start();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        lvContact = (ListView) findViewById(R.id.lvContact);
        threadLoadData.start();

    }

    private Thread threadLoadData = new Thread(new Runnable() {
        @Override
        public void run() {
            final ArrayList<String> data = new ArrayList<>();
            try {
                for (int i = 1; i <= 10; i++)
                {
                    data.add("Item" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lvContact.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    adapter = new ArrayAdapter<String>(ThreadActivity.this
                    , android.R.layout.simple_list_item_1,data);
                    lvContact.setAdapter(adapter);
                }
            });
        }
    });

    private Thread threadLuongCon = new Thread(new Runnable() {
        @Override
        public void run() {
            //Giả lập công việc ở dưới thực thi mất 10s
            try {
                for (int i = 1; i <= 15; i++) {
                    Thread.sleep(1000);//Câu lệnh thực thi code mất 10s
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Muốn thao tác với UI thì phải đăng ký. Không được viết trực tiếp
            //Cách 1:
            txtLuongCon.post(new Runnable() {
                @Override
                public void run() {
                    txtLuongCon.setText("Cách 1: Luồng con hoàn thành");
                }
            });

            //Cách 2:
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtLuongCon.setText("Cách 2: Luồng con hoàn thành");
                }
            });

        }
    });
}
