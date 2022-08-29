package msa.api.user.controller;

import msa.api.user.vo.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
public interface CodeRepository extends JpaRepository<Code, Long>  {
}
