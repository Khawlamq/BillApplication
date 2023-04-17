package com.assignment.billapplication.repository;
import com.assignment.billapplication.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillRepository extends JpaRepository<Bill, Long> {
}
