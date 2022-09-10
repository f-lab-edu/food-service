package com.food.auth.apiscanner;

import com.food.auth.apiscanner.requestmapping.RequestMappingInClass;
import com.food.auth.apiscanner.requestmapping.RequestMappingInMethod;
import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class ApiScanner {

    public Map<Role, Set<String>> scanRequestMethods() throws ClassNotFoundException {
        Map<Role, Set<String>> result = new HashMap<>();
        for (Role role : Role.values()) {
            result.put(role, new HashSet<>());
        }

        for (AnnotatedMethod annotatedMethod : scanMethodsAnnotatedApiFor()) {
            findRequestInfoOfMethod(annotatedMethod).ifPresent(requestInfo ->
                    Arrays.stream(annotatedMethod.getRoles()).forEach(role ->
                            result.get(role).addAll(requestInfo.requestPaths)
                    ));
        }

        return result;
    }

    private List<AnnotatedMethod> scanMethodsAnnotatedApiFor() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RestController.class));

        Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.food");

        List<AnnotatedMethod> result = new ArrayList<>();
        for (BeanDefinition beanDef : beanDefs) {
            Class targetClass = Class.forName(beanDef.getBeanClassName());
            Optional<ApiFor> classAnnotation = Optional.ofNullable((ApiFor) targetClass.getAnnotation(ApiFor.class));

            for (Method method : targetClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ApiFor.class)) {
                    result.add(new AnnotatedMethod(targetClass, method, method.getAnnotation(ApiFor.class)));
                } else {
                    classAnnotation.ifPresent(apiFor -> result.add(new AnnotatedMethod(targetClass, method, apiFor)));
                }
            }
        }

        return result;
    }

    private Optional<RequestInfo> findRequestInfoOfMethod(AnnotatedMethod target) {
        Set<String> requestPaths = new HashSet<>();
        Method method = target.getMethod();

        Optional<RequestMappingInMethod> optionalAnnotationInMethod = RequestMappingInMethod.findRequestMappingIn(method);
        if (optionalAnnotationInMethod.isEmpty()) return Optional.empty();

        RequestMappingInMethod annotationInMethod = optionalAnnotationInMethod.get();

        RequestMappingInClass.findRequestMappingIn(target.getClazz())
                .ifPresentOrElse(
                        requestMappingInClass ->
                                requestPaths.addAll(annotationInMethod.mergeRequestPathsWithClass(requestMappingInClass.getPaths())),
                        () -> requestPaths.addAll(annotationInMethod.getPaths())
                );

        return Optional.of(new RequestInfo(annotationInMethod.getMethods(), requestPaths));
    }

    @Getter
    private static class AnnotatedMethod {
        private final Class clazz;
        private final Method method;
        private final ApiFor annotationOfApiFor;


        public AnnotatedMethod(Class clazz, Method method, ApiFor annotationOfApiFor) {
            this.clazz = clazz;
            this.method = method;
            this.annotationOfApiFor = annotationOfApiFor;
        }

        public Role[] getRoles() {
            return annotationOfApiFor.roles();
        }
    }

    private static class RequestInfo {
        private final Set<RequestMethod> requestMethods = new HashSet<>();
        private final Set<String> requestPaths = new HashSet<>();

        public RequestInfo(Set<RequestMethod> requestMethods, Set<String> requestPaths) {
            this.requestMethods.addAll(requestMethods);
            this.requestPaths.addAll(requestPaths);
        }
    }
}
