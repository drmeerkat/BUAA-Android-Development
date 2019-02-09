package com.wen.xin.xiao.ge.gao.haro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.wen.xin.xiao.ge.gao.haro.login.LoginActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_withdrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TextView toolbar_title;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ArrayList<Fragment> fragments;
    private TabLayout fragment_news_tab;
    private FloatingActionButton fab;
    private AHBottomNavigation AHnavigation;
    private ImageView nav_imageview;
    private boolean pendingIntroAnimation;
    private boolean isLogin = false;

    private Fragment_News fragment_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_withdrawer);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        }
        else {
            pendingIntroAnimation = false;
        }


        /**
         * ToolBar Set
         */
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title.setText(R.string.title_news);
        setSupportActionBar(toolbar);
//        隐藏原始toolbar标题
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        /**
         * System Color
         */
        setSystemBarColor(R.color.color_tab_1);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_1));



//        mTextMessage = (TextView) findViewById(R.id.barmessage);  // 此处之后需要分别放在几个fragment中，目前暂时先不处理


        /**
         * Main activity viewpager
         */
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        fragments = new ArrayList<>();
        fragments.add(new Fragment_News());
        fragments.add(new Fragment_Wiki());
        fragments.add(new Fragment_Forum());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);


        mViewPager.setOffscreenPageLimit(3);




        /**
         * Bottom Navigation Bar
         */
        AHnavigation = (AHBottomNavigation) findViewById(R.id.navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.title_news, R.drawable.ic_description_black_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.title_forum, R.drawable.ic_forum_black_24dp, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.title_wiki, R.drawable.ic_dashboard_black_24dp, R.color.color_tab_3);

// Add items
        AHnavigation.addItem(item1);
        AHnavigation.addItem(item2);
        AHnavigation.addItem(item3);

// Set background color
        AHnavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        AHnavigation.setColored(true);
// Colors for selected (active) and non-selected items (in color reveal mode).
        AHnavigation.setColoredModeColors(Color.WHITE, fetchColor(R.color.bili_color_light_gray));
        AHnavigation.setBehaviorTranslationEnabled(true);




        /**
         * Floating Action Button
         */
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mClassToLaunchPackage = getPackageName();
                String mClassToLaunch = mClassToLaunchPackage + "."
                        + "ui.ActivityList.ActivitySplashScreen";
                Intent i = new Intent();
                i.setClassName(mClassToLaunchPackage, mClassToLaunch);
                Log.i("Information", Integer.toString(AHnavigation.getCurrentItem()));
                if (AHnavigation.getCurrentItem() == 2) {
                    startActivity(i);
                }
                else if (AHnavigation.getCurrentItem() == 0|AHnavigation.getCurrentItem() == 1) {
                    Snackbar.make(view, "Here is the global search button", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        AHnavigation.manageFloatingActionButtonBehavior(fab);
        AHnavigation .setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    toolbar_title.setText(R.string.title_news);
                    mViewPager.setCurrentItem(0,false);
                    setSystemBarColor(R.color.color_tab_1);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_1));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_tab_1)));
//                    fab.setVisibility(View.GONE);
//                    fab.hide();
                }

                else if (position == 1) {
                    toolbar_title.setText(R.string.title_forum);
                    mViewPager.setCurrentItem(2,false);
                    setSystemBarColor(R.color.color_tab_2);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_2));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_tab_2)));

//                    fab.setVisibility(View.GONE);

//                    fab.hide();
                }

                else if (position == 2) {
                    toolbar_title.setText(R.string.title_wiki);
                    mViewPager.setCurrentItem(1,false);
                    setSystemBarColor(R.color.color_tab_3);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_3));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_tab_3)));

//                    fab.setVisibility(View.VISIBLE);
                    //TODO:fab会乱跑
