package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attendance")
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(optional = false, targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "in_time", columnDefinition = "TIME", nullable = false)
    private LocalTime inTime;

    @Column(name = "out_time", columnDefinition = "TIME", nullable = false)
    private LocalTime outTime;

    public void setInTime(LocalTime time) {
        this.inTime = time;
        this.outTime = time;
    }
}
