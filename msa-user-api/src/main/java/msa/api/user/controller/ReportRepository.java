package msa.api.user.controller;


import msa.api.user.vo.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//PagingAndSortingRepository
//JpaRepository
@RepositoryRestResource
public interface ReportRepository extends JpaRepository<Report, Long> {

    // 일반 JPQL쿼리, from뒤는 엔티티 명 (소문자로 할 시 에러)
    @Query(value = "select m from Member m where id < 3")
    public List<Report> selectAllJPQL1();
//
//    // 일반 SQL쿼리
//    @Query(value = "select m.* from Member m", nativeQuery = true)
//    public List<Report> selectAllSQL1();
//
//    // SQL 일반 파라미터 쿼리, @Param 사용 O
//    @Query(value = "select m.* from Member m where id > :id", nativeQuery = true)
//    public List<Report> selectSQLById2(@Param(value = "id") Long id);
//
//    // SQL 객체 파라미터 쿼리
//    @Query(value = "select m.* from Member m where id > :#{#paramMember.id}", nativeQuery = true)
//    public List<Member> selectSQLById3(@Param(value = "paramMember") Member member);

}