//                    fab.show();
                }
                return true;
            }
        });
        AHnavigation.setCurrentItem(0);


        /**
         * Drawer Layout with Left Side Navigation
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                window.setStatusBarColor(Color.TRANSPARENT);

//                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                //创建statusbarview设置背景 高度等于系统的statusbar高度
//                View statusBarView = new View(getApplicationContext()) ;
//                statusBarView.setBackgroundColor(getResources().getColor(R.color.color_tab_2));
//                //获得contentview 并添加创建的statusbarview
//                ViewGroup decorView = (ViewGroup) window.getDecorView();
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight());
//                params.gravity = Gravity.TOP;
//                statusBarView.setLayoutParams(params);
//                decorView.addView(statusBarView);
//                //Called when a drawer's position changes.
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //Called when a drawer has settled in a completely open state.
                //The drawer is interactive at this point.
                // If you have 2 drawers (left and right) you can distinguish
                // them by using id of the drawerView. int id = drawerView.getId();
                // id will be your layout's id: for example R.id.left_drawer
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                window.setStatusBarColor(Color.TRANSPARENT);
//                toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
//                toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                String title = toolbar_title.getText().toString();
                Log.i("Information",title);
                if (title.equals(getString(R.string.title_news))) {
                    setSystemBarColor(R.color.color_tab_1);
                }
                else if (title.equals(getString(R.string.title_forum))) {
                    setSystemBarColor(R.color.color_tab_2);
                }
                else {
                    setSystemBarColor(R.color.color_tab_3);
                }
                // Called when a drawer has settled in a completely closed state.
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        nav_imageview = (ImageView)findViewById(R.id.imageView);
//        TextView nav_username = (TextView) findViewById(R.id.nav_user_name);
//        TextView nav_email = (TextView) findViewById(R.id.textView);
//        nav_imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isLogin) {
//                    //TODO；User Profile
//                    Toast.makeText(MainActivity_withdrawer.this, "您已经登陆了", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Intent intent = new Intent(MainActivity_withdrawer.this, LoginActivity.class);
//                    startActivity(intent);
//                    //TODO: 返回的登陆结果？
////                    isLogin = result;
//                    if (isLogin) {
//                        //TODO: 刷新用户名和邮箱
//                    }
//                }
//            }
//        });


    }

    private int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;
        return statusBarHeight;
    }

    private void setSystemBarColor(int id) {
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(id));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }


    /**
     * Main activity fragment
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragments;
        public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {

            return fragments.size();
        }
    }

    /**
     * IntroAnimation of toolbar with TabLayout and Content Animation
     */
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private void  startIntroAnimation() {
//        fab.setTranslationY(3*getResources().getDimension(R.dimen.btn_fab_size));
        // 留给content动画处理fab

        int actionbarsize = Utils.dpToPx(56);
        int tablayoutsize = Utils.dpToPx(34);
        toolbar.setTranslationY(-actionbarsize);
        toolbar_title.setTranslationY(-actionbarsize);
        this.fragment_news= (Fragment_News)fragments.get(0);
        this.fragment_news_tab = fragment_news.getTabLayout();
        this.fragment_news_tab.setTranslationY(-actionbarsize-tablayoutsize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        toolbar_title.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        fragment_news_tab.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    }


    private static final int ANIM_DURATION_FAB = 400;
    private void startContentAnimation() {

        fragment_news.getTabOne().getAdapter().updateItems();
    }




    /**
     * For Drawer use
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity_withdrawer, menu);


        /**
         * Animation
         */

        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        // Handle navigation view item clicks here.
        if (item == null){
            return false;
        }
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery) {
            Log.i("Information","Clickable");
            Intent intent = new Intent(MainActivity_withdrawer.this, LoginActivity.class);
            startActivityForResult(intent,1);

            //TODO:更改用户名等

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1){
            isLogin =  data.getBooleanExtra("text",false);
            if (isLogin) {
                Log.i("Information","登陆成功");
            }
            else {
                Log.i("Information","登陆失败");
            }
        }
    }
}
