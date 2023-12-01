package io.github.singhalmradul.empoyeemanagement.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

        @Column(name = "name")
        private String name;

        @Column(name = "location")
        private String location;

        @OneToMany(mappedBy = "department", targetEntity = Employee.class, orphanRemoval = true, cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE }, fetch = FetchType.LAZY)
        @JsonProperty("employees")
        private List<Employee> employees;

        @OneToMany(mappedBy = "department", targetEntity = Announcement.class, orphanRemoval = true, cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE }, fetch = FetchType.LAZY)
        private List<Announcement> announcements;

}
