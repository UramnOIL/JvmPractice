package com.uramnoil.nukkit.friend.repository

import com.uramnoil.nukkit.friend.repository.UsersDAO.User
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select

class FriendRelationsDAO {
	object FriendRelations : IntIdTable() {
		val offerer = reference("offerer",
			UsersDAO.Users
		)
		val offeree = reference("offeree",
			UsersDAO.Users
		)
	}

	class FriendRelation(id: EntityID<Int>) : IntEntity(id) {
		companion object : IntEntityClass<FriendRelation>(
			FriendRelations
		)
		var offerer by User referencedOn FriendRelations.offerer
		var offeree by User referencedOn FriendRelations.offeree
	}

	fun makeFriends(user1: User, user2: User) {
		FriendRelation.new {
			offerer = user1
			offeree = user2
		}
		FriendRelation.new {
			offerer = user2
			offeree = user1
		}
	}

	fun offer(offerer: User, offeree: User) {
		FriendRelation.new {
			this.offerer = offerer
			this.offeree = offeree
		}
	}

	fun unoffer(offerer: User, offeree: User) {
		FriendRelations.deleteWhere {
			(FriendRelations.offerer eq offerer.id.value) and (FriendRelations.offeree eq offeree.id.value)
		}
	}

	fun hasOffered(offerer: User, offeree: User): Boolean = FriendRelations.select {
		(FriendRelations.offerer eq offerer.id.value) and (FriendRelations.offeree eq offeree.id.value)
	}.count() == 1

	fun areFriends(user1: User, user2: User): Boolean = FriendRelations.select {
		((FriendRelations.offerer eq user1.id.value) and (FriendRelations.offeree eq user2.id.value)) or ((FriendRelations.offerer eq user2.id.value) and (FriendRelations.offeree eq user1.id.value))
	}.count() == 2
}