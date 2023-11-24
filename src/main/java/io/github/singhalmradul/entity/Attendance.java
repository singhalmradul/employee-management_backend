package io.github.singhalmradul.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attendance extends AbstractEntity {

    @ManyToOne(optional = false, targetEntity = Employee.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonProperty("employee")
    @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "attendance" })
    private Employee employee;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    @JsonProperty("date")
    private LocalDate date;

    @Column(name = "in_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("in_time")
    private LocalTime inTime;

    @Column(name = "out_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("out_time")
    private LocalTime outTime;

    public void setInTime(LocalTime time) {
        this.inTime = time;
        this.outTime = time;
    }
}
