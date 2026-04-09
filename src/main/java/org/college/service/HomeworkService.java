package org.college.service;

import lombok.RequiredArgsConstructor;
import org.college.model.AccessResponse;
import org.college.model.Homework;
import org.college.repo.HomeworkRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkService {

    private final HomeworkRepository repository;
    private final S3Service s3Service;

    public AccessResponse getAccessUrl(String id) {

        Homework hw = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        String url = s3Service.generateDownloadUrl("college-homework-bucket", hw.getS3Key());

        return new AccessResponse(hw.getFileName(), url, 600);
    }
}