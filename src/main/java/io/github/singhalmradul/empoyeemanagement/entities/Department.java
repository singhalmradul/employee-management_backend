package io.github.singhalmradul.empoyeemanagement.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id")
        private UUID id;

        @Column(name = "name")
        private String name;

        @Column(name = "location")
        private String location;

        @OneToMany(mappedBy = "department", targetEntity = Employee.class, orphanRemoval = true, fetch = FetchType.LAZY)
        @JsonProperty("employees")
        private List<Employee> employees;

}
