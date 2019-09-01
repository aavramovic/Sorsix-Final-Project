package com.sorsix.finkicommunity.domain.responses.post;

import java.util.ArrayList;
import java.util.List;

public class PageResponse {
    public int count;
    public List<SimplePostResponse> data = new ArrayList<>();
}
