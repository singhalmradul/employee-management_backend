package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Attendance extends AbstractEntity {

    @ManyToOne(optional = false, targetEntity = Employee.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
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
