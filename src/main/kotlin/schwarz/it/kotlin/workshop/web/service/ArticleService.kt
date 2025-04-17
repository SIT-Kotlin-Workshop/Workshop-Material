package schwarz.it.kotlin.workshop.web.service

import schwarz.it.kotlin.workshop.web.dto.ArticleCreationDTO
import schwarz.it.kotlin.workshop.web.entity.Article
import schwarz.it.kotlin.workshop.web.repository.ArticleRepository
import kotlin.random.Random

object ArticleService {
    fun findAllBySearchString(searchString: String? = null) =
        if (searchString.isNullOrBlank()) {
            ArticleRepository.findAll()
        } else {
            ArticleRepository.findAllBySearchString(searchString.trim())
        }

    fun createArticle(dto: ArticleCreationDTO): Article {
        require(dto.name.isNotBlank())

        val identifier = Random.nextLong(100_000, 1_000_000).toString()

        val article =
            Article(
                identifier = identifier,
                name = dto.name,
                description = dto.description,
            )

        ArticleRepository.save(article)

        return article
    }
}
