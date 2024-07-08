package com.project.apartment.global.enums;

import org.springframework.http.HttpMethod;

import java.util.Arrays;

public enum AllPermitPath {
    MEMBER_CREATE(HttpMethod.POST, "/member", "", "회원 저장"),
    SWAGGER(HttpMethod.GET, "/**/swagger-ui/**", "/swagger-ui.*", "스웨거"),
    SWAGGER_RESOURCE(HttpMethod.GET, "/**/swagger-resources/**", "/swagger-resources.*", "스웨거"),
    SWAGGER_API(HttpMethod.GET, "/v3/api-docs/**", "/v3/api-docs.*", "스웨거");

    private final HttpMethod httpMethod;
    private final String path;
    private final String regex;
    private final String description;

    AllPermitPath(HttpMethod httpMethod, String path, String regex, String description) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.regex = regex;
        this.description = description;
    }

    public static String[] toArrayPath(HttpMethod method) {
        return Arrays.stream(values())
                .filter(value -> value.httpMethod.equals(method))
                .map(AllPermitPath::getPath)
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
