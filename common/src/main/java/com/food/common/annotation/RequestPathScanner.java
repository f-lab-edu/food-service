package com.food.common.annotation;

import com.food.common.user.enumeration.Role;

import java.util.Map;
import java.util.Set;

public interface RequestPathScanner {
    Map<Role, Set<String>> scanRequestMethods();
}
