package io.github.singhalmradul.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee extends AbstractEntity {

        @Column(name = "first_name", length = 63, nullable = false)
        @JsonProperty("first_name")
        private String firstName;

        @Column(name = "last_name", length = 63, nullable = false)
        @JsonProperty("last_name")
        private String lastName;

        @Column(name = "email", length = 63, nullable = false)
        @JsonProperty("email")
        private String email;

        @Column(name = "phone_number", length = 15, nullable = false)
        @JsonProperty("phone_number")
        private String phoneNumber;

        @Column(name = "address", length = 255, nullable = false)
        @JsonProperty("address")
        private String address;

        @Column(name = "date_of_birth", columnDefinition = "DATE", nullable = false)
        @JsonProperty("date_of_birth")
        private LocalDate dateOfBirth;

        @Column(name = "job_title", length = 63, nullable = false)
        @JsonProperty("job_title")
        private String jobTitle;

        @Column(name = "hourly_rate", precision = 2, nullable = false)
        @JsonProperty("hourly_rate")
        private float hourlyRate;

        @Column(name = "hireDate", columnDefinition = "DATE", nullable = false)
        @JsonProperty("hire_date")
        private LocalDate hireDate;

        @ManyToOne(optional = false, targetEntity = Department.class, fetch = FetchType.EAGER, cascade = {
                        CascadeType.PERSIST, CascadeType.MERGE })
        @JoinColumn(name = "department_id")
        @JsonProperty("department")
        @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "employees" })
        private Department department;

        @ManyToMany(targetEntity = Meeting.class, cascade = { CascadeType.PERSIST,
                        CascadeType.MERGE }, fetch = FetchType.LAZY)
        @JoinTable(name = "meeting_attendees", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "meeting_id"))
        @JsonProperty("meetings")
        @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "attendees" })
        private List<Meeting> meetings;

        @OneToMany(mappedBy = "employee", targetEntity = Attendance.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonProperty("attendance")
        @JsonIgnore
        private List<Attendance> attendace;

        @ManyToMany(targetEntity = Shift.class, cascade = { CascadeType.PERSIST,
                        CascadeType.MERGE }, fetch = FetchType.LAZY)
        @JoinTable(name = "employee_shifts", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "shift_id"))
        @JsonProperty("shifts")
        @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "employees" })
        private List<Shift> shifts;

        @OneToMany(mappedBy = "applicant", targetEntity = LeaveApplication.class, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonProperty("leave_applications")
        @JsonIgnore
        private List<LeaveApplication> leaveApplications;
}
