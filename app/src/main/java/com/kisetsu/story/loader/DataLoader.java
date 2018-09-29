package com.kisetsu.story.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;

import com.kisetsu.story.itembeans.ItemBeanHome;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by kisetsu on 18-9-23.
 * Load data for homepage from server.
 */

public class DataLoader {
    private LruCache<String,ItemBeanHome> cache;
    private List<ItemBeanHome> list;

    public DataLoader(List<ItemBeanHome> list){
        long maxMemory=Runtime.getRuntime().maxMemory();
        long cacheSize=maxMemory/4;
        cache=new LruCache<String, ItemBeanHome>((int) cacheSize);
        this.list=list;
    }

    private void addItemToCache(String key, ItemBeanHome value){
        if (getItemFromCache(key)==null){
            cache.put(key,value);
        }
    }

    private ItemBeanHome getItemFromCache(String key){
        return cache.get(key);
    }

    public void loadItemByAsyncTask(List<ItemBeanHome> list, final String url){
        ItemBeanHome item=getItemFromCache(url);
        if (item==null){
            new LoadItemTask(list,url).execute(url);
        }else {
            list.add(item);
        }
    }

    class LoadItemTask extends AsyncTask<String,Void,ItemBeanHome> {
        private List<ItemBeanHome> list;
        private String url;
        LoadItemTask(List<ItemBeanHome> list, String url){
            this.list=list;
            this.url=url;
        }
        @Override
        protected ItemBeanHome doInBackground(String... strings) {
            String url=strings[0];
            ItemBeanHome itemBeanHome= loadItem(url);
            if (itemBeanHome!=null){
                addItemToCache(url,itemBeanHome);
            }
            return itemBeanHome;
        }

        @Override
        protected void onPostExecute(ItemBeanHome itemBeanHome) {
            super.onPostExecute(itemBeanHome);
            list.add(itemBeanHome);
        }
    }

    private ItemBeanHome loadItem(String urlString){
        ItemBeanHome itemBeanHome=new ItemBeanHome(null,null);
        InputStream is=null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            is=new BufferedInputStream(connection.getInputStream());
            Byte[] b=new Byte[1024];
            String result= String.valueOf(is.read());
            itemBeanHome.content=result;
            itemBeanHome.title=result;
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return itemBeanHome;
    }
}
