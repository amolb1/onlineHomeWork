package org.college.service;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.college.model.HomeworkDocument;
import org.college.model.HomeworkSummary;
import org.college.model.SearchRequest;
import org.college.model.SearchResponse;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchResponse search(SearchRequest request) {
        Query query = buildQuery(request);
        NativeQuery searchQuery = getNativeQuery(request, query);
        SearchHits<HomeworkDocument> hits = elasticsearchOperations.search(searchQuery, HomeworkDocument.class);
        List<HomeworkSummary> results = processResult(hits);
        return new SearchResponse(hits.getTotalHits(), request.getPage(), request.getSize(), results);
    }

    private static List<HomeworkSummary> processResult(SearchHits<HomeworkDocument> hits) {
        List<HomeworkSummary> results = hits.stream()
                .map(hit -> {
                    HomeworkDocument doc = hit.getContent();
                    return new HomeworkSummary(doc.getId(), doc.getTitle(), doc.getAuthor(), doc.getTags(),
                            doc.getAttachments().stream().map(a -> a.getName()).toList());}).toList();
        return results;
    }

    private static NativeQuery getNativeQuery(SearchRequest request, Query query) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(query)
                .withPageable(PageRequest.of(request.getPage(), request.getSize()))
                .build();
        return searchQuery;
    }

    private  Query buildQuery(SearchRequest request) {
        Query query = Query.of(q -> q.bool(b -> {

            if (request.getTitle() != null && !request.getTitle().isEmpty()) {
                b.must(m -> m.match(t -> t
                        .field("title")
                        .query(request.getTitle())));
            }

            if (request.getAuthor() != null && !request.getAuthor().isEmpty()) {
                b.must(m -> m.match(a -> a
                        .field("author")
                        .query(request.getAuthor())));
            }

            if (request.getTags() != null && !request.getTags().isEmpty()) {
                b.must(m -> m.terms(t -> t
                        .field("tags")
                        .terms(v -> v.value(
                                request.getTags()
                                        .stream()
                                        .map(FieldValue::of)
                                        .toList()
                        ))));
            }

            if (request.getAttachmentName() != null && !request.getAttachmentName().isEmpty()) {
                b.must(m -> m.match(a -> a
                        .field("attachments.name")
                        .query(request.getAttachmentName())));
            }

            return b;
        }));
        return query;
    }
}