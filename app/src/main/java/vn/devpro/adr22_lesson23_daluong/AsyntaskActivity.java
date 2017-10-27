package vn.devpro.adr22_lesson23_daluong;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyntaskActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyntask);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
    }

    /**
     * Sự kiện button click
     *
     * @param v
     */
    public void onDownload(View v) {

        new Download().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class Download extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Bước 1: Chương trình chạy vào đây
            //Method chạy ở chế độ UI thread: Có thể khởi tạo
            //code các thứ liên quan đến UI ở đây
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            //Bước 2: Chạy vào mehtod này
            //Method này chạy ở background tức là chạy ngầm chúng ta ko
            //thao tác UI ở đây
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(1000);
                    //Truyền thông điệp lên UI: Chạy ngầm này nó vẫn chạy
                    //nhưng đồng thời nó vẫn truyền được thông điệp lên UI
                    publishProgress(i * 10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;//Giả lập là tải thành công
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //Bước 2.1: Nếu ở bước 2 có gọi hàm publishProgress
            //Method này xử lý ở UI Thread
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //Bước 3: Tức là chạy xong bước 2 thì xuống đây
            //Đây là kết quả trả về của method doingbackground
            //Đây là UI thread
            if (aBoolean) {
                Toast.makeText(AsyntaskActivity.this, "Tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AsyntaskActivity.this, "Tải dữ liệu thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
