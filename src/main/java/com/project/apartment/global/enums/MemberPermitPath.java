package com.project.apartment.global.enums;

import org.springframework.http.HttpMethod;

import java.util.Arrays;

public enum MemberPermitPath {
    MEMBER_CREATE(HttpMethod.GET, "/member", "", "회원 조회");

    private final HttpMethod httpMethod;
    private final String path;
    private final String regex;
    private final String description;

    MemberPermitPath(HttpMethod httpMethod, String path, String regex, String description) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.regex = regex;
        this.description = description;
    }

    public static String[] toArrayPath(HttpMethod method) {
        return Arrays.stream(values())
                .filter(value -> value.httpMethod.equals(method))
                .map(MemberPermitPath::getPath)
                .toArray(String[]::new);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public String getRegex() {
        return regex;
    }

    public String getDescription() {
        return description;
    }
}
