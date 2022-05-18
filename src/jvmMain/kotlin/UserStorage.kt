import entities.User

class UserStorage : FileAccess() {

    private val fileName = "usuario.txt"
    private var user: User = this.load(fileName)

    fun new(user: User) {
        this.user = user
        this.dump(fileName, user)
    }

    fun validate(password: String) =
        user.password == password
}