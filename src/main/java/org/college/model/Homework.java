package org.college.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "homework")
public class Homework {

    @Id
    private String id;

    private String fileName;
    private String s3Key;
}