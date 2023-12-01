package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;

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
@Table(name = "announcement")
@Getter
@Setter
@NoArgsConstructor
public class Announcement extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false, targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

}
