package org.mgmt.system.employee;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    Optional<Employee>	findByemailId(String emailId);
}
