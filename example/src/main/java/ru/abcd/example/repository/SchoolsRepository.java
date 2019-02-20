package ru.abcd.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущностям {@link ru.abcd.example.repository.School}
 * @author dmitry
 *
 */
interface SchoolsRepository extends JpaRepository<School, Integer>  {

}
