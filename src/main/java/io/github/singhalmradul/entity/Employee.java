package io.github.singhalmradul.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends AbstractEntity {

        @Column(name = "first_name", length = 63, nullable = false)
        private String firstName;

        @Column(name = "last_name", length = 63, nullable = false)
        private String lastName;

        @Column(name = "email", length = 63, nullable = false)
        private String email;

        @Column(name = "phone_number", length = 15, nullable = false)
        private String phoneNumber;

        @Column(name = "address", length = 255, nullable = false)
        private String address;

        @Column(name = "date_of_birth", columnDefinition = "DATE", nullable = false)
        private LocalDate dateOfBirth;

        @Column(name = "job_title", length = 63, nullable = false)
        private String jobTitle;

        @Column(name = "hourly_rate", precision = 2, nullable = false)
        private float hourlyRate;

        @Column(name = "hireDate", columnDefinition = "DATE", nullable = false)
        private LocalDate hireDate;

        @ManyToOne(optional = false, targetEntity = Department.class, fetch = FetchType.EAGER, cascade = {
                        CascadeType.PERSIST, CascadeType.MERGE })
        @JoinColumn(name = "department_id")
        private Department department;

        @ManyToMany(targetEntity = Meeting.class, cascade = { CascadeType.PERSIST,
                        CascadeType.MERGE }, fetch = FetchType.LAZY)
        @JoinTable(name = "meeting_attendees", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "meeting_id"))
        private List<Meeting> meetings;

        @OneToMany(mappedBy = "employee", targetEntity = Attendance.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Attendance> announcements;

}
