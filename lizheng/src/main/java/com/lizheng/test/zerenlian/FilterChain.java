package com.lizheng.test.zerenlian;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements StudyPrepareFilter{

    private int pos = 0;
    private Study study;


    private List<StudyPrepareFilter> studyPrepareFilterList = new ArrayList<StudyPrepareFilter>();

    public FilterChain(Study study) {
        this.study = study;
    }

    public void addFilter(StudyPrepareFilter studyPrepareFilter) {
        studyPrepareFilterList.add(studyPrepareFilter);
    }

    @Override
    public void doFilter(PreparationList thingList, FilterChain filterChain) {
        // 所有过滤器执行完毕
        if (pos == studyPrepareFilterList.size()) {
            study.study();
            return;
        }
        studyPrepareFilterList.get(pos++).doFilter(thingList, filterChain);
    }

}
