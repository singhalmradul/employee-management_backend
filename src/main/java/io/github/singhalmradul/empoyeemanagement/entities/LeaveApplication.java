package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leave_application")
@Getter
@Setter
public class LeaveApplication {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

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
