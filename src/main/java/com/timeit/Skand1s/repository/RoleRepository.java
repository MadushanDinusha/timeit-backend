package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.Role;
import com.timeit.Skand1s.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
