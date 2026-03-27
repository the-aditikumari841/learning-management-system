package com.learningportal.learningportalproject.favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavRepository extends JpaRepository<FavEntity, Long> {

}
