package vn.devpro.adr22_lesson23_daluong;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageActivity extends AppCompatActivity {

    private ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        loadImag1.start();
    }

    private Thread loadImag1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL("http://devpro.edu.vn/wp-content/uploads/2016/06/logo-devpro.png");
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(60000);
                connection.setRequestMethod("GET");//Mặc định là get
                //Sau này Post (thêm), put (sửa), delete (xóa)
                connection.setReadTimeout(60000);


                //Lấy dữ liệu về
                final InputStream inputStream =
                        connection.getInputStream();

                //Kiểm tra xem thành công hay không
                if (connection.getResponseCode()==200 && inputStream!=null){
                    //Trả về mã 200 là thành công và có dữ liệu
                    //inputstream nó dữ liệu dang binary => bitmap thì mới hiển thị lên image được

                    //Chú ý bỏ đoạn chuyển đổi ra ngoài
                    final Bitmap anh=
                            BitmapFactory.decodeStream(inputStream);
                    img1.post(new Runnable() {
                        @Override
                        public void run() {
                            img1.setImageBitmap(anh);
                        }
                    });

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}
