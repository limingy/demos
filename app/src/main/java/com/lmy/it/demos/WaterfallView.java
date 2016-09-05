package com.lmy.it.demos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by limingyang on 2016/9/3.
 */
public class WaterfallView extends ScrollView {

    private LinearLayout fristLayout, secondLayout, threeLayout;

    //图片地址
    private static String[] imgUrls = new String[]{
            "http://d.hiphotos.baidu.com/image/h%3D360/sign=e0a211de5eafa40f23c6c8db9b65038c/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D360/sign=bb25ef64bf315c605c956de9bdb0cbe6/a5c27d1ed21b0ef4400edb2fdec451da80cb3ed8.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=5a648d60f1deb48fe469a7d8c01e3aef/b812c8fcc3cec3fd5b9db074d488d43f8794270b.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=4178ae4f9c2f070840052c06d925b865/d8f9d72a6059252d1284a104369b033b5bb5b91b.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=2a716d3d4e086e0675a8394d32097b5a/023b5bb5c9ea15cea43b1105b4003af33b87b2e1.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=4cd34e043ff33a87816d061cf65d1018/8d5494eef01f3a29b41f18fa9c25bc315c607c2b.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=80285822f9f2b211fb2e8348fa806511/bd315c6034a85edf1da1c0724b540923dd5475b5.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=21a755bfa41ea8d395227202a70b30cf/43a7d933c895d143bf16062771f082025aaf0755.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=e1f33b1ddf54564efa65e23f83df9cde/80cb39dbb6fd526678b13b1aa918972bd4073621.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=f96d6a288501a18befeb1449ae2e0761/8644ebf81a4c510fab400e1e6259252dd42aa52a.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=b988ca9dc1cec3fd943ea173e689d4b6/1f178a82b9014a90f69971ecab773912b31bee3f.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=c3b7da77d309b3def4bfe26efcbe6cd3/5366d0160924ab1802d29c2837fae6cd7a890bec.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=0d00fbf7d2ca7bcb627bc1298e096b3f/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=da2b333738f33a87816d061cf65c1018/8d5494eef01f3a2922e765c99b25bc315c607c8d.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=6ffa610b5e6034a836e2be87fb1249d9/7c1ed21b0ef41bd55bb100fe53da81cb38db3dde.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=f5781eb6932397ddc9799e026983b216/0823dd54564e92584149a1bf9e82d158ccbf4e65.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=4793d9f6cb3d70cf53faac0bc8ded1ba/024f78f0f736afc3419cb4aab119ebc4b6451268.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=1c9a50843ec79f3d90e1e2368aa0cdbc/f636afc379310a5566becb8fb24543a982261036.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=9a66dab99c510fb367197191e932c893/b999a9014c086e06613eab4b00087bf40ad1cb18.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=5dbeb760d10735fa8ef048bfae500f9f/060828381f30e9244e3f894a49086e061d95f736.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=e33836519013b07ea2bd560e3cd69113/35a85edf8db1cb13519e8d63de54564e93584bce.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=b7bd1c8448ed2e73e3e9802ab700a16d/6a63f6246b600c3306832dc8184c510fd9f9a13c.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=6c234cd5881363270aedc435a18ea056/11385343fbf2b211eeef5547c88065380dd78ef2.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=e8c8db0d249759ee555066cd82fb434e/0dd7912397dda1444d06d3b2b0b7d0a20cf486a7.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=905a3eccc9ef7609230b9f991edca301/6d81800a19d8bc3e2d9b3c9b808ba61ea9d345cb.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=0b74210499504fc2bd5fb603d5dce7f0/c8177f3e6709c93d5495a3fb9d3df8dcd1005452.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=1736c04c2b381f3081198baf99014c67/6159252dd42a2834bd82c47f58b5c9ea15cebf9a.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=ab1e04fe79cb0a469a228d3f5b62f63e/7dd98d1001e9390186d26b3a79ec54e737d196e6.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=447c7edfb0119313d843f9b655380c10/5d6034a85edf8db1dc2ccc790b23dd54564e74a8.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=635d347dbb014a909e3e40bb99763971/21a4462309f79052e568ccf10ef3d7ca7acbd58e.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=6035c56d3c01213fd03348da64e636f8/fc1f4134970a304ec83a514ed4c8a786c9175c6e.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=10d0f8d5cc1b9d1695c79c67c3deb4eb/bba1cd11728b4710df0497c6c1cec3fdfc032394.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=e2d14b28f3246b60640eb472dbf91a35/b90e7bec54e736d173f699739e504fc2d46269e7.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=eb63fc7f4e36acaf46e090fa4cd88d03/18d8bc3eb13533faa2072c68add3fd1f40345bc6.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D360/sign=9a742efcb451f819ee25054ceab54a76/d6ca7bcb0a46f21f1d6ff684f2246b600d33ae8a.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=b2ca78c9ae4bd1131bcdb1346aaea488/7af40ad162d9f2d32d73b855aaec8a136327cc2e.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=b2a4bc19a918972bbc3a06ccd6cc7b9d/267f9e2f070828387d825c19ba99a9014c08f134.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=1fcbb222b11bb0519024b52e067bda77/0df3d7ca7bcb0a4644a335396963f6246b60af29.jpg"
    };

