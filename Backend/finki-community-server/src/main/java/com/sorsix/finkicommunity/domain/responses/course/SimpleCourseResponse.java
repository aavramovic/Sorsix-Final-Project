package com.sorsix.finkicommunity.domain.responses.course;

public class SimpleCourseResponse implements Comparable<SimpleCourseResponse>{
    public long courseId;
    public String code;
    public String courseName;
    public String courseDescription;

    /*
    When SimpleCourseResponse-s are stored in a TreeSet, used to sort them
     */
    @Override
    public int compareTo(SimpleCourseResponse o) {
        return courseName.compareTo(o.courseName);
    }
}
