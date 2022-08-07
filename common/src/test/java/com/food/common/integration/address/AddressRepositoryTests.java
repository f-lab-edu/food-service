package com.food.common.integration.address;

import com.food.common.address.domain.Address;
import com.food.common.address.repository.AddressRepository;
import com.food.common.integration.SuperIntegrationTest;
import com.food.common.mock.MockAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AddressRepositoryTests extends SuperIntegrationTest {

    @Autowired
    private AddressRepository addressRepository;

    @DisplayName("우편번호는 비어있거나 글자수가 30자를 초과하면 유효성검증에 실패한다")
    @Test
    void postCodeShouldNotBeEmptyOrGreaterLettersThan30() {
        List<Executable> executables = new ArrayList<>();

        for (String blankValue : blankValues()) {
            String failMessage = String.format("Address의 PostCode가 비어있으면 유효성 검증에 실패해야한다. value: %s", blankValue);
            Executable executable = createAssertThrownByExecutable(createAddressWithPostCode(blankValue), failMessage);

            executables.add(executable);
        }

        for (String longValue : longValues(31)) {
            String failMessage = String.format("Address의 PostCode가 30자 이상일 경우 유효성 검증에 실패해야한다. value: %s, length: %d", longValue, longValue.length());
            Executable executable = createAssertThrownByExecutable(createAddressWithPostCode(longValue), failMessage);

            executables.add(executable);
        }

        assertAll(executables);
    }

    private Address createAddressWithPostCode(String postCode) {
        return MockAddress.builder()
                .postCode(postCode)
                .build();
    }

    @DisplayName("시도는 비어있거나 글자수가 30자를 초과하면 유효성 검증에 실패한다")
    @Test
    void sidoShouldNotBeEmptyOrGreaterLettersThan30() {
        List<Executable> executables = new ArrayList<>();

        for (String blankValue : blankValues()) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithSido(blankValue),
                    "Address의 Sido가 비어있으면 유효성 검증에 실패해야한다. value: %s",
                    blankValue);

            executables.add(executable);
        }

        for (String longValue : longValues(31)) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithSido(longValue),
                    "Address의 Sido가 30자 이상일 경우 유효성 검증에 실패해야한다. value: %s, length: %d",
                    longValue, longValue.length());

            executables.add(executable);
        }

        assertAll(executables);
    }

    private Address createAddressWithSido(String sido) {
        return MockAddress.builder()
                .sido(sido)
                .build();
    }

    @DisplayName("시군구는 비어있거나 글자수가 30자를 초과하면 유효성 검증에 실패한다")
    @Test
    void sigunguShouldNotBeEmptyOrGreaterLettersThan30() {
        List<Executable> executables = new ArrayList<>();

        for (String blankValue : blankValues()) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithSigungu(blankValue),
                    "Address의 sigungu가 비어있으면 유효성 검증에 실패해야한다. value: %s",
                    blankValue);

            executables.add(executable);
        }

        for (String longValue : longValues(31)) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithSigungu(longValue),
                    "Address의 sigungu가 30자 이상일 경우 유효성 검증에 실패해야한다. value: %s, length: %d",
                    longValue, longValue.length());

            executables.add(executable);
        }

        assertAll(executables);
    }

    private Address createAddressWithSigungu(String sigungu) {
        return MockAddress.builder()
                .sigungu(sigungu)
                .build();
    }

    @DisplayName("주소유형은 null이면 유효성 검증에 실패한다")
    @Test
    void typeShouldNotBeNull() {
        List<Executable> executables = new ArrayList<>();

        Executable executable = createAssertThrownByExecutable(
                createAddressWithType(null),
                "Address의 Type가 null일 경우 유효성 검증에 실패해야한다.");

        executables.add(executable);

        for (Address.Type addressType : Address.Type.values()) {
            Executable executable1 = createAssertDoesNotThrowAnyExceptionExecutable(createAddressWithType(addressType),
                    "Address의 Type은 Enum타입 모두 허용해야한다. value: %s", addressType);

            executables.add(executable1);
        }

        assertAll(executables);
    }

    private Address createAddressWithType(Address.Type type) {
        return MockAddress.builder()
                .type(type)
                .build();
    }

    @DisplayName("지번/도로명 주소는 비어있거나 글자수가 100자를 초과하면 유효성 검증에 실패한다")
    @Test
    void typeAddressShouldNotBeEmptyOrGreaterLettersThan100() {
        List<Executable> executables = new ArrayList<>();

        for (String blankValue : blankValues()) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithTypeAddress(blankValue),
                    "Address의 typeAddress가 비어있으면 유효성 검증에 실패해야한다. value: %s",
                    blankValue);

            executables.add(executable);
        }

        for (String longValue : longValues(101)) {
            Executable executable = createAssertThrownByExecutable(
                    createAddressWithTypeAddress(longValue),
                    "Address의 typeAddress가 100자 이상일 경우 유효성 검증에 실패해야한다. value: %s, length: %d",
                    longValue, longValue.length());

            executables.add(executable);
        }

        assertAll(executables);
    }

    private Address createAddressWithTypeAddress(String typeAddress) {
        return MockAddress.builder()
                .typeAddress(typeAddress)
                .build();
    }

    @DisplayName("주소 본번과 부번은 null값이면 유효성검증에 실패한다")
    @Test
    void mainNoAndSubNoShouldNotBeNull() {
        List<Executable> executables = new ArrayList<>();

        Address addressWithNullMainNo = MockAddress.builder()
                .mainNo(null)
                .build();
        Executable mainExecutable = createAssertThrownByExecutable(addressWithNullMainNo,
                "Address의 mainNo가 null일 경우 유효성 검증에 실패해야한다.");

        executables.add(mainExecutable);

        Address addressWithNullSubNo = MockAddress.builder()
                .subNo(null)
                .build();
        Executable subExecutable = createAssertThrownByExecutable(addressWithNullSubNo,
                "Address의 subNo가 null일 경우 유효성 검증에 실패해야한다.");

        executables.add(subExecutable);

        assertAll(executables);
    }

    public Executable createAssertThrownByExecutable(Address address, String failMessage, Object... args) {
        return () -> assertThatThrownBy(() ->
                        addressRepository.save(address), failMessage, args)
                .isInstanceOf(ConstraintViolationException.class);
    }

    public Executable createAssertDoesNotThrowAnyExceptionExecutable(Address address, String failMessage, Object... args) {
        return () -> assertDoesNotThrow(() -> addressRepository.save(address), String.format(failMessage, args));
    }


    private List<String> blankValues() {
        List<String> result = new ArrayList<>();
        result.add("");
        result.add(" ");
        result.add("   ");
        result.add(null);
        return result;
    }

    private List<String> longValues(int length) {
        return List.of("a".repeat(length), "가".repeat(length));
    }
}
