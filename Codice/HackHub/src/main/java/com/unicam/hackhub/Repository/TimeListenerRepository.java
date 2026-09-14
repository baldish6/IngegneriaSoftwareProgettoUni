package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Util.ITimeListener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeListenerRepository extends JpaRepository<ITimeListener,Long> {
}
