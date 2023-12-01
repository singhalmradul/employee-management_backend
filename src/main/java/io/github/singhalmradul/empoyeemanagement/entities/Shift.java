package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    private LocalTime endTime;

    @ManyToMany(targetEntity = Entity.class, mappedBy = "shifts", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    private transient List<Employee> employees;

}
