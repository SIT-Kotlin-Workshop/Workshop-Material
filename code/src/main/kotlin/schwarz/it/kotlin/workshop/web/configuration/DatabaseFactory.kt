package schwarz.it.kotlin.workshop.web.configuration

import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import schwarz.it.kotlin.workshop.web.entity.ArticlePrices
import schwarz.it.kotlin.workshop.web.entity.Articles
import java.math.BigDecimal
import java.time.Instant

object DatabaseFactory {
    fun startDatabase(
        config: ApplicationConfig,
        testMode: Boolean = false,
    ) {
        val driverClassName = config.property("database.driverClassName").getString()
        val jdbcURL = config.property("database.jdbcURL").getString()
        val username = config.property("database.username").getString()
        val password = config.property("database.password").getString()

        Database.connect(
            url = jdbcURL,
            driver = driverClassName,
            user = username,
            password = password,
        )

        transaction {
            SchemaUtils.create(Articles)
            SchemaUtils.create(ArticlePrices)

            if (!testMode) {
                Articles.insert {
                    it[this.identifier] = "123456"
                    it[this.name] = "Kodee sticker pack"
                    it[this.description] = "A bundle of cute stickers of Kotlin mascot Kodee."
                }

                Articles.insert {
                    it[this.identifier] = "987654"
                    it[this.name] = "Kodee T-shirt"
                    it[this.description] = "A T-shirt with a print of Kotlin mascot Kodee."
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "123456"
                    it[this.price] = BigDecimal(17.34)
                    it[this.startDate] = Instant.parse("2015-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2019-12-31T23:59:59Z")
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "987654"
                    it[this.price] = BigDecimal(42.00)
                    it[this.startDate] = Instant.parse("2020-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2022-12-31T23:59:59Z")
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "123456"
                    it[this.price] = BigDecimal(13.37)
                    it[this.startDate] = Instant.parse("2020-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2029-12-31T23:59:59Z")
                }
            }
        }
    }
}
