package com.kolushkin.composition.domain.usecases

import com.kolushkin.composition.domain.entity.GameSettings
import com.kolushkin.composition.domain.entity.Level
import com.kolushkin.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}