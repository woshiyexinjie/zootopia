package com.helloxin.elephant.saas;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class TenantInfo {
    private String tenant;
}