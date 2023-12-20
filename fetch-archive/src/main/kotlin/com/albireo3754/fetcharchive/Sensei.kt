package com.albireo3754.fetcharchive

import jakarta.persistence.Entity

@Entity
class Sensei(val userId: Long, val students) {
}