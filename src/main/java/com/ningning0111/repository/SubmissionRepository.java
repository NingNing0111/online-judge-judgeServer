package com.ningning0111.repository;

import com.ningning0111.model.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: com.ningning0111.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 13:22
 * @Description:
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {
}
