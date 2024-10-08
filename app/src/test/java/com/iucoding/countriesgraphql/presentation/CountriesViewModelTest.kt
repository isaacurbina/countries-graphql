package com.iucoding.countriesgraphql.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.iucoding.countriesgraphql.MainCoroutineExtension
import com.iucoding.countriesgraphql.domain.model.DetailedCountry
import com.iucoding.countriesgraphql.domain.model.SimpleCountry
import com.iucoding.countriesgraphql.domain.usecase.GetCountriesUseCase
import com.iucoding.countriesgraphql.domain.usecase.GetCountryUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class CountriesViewModelTest {

    private lateinit var viewModel: CountriesViewModel
    private val getCountriesUseCase: GetCountriesUseCase = mockk()
    private val getCountryUseCase: GetCountryUseCase = mockk()

    // region common
    @BeforeEach
    fun setUp() {
        coEvery { getCountriesUseCase.execute() } coAnswers {
            delay(10)
            listOf(simpleCountry)
        }
        coEvery { getCountryUseCase.execute(any()) } coAnswers {
            delay(10)
            detailedCountry
        }
        viewModel = CountriesViewModel(
            getCountriesUseCase = getCountriesUseCase,
            getCountryUseCase = getCountryUseCase
        )
    }

    @AfterEach
    fun cleanUp() {
        clearAllMocks()
    }
    // endregion

    // region tests
    @Test
    fun `init fetches list of countries`() = runTest {
        viewModel.state.test {
            viewModel.init()
            awaitItem() // ignore first emission

            val secondEmission = awaitItem()
            assertThat(secondEmission.isLoading).isTrue()

            val thirdEmission = awaitItem()
            assertThat(thirdEmission.isLoading).isFalse()
            assertThat(thirdEmission.countries).isNotEmpty()
        }
    }

    @Test
    fun `selectCountry fetches data from use case`() = runTest {
        viewModel.state.test {
            viewModel.selectCountry("US")
            awaitItem() // ignore first emission
            val emission = awaitItem()
            assertThat(emission.selectedCountry).isNotNull()
            assertThat(emission.selectedCountry).isEqualTo(detailedCountry)
        }
    }

    @Test
    fun `dismissDialog removes detailed country data from state`() = runTest {
        viewModel.state.test {
            viewModel.dismissCountryDialog()
            awaitItem() // ignore first emission
            val emission = awaitItem()
            assertThat(emission.selectedCountry).isNull()
        }
    }
    // endregion

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()

        private val simpleCountry = SimpleCountry(
            code = "US",
            name = "United States",
            emoji = "\uD83C\uDDFA\uD83C\uDDF8",
            capital = "Washington D.C."
        )

        private val detailedCountry = DetailedCountry(
            code = "CA",
            name = "Canada",
            emoji = "\uD83C\uDDE8\uD83C\uDDE6",
            capital = "Ottawa",
            continent = "North America",
            currency = "CAD",
            languages = listOf("English", "French")
        )
    }
}
