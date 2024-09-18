package br.com.taxone.kotlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.User;

@Repository
interface UserRepository : JpaRepository<User, Int>{

	fun findByName(username: String): User

	@Query("update User u set u.name = :name where u.id = :id")
	@Modifying
	fun updateName(@Param("name") name: String, @Param("id") id: Int)

}
