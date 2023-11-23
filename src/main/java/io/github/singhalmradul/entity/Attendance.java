package io.github.singhalmradul.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne(optional = false, targetEntity = Entity.class)
    @JoinColumn(name = "employee_id")
    private Entity employee;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "in_time", columnDefinition = "TIME", nullable = false)
    private LocalTime inTime;

    @Column(name = "out_time", columnDefinition = "TIME", nullable = false)
    private LocalTime outTime;
}
