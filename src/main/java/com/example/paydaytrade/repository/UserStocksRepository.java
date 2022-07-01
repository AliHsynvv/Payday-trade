package com.example.paydaytrade.repository;

import com.example.paydaytrade.entity.UserStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStocksRepository extends JpaRepository<UserStocks, Integer>, JpaSpecificationExecutor<UserStocks> {

}
