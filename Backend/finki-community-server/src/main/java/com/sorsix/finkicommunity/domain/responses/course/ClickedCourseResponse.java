package com.sorsix.finkicommunity.domain.responses.course;

import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import java.util.Set;
import java.util.TreeSet;

public class ClickedCourseResponse {
    public String code;
    public String courseName;
    public String courseDescription;
    public String studyYear;
    public String semester;
    public String programs;
    public String courseType;
    public int numberOfPosts;
    public int numberOfReplies;
    public Set<SimplePostResponse> posts = new TreeSet<>();
}
