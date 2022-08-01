package com.company.paydaytrade.repository;

import com.company.paydaytrade.entity.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStocksRepository extends JpaRepository<UserStock, Integer>, JpaSpecificationExecutor<UserStock> {
    Optional<UserStock> findUserStocksByUserId(Integer id);
}
