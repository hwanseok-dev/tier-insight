package io.lucky.server.user.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;
}
