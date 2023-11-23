package io.github.singhalmradul.entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
public class Shift extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    private LocalTime endTime;

    @ManyToMany(targetEntity = Entity.class, mappedBy = "shifts")
    @JoinTable(name = "employee_shifts", joinColumns = @JoinColumn(name = "shift_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Entity> employees;

}
