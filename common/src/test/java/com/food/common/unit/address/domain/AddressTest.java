package com.food.common.unit.address.domain;

import com.food.common.address.domain.Address;
import com.food.common.mock.address.MockAddress;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.address.utils.AddressFailureMessages.POST_CODE_CANNOT_BE_BLANK;
import static com.food.common.address.utils.AddressFailureMessages.POST_CODE_IS_OUT_OF_LENGTH_OF_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AddressTest extends SuperValidationTests<Address> {

    @Test
    void validatePostCodeInAddress() {
        Address mockAddressWithBlankPostCode = MockAddress.builder()
                .postCode(" ")
                .build();

        String longPostCode = "A".repeat(31);
        Address mockAddressWithLongPostCode = MockAddress.builder()
                .postCode(longPostCode)
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockAddressWithBlankPostCode)).containsExactlyInAnyOrder(POST_CODE_CANNOT_BE_BLANK),
                () -> assertThat(failureMessagesOf(mockAddressWithLongPostCode)).containsExactlyInAnyOrder(formatLength(POST_CODE_IS_OUT_OF_LENGTH_OF_STRING, 30, longPostCode))
        );
    }
}
