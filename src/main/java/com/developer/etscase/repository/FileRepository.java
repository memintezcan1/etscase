package com.developer.etscase.repository;

import com.developer.etscase.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query(value = " SELECT *\n" +
            "FROM tbl_upload_file tuf\n" +
            "WHERE (cast(cast(:aktif as text) as boolean) is null or tuf.aktif = (cast(cast(:aktif as text) as boolean)))\n" +
            "    AND (cast(cast(:userId as text) as bigint) is null or tuf.user_id = (cast(cast(:userId as text) as bigint))) ", nativeQuery = true)
    List<FileEntity> getAllFileByUserId(Long userId, Boolean aktif);
}
