package com.zrp.toyproject0.domain.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.zrp.toyproject0.domain.order.entity.Order;
import com.zrp.toyproject0.domain.order.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Query("select o from Order o join fetch o.item where o.member.username = :username and o.status = :status")
    Page<Order> findByUsernameAndStatus(@Param("username") String username, 
                                        @Param("status") OrderStatus status, 
                                        Pageable pageable);

}
