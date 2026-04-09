package org.college.controller;

import lombok.RequiredArgsConstructor;
import org.college.model.AccessResponse;
import org.college.service.HomeworkService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    @GetMapping("/{id}/access")
    public AccessResponse getAccess(@PathVariable String id) {
        return homeworkService.getAccessUrl(id);
    }
}