package tablayout.huangbo.com.mytablayout;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import tablayout.huangbo.com.tablayout.HbElasticView;
import tablayout.huangbo.com.tablayout.MyIndicator;

public class MainActivity extends AppCompatActivity {
    MyIndicator myIndicator;
    ScrollView srolview;
    HbElasticView mHbElasticView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHbElasticView = (HbElasticView) findViewById(R.id.hb);
        myIndicator = (MyIndicator) findViewById(R.id.my);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.add("tab5", 10);
                myIndicator.setChanger(4);
                Toast.makeText(MainActivity.this, "添加tab5", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_speed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setAnimationTime(100);
                Toast.makeText(MainActivity.this, "修改移动速度改为100", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_jinzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setProhibitPositio(2);
                Toast.makeText(MainActivity.this, "禁止tab3修改状态", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.setFull();
                Toast.makeText(MainActivity.this, "修改下标线的长度铺满", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIndicator.resetNomalColor(0xff000000,0xffffffff,0xff000000);
            }
        });
    }


    private class Service extends IntentService{

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public Service(String name) {
            super(name);
        }

        @Override
        public void onCreate() {
            super.onCreate();
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

        }
    }
}
