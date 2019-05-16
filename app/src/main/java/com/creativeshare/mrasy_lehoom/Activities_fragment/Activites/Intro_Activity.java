package com.creativeshare.mrasy_lehoom.Activities_fragment.Activites;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.creativeshare.mrasy_lehoom.Language.Language;
import com.creativeshare.mrasy_lehoom.preferences.Preferences;
import com.creativeshare.mrasy_lehoom.R;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class Intro_Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout  indicator;
    private Button start;
    private Intro_Pager_Adapter intro_pager_adapter;
    Preferences preferences;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Language.getLanguage(newBase)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_);
        if(savedInstanceState==null) {
            preferences=Preferences.getInstance();
            intro_pager_adapter = new Intro_Pager_Adapter(this);
            viewPager = findViewById(R.id.view_pager);
            indicator=findViewById(R.id.tab1);
            start=findViewById(R.id.btn_next);
            indicator.setupWithViewPager(viewPager);
            viewPager.setAdapter(intro_pager_adapter);
start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        preferences.create_first_time(Intro_Activity.this,false);
        Intent i = new Intent(Intro_Activity.this,Login.class);
        startActivity(i);
    }
});
            ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {


                @Override
                public void onPageSelected(int position) {
                    if (position == 4) {
                        viewPager.removeAllViews();
                    }
                    viewPager.setCurrentItem(position);
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                }
            };
        }
    }
    public class Intro_Pager_Adapter extends PagerAdapter {
        private int[] layouts = new int[]{R.layout.introslider1, R.layout.introslider2, R.layout.introslider3, R.layout.introslider4,R.layout.introslider5};
        private int image[]=new int[]{R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4,R.drawable.slider5};
        private LayoutInflater layoutInflater;
        Intro_Activity intro_activity;
        View view = null;
        public Intro_Pager_Adapter(Intro_Activity intro_activity) {
            this.intro_activity=intro_activity;
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
try {

        view = layoutInflater.inflate(layouts[position], container, false);
        /*ImageView imageView = view.findViewById(R.id.item_image);
      //  Glide.with(intro_activity).load(getResources().getDrawable(image[position])).skipMemoryCache(true).into(imageView);
         imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),image[position]));*/

        container.addView(view);





}catch (OutOfMemoryError e){
    deleteCache(getApplicationContext());
}

            return view;
        }

        @Override
        public int getCount() {
            return (layouts != null) ? layouts.length : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return (view == obj);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup collection, int position, Object view) {
            View itemView = (View)view;
            collection.removeView(itemView);
            itemView=null;
        }
        public void deleteCache(Context context) {
            try {
                File dir = context.getCacheDir();
                deleteDir(dir);
            } catch (Exception e) {}
        }
        public  boolean deleteDir(File dir) {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
                return dir.delete();
            } else if(dir!= null && dir.isFile()) {
                return dir.delete();
            } else {
                return false;
            }
        }
    }

}
