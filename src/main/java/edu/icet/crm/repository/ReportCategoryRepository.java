package edu.icet.crm.repository;

import edu.icet.crm.dto.ReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory,String> {
}
