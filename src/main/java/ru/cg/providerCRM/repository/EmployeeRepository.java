package ru.cg.providerCRM.repository;

import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFullName(String name);
}