    //图片缓存
    LruCache<String, Bitmap> lruCache;

    //图片每列宽度
    int columnWidth;

    public WaterfallView(Context context) {
        super(context);
        initCache();
    }

    public WaterfallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCache();
    }

    public WaterfallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCache();
    }

    private void initCache() {
        int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        lruCache = new LruCache<String, Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if(changed) {
            ViewGroup group = (ViewGroup) getChildAt(0);
            fristLayout = (LinearLayout) group.getChildAt(0);
            secondLayout = (LinearLayout) group.getChildAt(1);
            threeLayout = (LinearLayout) group.getChildAt(2);
            columnWidth = fristLayout.getWidth();
            Log.d("列宽度：", columnWidth +"");

            //加载图片
            loadImages();
        }
    }

    private int page, count = 15;
    
    private void loadImages() {
        int startIndex = page * count;
        int endIndex = (page + 1) * count;
        if(endIndex > imgUrls.length){
            endIndex = imgUrls.length;
        }
        
        for (int i = startIndex; i < endIndex; i++) {
            new LoadImageTask().execute(imgUrls[i]);
        }
        
        page++;
    }
    
    class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return loadImageByUrl(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null){
                int scaleHeight = columnWidth * bitmap.getHeight() / bitmap.getWidth();
                addToView(bitmap, scaleHeight);
            }
        }
    }

    int fristLayoutHeight, secondLayoutHeight, threeLayoutHeight;
    private void addToView(Bitmap bitmap, int scaleHeight) {
        int minHeight = Math.min(fristLayoutHeight, Math.min(secondLayoutHeight, threeLayoutHeight));
        if(minHeight == fristLayoutHeight){
            addToView(fristLayout, bitmap);
            fristLayoutHeight += scaleHeight;
            return;
        }
        if(minHeight == secondLayoutHeight){
            addToView(secondLayout, bitmap);
            secondLayoutHeight +=  scaleHeight;
            return;
        }
        if(minHeight == threeLayoutHeight){
            addToView(threeLayout, bitmap);
            threeLayoutHeight += scaleHeight;
        }
    }

    private void addToView(LinearLayout layout, final Bitmap bitmap) {
        ImageView imageView = null;
//        for (int i =0; i< layout.getChildCount(); i++){
//            View view = layout.getChildAt(i);
//            if(view.getBottom() > getBottom()){
//                imageView = (ImageView) view;
//                layout.removeView(view);
//                break;
//            }
//            Log.d("scollView顶点:", getBottom()+"");
//        }

        //是否有可重用的view，若没有则新建
        if(imageView == null){
            imageView = new ImageView(getContext());
            imageView.setPadding(5,5,5,5);
            imageView.setAdjustViewBounds(true);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setType("image/*");
                    i.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
                    getContext().startActivity(i);
                }
            });
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, layoutParams);
        imageView.setImageBitmap(bitmap);

    }

    private Bitmap loadImageByUrl(String param) {
        //1.从内存加载
        Bitmap bitmap = lruCache.get(param);
        if(bitmap != null){
            return bitmap;
        }

        //2.从SDCard加载
        bitmap = loadImageFromSdCard(param);
        if(bitmap != null){
            //更新缓存
            lruCache.put(param, bitmap);
            return bitmap;
        }

        //3.从网络加载
        bitmap = loadImageFromNet(param);
        if(bitmap != null){
            //更新缓存
            lruCache.put(param, bitmap);
            return bitmap;
        }

        return null;
    }

    private Bitmap loadImageFromSdCard(String param) {
        File file = getContext().getExternalCacheDir();
        //SDCard不存在或打不开
        if(file == null){
            return null;
        }

        String url = Base64.encodeToString(param.getBytes(), Base64.DEFAULT);
        File cacheFile = new File(file, url);
        //SDCard不存在该图片
        if(!cacheFile.exists()){
            return null;
        }

        //加载并优化图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(cacheFile.getPath(), options);
        //根据原图片宽度与展示列宽度求得缩放比
        options.inSampleSize = options.outWidth / columnWidth;
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeFile(cacheFile.getPath(), options);
    }

    private Bitmap loadImageFromNet(String param) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            //获取缓存路劲
            File cacheDir = getContext().getExternalCacheDir();
            String url = Base64.encodeToString(param.getBytes(), Base64.DEFAULT);
            fileOutputStream = new FileOutputStream(new File(cacheDir, url));

            httpURLConnection  = (HttpURLConnection) new URL(param).openConnection();
            inputStream = httpURLConnection.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.flush();

            //重新从SDCard加载
            return loadImageFromSdCard(param);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }

            }catch (IOException e){
            }

        }

        return null;
    }

    int oldScrollY = -1;
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);

        if(oldScrollY == scrollY){
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 10);
        }else {
            oldScrollY = scrollY;
        }
    }

    private Handler handler = new Handler();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //加载图片
            loadImages();
        }
    };
}
