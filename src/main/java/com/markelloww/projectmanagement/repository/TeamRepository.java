package com.markelloww.projectmanagement.repository;

import com.markelloww.projectmanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Markelloww
 */

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
