package org.college.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private String title;
    private String author;
    private List<String> tags;
    private String attachmentName;

    private int page = 0;
    private int size = 20;
}