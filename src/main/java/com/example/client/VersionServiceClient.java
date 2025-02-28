package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-provider", path = "/api/v1/version")
public interface VersionServiceClient {
    @GetMapping("getVersion")
    public String getVersion();
}
