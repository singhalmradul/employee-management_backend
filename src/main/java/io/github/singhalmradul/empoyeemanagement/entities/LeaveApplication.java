package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leave_application")
@Getter
@Setter
@NoArgsConstructor
public class LeaveApplication extends AbstractEntity {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    @ManyToOne(optional = false, targetEntity = Employee.class, fetch = FetchType.EAGER)
    private Employee applicant;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'))")
    private Status status;

    @Column(name = "end_date", columnDefinition = "DATE", nullable = false)
    private LocalDate endDate;

    @Column(name = "start_date", columnDefinition = "DATE", nullable = false)
    private LocalDate startDate;

    public boolean isPending() {
        return this.status == Status.PENDING;
    }

    public boolean isApproved() {
        return this.status == Status.APPROVED;
    }

    public boolean isRejected() {
        return this.status == Status.REJECTED;
    }

}
