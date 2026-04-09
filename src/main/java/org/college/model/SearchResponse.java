package org.college.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponse {
    private long total;
    private int page;
    private int size;
    private List<HomeworkSummary> results;
}