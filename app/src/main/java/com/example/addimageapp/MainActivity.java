package com.example.addimageapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtUrl;
    private Button btnBackward;
    private Button btnForward;
    private Button btnAddLink;
    private ViewPager2 viewPager;
    private TaskDao taskDao;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ánh xạ
        edtUrl = findViewById(R.id.edtUrl);
        btnBackward = findViewById(R.id.btnBackward);
        btnForward = findViewById(R.id.btnForward);
        btnAddLink = findViewById(R.id.btnAddLink);
        viewPager = findViewById(R.id.viewPager);
        // khởi tạo DAO
        taskDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                .taskDao();

        // khởi tạo Adapter
        adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        getImages();

        //previous
        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        // Next
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        // add link
        btnAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });

    }


    private void addImage() {
        // CHECK EDITTEXT IS EMPTY
        if (TextUtils.isEmpty(edtUrl.getText())) {
            edtUrl.setError("Không được bỏ trống");
            return;
        }

        // CHECK Valid Url
        if (!URLUtil.isValidUrl(edtUrl.getText().toString())) {
            edtUrl.setError("URL không hợp lệ");
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Image image = new Image();
                image.setUrl(edtUrl.getText().toString());
                taskDao.insert(image);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                getImages();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    // Get image from database
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, List<Image>> {

            @Override
            protected List<Image> doInBackground(Void... voids) {
                // LIST DEFAULT 3 IMAGES
                List<Image> taskList = taskDao
                        .getAll();
                taskList.add(new Image("https://kynguyenlamdep.com/wp-content/uploads/2020/01/hinh-anh-chu-chim-dep.jpg"));
                taskList.add(new Image("https://nld.mediacdn.vn/2020/5/29/doi-hoa-tim-5-1590731334546464136746.jpg"));
                taskList.add(new Image("https://o.rada.vn/data/image/2020/07/07/cam-nhan-hinh-anh-thien-nhien-trong-mua-xuan-nho-nho-va-sang-thu.jpg"));
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Image> images) {
                super.onPostExecute(images);
                // set data adapter
                adapter.setData(images);
                // setCurrentItem viewpager = 0
                viewPager.setCurrentItem(0);
            }
        }

        GetImages gt = new GetImages();
        gt.execute();
    }

}