package io.github.singhalmradul.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
public class Department extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", targetEntity = Entity.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entity> employees;

    @OneToMany(mappedBy = "department", targetEntity = Announcement.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Announcement> announcements;

}
