package kz.net.book_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {

        Instant now = Instant.now();

        if (this.createdAt == null) {

            this.createdAt = now;

        }

        this.updatedAt = now;

    }

    @PreUpdate
    public void preUpdate() {

        this.updatedAt = Instant.now();

    }


}
