package com.demo.Visitor.access.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.Visitor.access.model.WeeklyReport;

public interface ReportRepository extends MongoRepository<WeeklyReport, String> {

	Optional<WeeklyReport> findByReportId(int random_id);

	List<WeeklyReport> findByManagerEmpId(String empId);

}
