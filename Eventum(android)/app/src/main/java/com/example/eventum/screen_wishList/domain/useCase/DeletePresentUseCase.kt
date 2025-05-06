package com.example.eventum.screen_wishList.domain.useCase

import android.util.Log
import com.example.eventum.domain.model.Operation
import com.example.eventum.screen_presents.domain.model.Present
import com.example.eventum.screen_presents.domain.repository.PresentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeletePresentUseCase @Inject constructor(
    private val repository: PresentsRepository
) {
    operator fun invoke(present: Present): Flow<Operation> =
        flow{
            try {
                emit(Operation.Loading())
                repository.deletePresent(present)
                emit(Operation.Success())
            }
            catch (e: IOException) {
                emit(Operation.Error("Couldn't reach server"))
            }
            catch (e: Exception) {
                emit(Operation.Error("Unexpected error occurred"))
            }
        }
}