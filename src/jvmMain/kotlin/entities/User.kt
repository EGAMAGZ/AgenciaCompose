package entities

import java.io.Serializable

data class User(val username: String, val password: String): Serializable {
    override fun toString(): String {
        return "User [username=$username, password=$password]"
    }
}
