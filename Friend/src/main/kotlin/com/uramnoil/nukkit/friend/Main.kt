package com.uramnoil.nukkit.friend

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
	Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

	transaction {
		addLogger(StdOutSqlLogger)

		SchemaUtils.create(Users)

		val hoge = User.new {
			name = "hoge"
		}

		val fuga = User.new {
			name = "fuga"
		}

		println("Users: ${Users.selectAll()}")
	}
}


