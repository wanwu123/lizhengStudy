package com.lizheng.test.zerenlian;

public class TestZerenlian {
    public static void main(String[] args) {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(false);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(false);
        Study study = new Study();
        StudyPrepareFilter washFaceFilter = new WashFaceFilter();
        StudyPrepareFilter washHairFilter = new WashHairFilter();
        StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();
        FilterChain filterChain = new FilterChain(study);
        filterChain.addFilter(washFaceFilter);
        filterChain.addFilter(washHairFilter);
        filterChain.addFilter(haveBreakfastFilter);
        filterChain.doFilter(preparationList, filterChain);
    }
}
