package com.example.knjizara.Activities;
import android.os.Bundle;
import android.widget.GridView;


import com.example.knjizara.Adapters.RecommendationAdapter;
import com.example.knjizara.Database;
import com.example.knjizara.ExpandableHeightGridView;
import com.example.knjizara.R;
import com.example.knjizara.Types.Recommendation;
import com.example.knjizara.Utility;

import java.util.ArrayList;


public class RecommendationsPageActivity extends LoggedActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_page);

        Utility.initToolbar(this);

        ExpandableHeightGridView mAppsGrid = (ExpandableHeightGridView) findViewById(R.id.gridview);
        mAppsGrid.setExpanded(true);

        ArrayList<Recommendation> recommendations= Database.getRecommendations(Utility.getUser().getId());
        GridView gridView=findViewById(R.id.gridview);
        RecommendationAdapter adapter=new RecommendationAdapter(this, recommendations);
        gridView.setAdapter(adapter);
    }
}