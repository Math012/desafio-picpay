package com.math012.Picpay.repository;

import com.math012.Picpay.model.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferModel, Long> {
}
