package io.caffinatedsquirrel.synopsis.services

class ValidationResult(val hasError: Boolean, val message: String) {

    companion object {
        fun valid(): ValidationResult {
            return ValidationResult(false, "")
        }

        fun invalid(message: String): ValidationResult {
            return ValidationResult(true, message)
        }
    }
}