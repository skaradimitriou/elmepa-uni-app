package com.stathis.elmepaunivapp.ui.announcements;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class AnnouncementsViewModel extends ViewModel {

    private ArrayList<Announcement> announcements = new ArrayList<>();

    ArrayList<Announcement> getAnnouncements(){
        try {
            String url = "https://mst.hmu.gr/news_gr/";
            Document doc = Jsoup.connect(url).get();
            Elements data = doc.select("article");
            int size = data.size();
            Log.d("doc", "doc: " + doc);
            Log.d("data", "data: " + data);
            for (int i = 0; i < size - 1; i++) {
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
            announcements.add(new Announcement("Δείτε όλες τις ανακοινώσεις του Τμήματος", "https://mst.hmu.gr/news_gr/", "https://mst.hmu.gr/wp-content/uploads/2020/06/student-using-laptop-library_74855-2539-400x250.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return announcements;
    }
}
