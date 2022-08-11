package com.food.common.unit.user.domain;

import com.food.common.mock.address.MockAddress;
import com.food.common.mock.user.MockUser;
import com.food.common.mock.user.MockUserAddress;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.UserAddress;
import org.junit.jupiter.api.Test;

import static com.food.common.user.UserValidationFailureMessages.UserAddress.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserAddressTest extends SuperValidationTests<UserAddress> {

    @Test
    void validateUserInUserAddress() {
        UserAddress mockUserAddressWithNullUser = MockUserAddress.builder()
                .user(null)
                .build();

        UserAddress mockUserAddressWithNormalUser = MockUserAddress.builder()
                .user(MockUser.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockUserAddressWithNullUser)).containsExactlyInAnyOrder(USER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockUserAddressWithNormalUser)).isEmpty()
        );
    }

    @Test
    void validateNameInUserAddress() {
        String longName = "A".repeat(31);
        UserAddress mockUserAddressWithLongName = MockUserAddress.builder()
                .name(longName)
                .build();

        UserAddress mockUserAddressWithName = MockUserAddress.builder()
                .name("My Home")
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockUserAddressWithLongName)).containsExactlyInAnyOrder(formatLength(NAME_HAS_TO_BE_BETWEEN_LENGTH, 30, longName)),
                () -> assertThat(failureMessagesOf(mockUserAddressWithName)).isEmpty()
        );
    }

    @Test
    void validateAddressInUserAddress() {
        UserAddress mockUserAddressWithNullAddress = MockUserAddress.builder()
                .address(null)
                .build();

        UserAddress mockUserAddressWithAddress = MockUserAddress.builder()
                .address(MockAddress.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockUserAddressWithNullAddress)).containsExactlyInAnyOrder(ADDRESS_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockUserAddressWithAddress)).isEmpty()
        );
    }

    @Test
    void validateAddressDetailInUserAddress() {
        String longAddressDetail = "A".repeat(151);
        UserAddress mockUserAddressWithNullAddress = MockUserAddress.builder()
                .addressDetail(longAddressDetail)
                .build();

        assertThat(failureMessagesOf(mockUserAddressWithNullAddress))
                .containsExactlyInAnyOrder(formatLength(ADDRESS_DETAIL_HAS_TO_BE_BETWEEN_LENGTH, 150, longAddressDetail));
    }
}
