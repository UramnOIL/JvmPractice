package com.uramnoil.nukkit.friend.repository

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.deleteWhere

class UsersDAO {
	object Users : IntIdTable() {
		val name = varchar("name", 16)
	}

	class User(id: EntityID<Int>) : IntEntity(id) {
		companion object : IntEntityClass<User>(
			Users
		)
		var name by Users.name
	}

	fun createUser(name: String): User = User.new { this.name = name }
	fun deleteUser(user: User) = Users.deleteWhere { Users.name eq user.name }
	fun findUser(name: String): User? = User.find { Users.name eq name }.firstOrNull()
}