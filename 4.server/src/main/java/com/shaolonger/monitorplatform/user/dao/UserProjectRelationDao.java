package com.shaolonger.monitorplatform.user.dao;

import com.shaolonger.monitorplatform.user.entity.UserProjectRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProjectRelationDao extends JpaRepository<UserProjectRelationEntity, Long> {

    List<UserProjectRelationEntity> findByUserId(Long userId);

    List<UserProjectRelationEntity> findByProjectId(Long projectId);

    void deleteByUserIdAndProjectId(Long userId, Long projectId);

    void deleteAllByProjectId(Long projectId);
}
