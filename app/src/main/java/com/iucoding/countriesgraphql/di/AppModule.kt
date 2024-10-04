package com.iucoding.countriesgraphql.di

import com.apollographql.apollo.ApolloClient
import com.iucoding.countriesgraphql.data.ApolloCountryClient
import com.iucoding.countriesgraphql.domain.CountryClient
import com.iucoding.countriesgraphql.domain.usecase.GetCountriesUseCase
import com.iucoding.countriesgraphql.domain.usecase.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(client: ApolloClient): CountryClient {
        return ApolloCountryClient(client)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(client: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(client)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(client: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(client)
    }
}
