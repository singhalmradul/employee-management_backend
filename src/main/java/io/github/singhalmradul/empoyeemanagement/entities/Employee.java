package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id")
        private UUID id;

        public Employee(final String URI) {
                this.id = UUID.fromString(URI.substring(URI.lastIndexOf('/') + 1));
        }

        @Column(name = "name", length = 127, nullable = false)
        private String name;

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

        @Column(name = "hire_date", columnDefinition = "DATE", nullable = false)
        private LocalDate hireDate;

        @ManyToOne(optional = false, targetEntity = Department.class, fetch = FetchType.EAGER, cascade = {
                        CascadeType.PERSIST })
        @JoinColumn(name = "department_id")
        private Department department;

        @ManyToMany(targetEntity = Meeting.class, fetch = FetchType.LAZY)
        @JoinTable(name = "meeting_attendees", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "meeting_id"))
        private List<Meeting> meetings;

        @OneToMany(mappedBy = "employee", targetEntity = Attendance.class, orphanRemoval = true, cascade = {
                        CascadeType.DETACH }, fetch = FetchType.LAZY)
        private List<Attendance> attendance;

        @OneToMany(mappedBy = "applicant", targetEntity = LeaveApplication.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<LeaveApplication> leaveApplications;

        @OneToMany(mappedBy = "employee", targetEntity = Announcement.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Announcement> announcements;

        @JsonProperty("age")
        int getAge() {
                return LocalDate.now().getYear() - dateOfBirth.getYear();
        }

        @JsonProperty("yearsOfService")
        int getYearsOfService() {
                return LocalDate.now().getYear() - hireDate.getYear();
        }
}
