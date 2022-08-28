package com.food.auth.apiscanner.requestmapping;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public class RequestMappingInClass {
    private final Set<String> paths = new HashSet<>();

    public RequestMappingInClass(String[] values, String[] paths) {
        initPaths(values, paths);
    }

    public static Optional<RequestMappingInClass> findRequestMappingIn(Class clazz) {
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            return Optional.of(new RequestMappingInClass(annotation.value(), annotation.path()));
        }

        return Optional.empty();
    }

    protected void initPaths(String[] values, String[] paths) {
        if (values.length > 0) {
            this.paths.addAll(Set.of(values));
        } else if (paths.length > 0) {
            this.paths.addAll(Set.of(paths));
        } else {
            this.paths.add("");
        }
    }
}
