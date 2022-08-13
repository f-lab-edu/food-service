package com.food.common.mock.address;

import com.food.common.address.domain.Address;

public class MockAddress {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String postCode = "06628";
        private String sido = "서울특별시";
        private String sigungu = "서초시";
        private Address.Type type = Address.Type.ROAD;
        private String typeAddress = "강남대로";
        private Short mainNo = 311;
        private Short subNo = 0;
        private String referenceAddress = "(서초동)";

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder sido(String sido) {
            this.sido = sido;
            return this;
        }

        public Builder sigungu(String sigungu) {
            this.sigungu = sigungu;
            return this;
        }

        public Builder type(Address.Type type) {
            this.type = type;
            return this;
        }

        public Builder typeAddress(String typeAddress) {
            this.typeAddress = typeAddress;
            return this;
        }

        public Builder mainNo(Short mainNo) {
            this.mainNo = mainNo;
            return this;
        }

        public Builder subNo(Short subNo) {
            this.subNo = subNo;
            return this;
        }

        public Builder referenceAddress(String referenceAddress) {
            this.referenceAddress = referenceAddress;
            return this;
        }

        public Address build() {
            return Address.create(
                    postCode, sido, sigungu, type, typeAddress, mainNo, subNo, referenceAddress);
        }
    }
}
