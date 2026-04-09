package org.college.controller;

import lombok.RequiredArgsConstructor;
import org.college.model.SearchRequest;
import org.college.model.SearchResponse;
import org.college.service.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping
    public SearchResponse search(@RequestBody SearchRequest request) {
        return searchService.search(request);
    }
}