package org.college.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HomeworkSummary {
    private String id;
    private String title;
    private String author;
    private List<String> tags;
    private List<String> attachmentNames;
}