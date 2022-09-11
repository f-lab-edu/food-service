package com.food.scanner.requestmapping;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Getter
public class RequestMappingInMethod {
    private final Set<String> paths = new HashSet<>();
    private final Set<RequestMethod> methods = new HashSet<>();

    public RequestMappingInMethod(RequestMethod[] methods, String[] values, String[] paths) {
        this.methods.addAll(List.of(methods));
        initPaths(values, paths);
    }

    public RequestMappingInMethod(RequestMethod method, String[] values, String[] paths) {
        this.methods.addAll(List.of(method));
        initPaths(values, paths);
    }

    public static Optional<RequestMappingInMethod> findRequestMappingIn(Method method) {
        List<Annotation> annotations = List.of(method.getAnnotations());
        if (annotations.isEmpty()) return Optional.empty();

        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);

            return Optional.of(new RequestMappingInMethod(RequestMethod.values(), annotation.value(), annotation.path()));
        }

        for (Annotation targetAnnotation : annotations) {
            String targetSimpleName = targetAnnotation.annotationType().getSimpleName();
            if (targetSimpleName.equals(GetMapping.class.getSimpleName())) {
                GetMapping annotation = (GetMapping) targetAnnotation;

                return Optional.of(new RequestMappingInMethod(RequestMethod.GET, annotation.value(), annotation.path()));
            }

            if (targetSimpleName.equals(PostMapping.class.getSimpleName())) {
                PostMapping annotation = (PostMapping) targetAnnotation;

                return Optional.of(new RequestMappingInMethod(RequestMethod.POST, annotation.value(), annotation.path()));
            }

            if (targetSimpleName.equals(PutMapping.class.getSimpleName())) {
                PutMapping annotation = (PutMapping) targetAnnotation;

                return Optional.of(new RequestMappingInMethod(RequestMethod.PUT, annotation.value(), annotation.path()));
            }

            if (targetSimpleName.equals(PatchMapping.class.getSimpleName())) {
                PatchMapping annotation = (PatchMapping) targetAnnotation;

                return Optional.of(new RequestMappingInMethod(RequestMethod.PATCH, annotation.value(), annotation.path()));
            }

            if (targetSimpleName.equals(DeleteMapping.class.getSimpleName())) {
                DeleteMapping annotation = (DeleteMapping) targetAnnotation;

                return Optional.of(new RequestMappingInMethod(RequestMethod.DELETE, annotation.value(), annotation.path()));
            }
        }

        return Optional.empty();
    }

    private void initPaths(String[] values, String[] paths) {
        if (values.length > 0) {
            this.paths.addAll(Set.of(values));
        } else if (paths.length > 0) {
            this.paths.addAll(Set.of(paths));
        } else {
            this.paths.add("");
        }
    }

    public Set<String> mergeRequestPathsWithClass(Set<String> classPaths) {
        Set<String> result = new HashSet<>();
        for (String classPath : classPaths) {
            paths.forEach(methodPath ->
                    result.add(classPath + methodPath));
        }

        return result;
    }
}
