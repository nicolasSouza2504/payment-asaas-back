package org.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = UserLogin.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    private UserLogin user;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status", length = 80)
    private String status;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "payment_id", columnDefinition = "TEXT")
    private String paymentId;

    @Column(name = "value", columnDefinition = "TEXT")
    private Double value;

    @Column(name = "billing_type")
    private Double billingType;

    @PreUpdate
    @PrePersist
    public void setOptions() {

        if (this.user != null) {
            this.userId = this.getUser().getId();
        }

    }

}
