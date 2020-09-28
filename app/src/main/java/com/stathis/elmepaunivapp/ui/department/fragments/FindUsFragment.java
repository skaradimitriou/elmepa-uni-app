package com.stathis.elmepaunivapp.ui.department.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.ui.department.DepartmentViewModel;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.SocialChannelAdapter;

public class FindUsFragment extends Fragment {

    private RecyclerView findUs;
    private SocialChannelAdapter socialChannelAdapter;
    private DepartmentViewModel departmentViewModel;

    public FindUsFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        departmentViewModel = new ViewModelProvider(this).get(DepartmentViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findUs = view.findViewById(R.id.social_recView);
        socialChannelAdapter = new SocialChannelAdapter(new SocialClickListener() {
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
        socialChannelAdapter.submitList(departmentViewModel.getSocialList());
        findUs.setAdapter(socialChannelAdapter);
    }
}