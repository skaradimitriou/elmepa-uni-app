package com.stathis.elmepaunivapp.ui.research.model;

import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

import java.util.List;

public class ResearchItem {

    String categoryName;
    List<UsefulLinks> researchItems;

    public ResearchItem(String categoryName, List<UsefulLinks> researchItems) {
        this.categoryName = categoryName;
        this.researchItems = researchItems;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<UsefulLinks> getResearchItems() {
        return researchItems;
    }

    public void setResearchItems(List<UsefulLinks> researchItems) {
        this.researchItems = researchItems;
    }

    @Override
    public String toString() {
        return "ResearchItem{" +
                "categoryName='" + categoryName + '\'' +
                ", researchItems=" + researchItems +
                '}';
    }
}
