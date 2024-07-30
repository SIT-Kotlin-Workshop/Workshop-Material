@file:Suppress("ktlint:standard:filename")

package schwarz.it.kotlin.workshop.basic.nullability

data class Company(
    val companyName: String,
    val numberOfEmployees: Int,
)

data class Worker(
    val name: String,
    val company: Company,
) {
    val format = "$name is working for company ${company.companyName} with ${company.numberOfEmployees} employees"
}

private fun main() {
    // Illegal in Kotlin
    // val worker : Worker = null
    val worker: Worker? = null

    // Illegal in Kotlin
    // println(worker.name)

    // Safe navigation
    println(worker?.name)
    println(worker?.company?.companyName)

    // Also works for function calls - result is always nullable
    val string = worker?.format
    println(string)

    // Elvis operator for providing an alternative
    println(worker?.name ?: "Unknown")

    if (worker != null) {
        // Smart-casting to non-nullable type
        println(worker.name)
    }
}
