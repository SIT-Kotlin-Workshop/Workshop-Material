@file:Suppress("ktlint:standard:filename")

package schwarz.it.kotlin.workshop.basic.immutability

/**
 * A regular (non-data) class.
 */
class Student(
    val name: String,
    val age: Int,
)

/**
 * A data class.
 */
data class Teacher(
    val name: String,
    val age: Int,
)

private fun main() {
    val student = Student("Bob", 18)
    val studentCopy = Student("Bob", 18)

    val teacher = Teacher("Alice", 36)
    val teacherCopy = Teacher("Alice", 36)

    // 1. Check if `student` is equal to `studentCopy`. Do the same for the `Teacher` objects.

    // 2. Print `student` and `teacher` to the console and observe the difference.

    // 3. It was Bob's and Alice's birthday and they are now one year older! Try to increment their age.

    // None of these work because `age` is a `val` in both class definitions.
    // student.age++
    // teacher.age = 37

    // 4. Define new instances `studentUpdated` and `teacherUpdated` with the incremented age.
}
