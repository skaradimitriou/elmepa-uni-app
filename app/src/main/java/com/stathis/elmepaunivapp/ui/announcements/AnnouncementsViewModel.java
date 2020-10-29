package com.stathis.elmepaunivapp.ui.announcements;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.stathis.elmepaunivapp.database.AnnouncementsDatabase;
import com.stathis.elmepaunivapp.listeners.activity_listeners.AnnouncementClickListener;
import com.stathis.elmepaunivapp.listeners.NewsClickListener;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;
import com.stathis.elmepaunivapp.ui.announcements.recyclerviews.LatestNewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementsViewModel extends AndroidViewModel implements NewsClickListener {

    AnnouncementsDatabase announcementsDatabase;
    LatestNewsAdapter adapter = new LatestNewsAdapter(this);
    ArrayList<Announcement> announcements = new ArrayList<>();
    Content content;
    private AnnouncementClickListener announcementClickListener;

    public AnnouncementsViewModel(@NonNull Application application) {
        super(application);
    }

    void setUpListener(AnnouncementClickListener announcementClickListener){
        this.announcementClickListener = announcementClickListener;
    }

    void setupDatabase(Context context){
        announcementsDatabase = AnnouncementsDatabase.getInstance(context);
    }

    void getAnnouncements(){
        adapter.submitList(announcementsDatabase.getAnnouncementDao().getAll());

        content = new Content();
        content.execute();
    }

    @Override
    public void onNewsClick(Announcement announcement) {
        announcementClickListener.goToAnnouncement(announcement);
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.submitList(announcementsDatabase.getAnnouncementDao().getAll());
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://mst.hmu.gr/news_gr/";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("article");
                int size = data.size();
                Log.d("doc", "doc: " + doc);
                Log.d("data", "data: " + data);
                for (int i = 0; i < size; i++) {
                    String imgUrl = data.select("a.entry-featured-image-url")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("h2.entry-title")
                            .select("h2")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("h2.entry-title")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    announcements.add(new Announcement(title, detailUrl, imgUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title);
                }

                /* checks if announcements db size is less than 0 (empty) and either inserts or updates the data
                then passes the data into the ListAdapter   */

                if (dbIsEmpty()) {
                    announcementsDatabase.getAnnouncementDao().insert(announcements);
                } else {
                    announcementsDatabase.getAnnouncementDao().update(announcements);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public boolean dbIsEmpty() {
        List<Announcement> announcements = announcementsDatabase.getAnnouncementDao().getAll();
        if (announcements.size() < 1) {
            return true;
        }
        return false;
    }

}