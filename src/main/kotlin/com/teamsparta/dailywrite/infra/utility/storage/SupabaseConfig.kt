package com.teamsparta.dailywrite.infra.utility.storage

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SupabaseConfig {

    @Value("\${supabase.url}")
    lateinit var supabaseUrl: String

    @Value("\${supabase.key}")
    lateinit var supabaseKey: String


    @Bean
    fun supabaseClient() : SupabaseClient {
        return createSupabaseClient(supabaseUrl, supabaseKey) {
            install (Storage)
        }
    }


}