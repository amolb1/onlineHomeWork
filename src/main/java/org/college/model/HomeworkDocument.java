package org.college.model;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "homework")
public class HomeworkDocument {

    @Id
    private String id;

    private String title;
    private String author;
    private List<String> tags;

    private List<Attachment> attachments;

    @Data
    public static class Attachment {
        private String name;
    }
}