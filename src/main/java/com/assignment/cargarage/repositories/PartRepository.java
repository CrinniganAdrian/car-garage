package com.assignment.cargarage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cargarage.dto.Part;

@Repository
public interface PartRepository extends JpaRepository<Part,Long>
{
}
