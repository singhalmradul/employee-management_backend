package io.github.singhalmradul.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaveApplication extends AbstractEntity {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    @ManyToOne(optional = false, targetEntity = Employee.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JsonProperty("applicant")
    @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "leaveApplications" })
    private Employee applicant;

    @Column(name = "reason", nullable = false)
    @JsonProperty("reason")
    private String reason;

    @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING'")
    @JsonProperty("status")
    private Status status;

    @Column(name = "end_date", columnDefinition = "DATE", nullable = false)
    @JsonProperty("end_date")
    private LocalDate endDate;

    @Column(name = "start_date", columnDefinition = "DATE", nullable = false)
    @JsonProperty("start_date")
    private LocalDate startDate;

}
