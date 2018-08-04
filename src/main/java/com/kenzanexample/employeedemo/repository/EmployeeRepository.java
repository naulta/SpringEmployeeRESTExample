package com.kenzanexample.employeedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;




import com.kenzanexample.employeedemo.domain.Employee;


//Inherit JpaRepository methods

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>, JpaSpecificationExecutor<Employee> {

	// Only Admin role is authorized for delete
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Long ID);

	// Use JPQL to filter out ACTIVE employes for GET 

	@Query(value = "SELECT * FROM Employee e WHERE e.status = 'ACTIVE'", nativeQuery = true)
	List<Employee> findAllActive();

	@Query(value = "SELECT * FROM Employee e WHERE e.id = :id and e.status = 'ACTIVE'", nativeQuery = true)
	Optional <Employee> findActiveByID(Long id);
	}
