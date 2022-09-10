package com.food.common.annotation;

import com.food.common.user.enumeration.Role;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class ApiMethod {
    private RequestMethod[] method;
    private String[] path;
    private Role role;

    public ApiMethod(RequestMethod[] method, String[] path, Role role) {
        this.method = method;
        this.path = path;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiMethod apiMethod = (ApiMethod) o;
        return Arrays.equals(method, apiMethod.method) && Arrays.equals(path, apiMethod.path) && role == apiMethod.role;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(role);
        result = 31 * result + Arrays.hashCode(method);
        result = 31 * result + Arrays.hashCode(path);
        return result;
    }
}
