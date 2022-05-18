import entities.User

class UserStorage : FileAccess {

    private val fileName = "usuario.txt"
    private var user: User

    constructor() {
        this.user = this.load(fileName)
        println(user)
    }

    fun new(user: User) {
        this.user = user
        this.dump(fileName, user)
    }

    fun isValid(username: String, password: String) =
        user.password == password && user.username == username


}