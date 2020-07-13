package com.stathis.elmepaunivapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.SocialChannelAdapter;

import java.util.ArrayList;

public class FindUsFragment extends Fragment {

    private RecyclerView findUs;
    private SocialChannelAdapter socialChannelAdapter;
    private ArrayList<SocialChannels> socialChannels = new ArrayList<>();

    public FindUsFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createTheList();

        findUs = view.findViewById(R.id.social_recView);
        socialChannelAdapter = new SocialChannelAdapter(socialChannels, new ItemClickListener() {
            @Override
            public void onClick(View v) {

            }

            @Override
            public void onItemClick(DeptFieldsOfStudy item) {

            }

            @Override
            public void onProgrammesClick(Programmes programmes) {

            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {

            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {

            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {
                switch (socialChannels.getImg()) {
                    case R.drawable.map:
                        String MapUrl = socialChannels.getUrl();
                        Intent OpenMap = new Intent(Intent.ACTION_VIEW, Uri.parse(MapUrl));
                        startActivity(OpenMap);
                        break;
                    case R.drawable.youtube:
                        String youtubeUrl, LinkedInUrl, ResearchGateUrl;
                        youtubeUrl = socialChannels.getUrl();
                        try {
                            //goes to channel in youtube app
                            String inAppUrl = "vnd.youtube.com/channel/" + youtubeUrl;
                            Intent Youtube = new Intent(Intent.ACTION_VIEW, Uri.parse(inAppUrl));
                            startActivity(Youtube);
                        } catch (Exception e) {
                            //goes to channel in web view (opens browser)
                            String WebUrl = "https://www.youtube.com/channel/" + youtubeUrl;
                            Intent Youtube = new Intent(Intent.ACTION_VIEW, Uri.parse(WebUrl));
                            startActivity(Youtube);
                        }
                        break;
                    case R.drawable.linkedin:
                        LinkedInUrl = socialChannels.getUrl();
                        Intent Linkedin = new Intent(Intent.ACTION_VIEW, Uri.parse(LinkedInUrl));
                        startActivity(Linkedin);
                        break;
                    case R.drawable.researchgate:
                        ResearchGateUrl = socialChannels.getUrl();
                        Intent rg = new Intent(Intent.ACTION_VIEW, Uri.parse(ResearchGateUrl));
                        startActivity(rg);
                        break;
                }
            }
        });
        findUs.setAdapter(socialChannelAdapter);
    }

    private void createTheList() {
        socialChannels.add(new SocialChannels("Χάρτης", "https://www.google.gr/maps/place/Hellenic+Mediterσ46953,25.6549865,17z/data=!3m1!4b1!4m5!3m4!1s0x149a7fea00679c2f:0x8038b06fd113f3fb!8m2!3d35.1946909!4d25.6571752", R.drawable.map));
        socialChannels.add(new SocialChannels("Youtube", "UCapUQKQVrP2p4_ijj_OxvNg", R.drawable.youtube));
        socialChannels.add(new SocialChannels("LinkedIn", "https://www.linkedin.com/groups/13536369/", R.drawable.linkedin));
        socialChannels.add(new SocialChannels("Research\nGate", "https://www.researchgate.net/institution/Hellenic_Mediterranean_University/department/Department_of_Management_Science_and_Technology_Agios_Nikolaos", R.drawable.researchgate));
    }
}
