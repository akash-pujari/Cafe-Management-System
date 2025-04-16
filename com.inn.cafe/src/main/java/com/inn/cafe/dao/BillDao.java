package com.inn.cafe.dao;

import com.inn.cafe.pojo.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<Bill,Integer> {
}
