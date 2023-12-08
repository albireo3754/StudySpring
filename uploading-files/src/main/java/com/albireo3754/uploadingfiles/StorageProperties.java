package com.albireo3754.uploadingfiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
