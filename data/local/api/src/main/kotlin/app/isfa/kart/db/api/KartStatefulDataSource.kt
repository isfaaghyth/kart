package app.isfa.kart.db.api

interface KartStatefulDataSource {

    suspend fun safeExecute(
        invoke: suspend () -> Int,

        preCondition: suspend () -> Boolean,
        vararg errorMessages: String
    ): Result<Boolean> {
        if (!preCondition()) return Result.failure(Throwable(errorMessages[0]))

        return if (invoke() > 0) {
            Result.success(true)
        } else {
            Result.failure(Throwable(errorMessages[0]))
        }
    }
}