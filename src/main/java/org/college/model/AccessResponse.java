package org.college.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessResponse {
    private String fileName;
    private String url;
    private int expirySeconds;
}