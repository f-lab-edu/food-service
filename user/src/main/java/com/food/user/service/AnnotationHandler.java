package com.food.user.service;

import com.food.common.common.api.ApiFor;
import com.food.common.common.api.ApiMethod;
import com.food.common.user.enumeration.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AnnotationHandler {

    public Set<ApiMethod> findAnnotationInformation(Class targetClass) {
        Set<ApiMethod> result = new HashSet<>();

        ApiFor apiForClassAnnotation = (ApiFor) targetClass.getAnnotation(ApiFor.class);
        if (apiForClassAnnotation == null) {
            return result;
        }

        Role classRole = apiForClassAnnotation.role();
        RequestMapping requestMappingClassAnnotation = (RequestMapping) targetClass.getAnnotation(RequestMapping.class);
        String[] value = requestMappingClassAnnotation.value();

        /**
         * 클래스에 있는 RequestMapping을 찾아서 path와 method를 빼와야함
         *
         */

        for (Method method : targetClass.getMethods()) {
            RequestMapping requestMappingMethodAnnotation = method.getAnnotation(RequestMapping.class);
            if (requestMappingMethodAnnotation == null) continue;

            String[] requestPaths = requestMappingMethodAnnotation.value();
            RequestMethod[] requestMethods = requestMappingMethodAnnotation.method();

            ApiFor apiForMethodAnnotation = method.getAnnotation(ApiFor.class);
            Role requestRole = apiForMethodAnnotation != null ? apiForMethodAnnotation.role() : classRole;

            ApiMethod apiMethod = new ApiMethod(requestMethods, requestPaths, requestRole);
            result.add(apiMethod);
        }

        return result;
    }


    private static class RequestApiInformation {
        private Role role;
        private String[] paths;
        private RequestMethod[] methods;

        public RequestApiInformation(Class targetClass, Method targetMethod) {

        }
    }

}
