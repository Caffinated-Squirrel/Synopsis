package io.caffinatedsquirrel.synopsis.controllers

import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import org.reactivestreams.Publisher
import io.micronaut.security.authentication.AuthenticationFailed
import io.reactivex.Flowable
import java.util.ArrayList
import io.micronaut.security.authentication.UserDetails
import javax.inject.Singleton

@Singleton
class AuthController: AuthenticationProvider {

    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>?): Publisher<AuthenticationResponse> {
        return if (authenticationRequest?.identity != null &&
                authenticationRequest.identity == "sherlock" &&
                authenticationRequest.secret != null &&
                authenticationRequest.secret == "password") {
            Flowable.just<UserDetails>(UserDetails(authenticationRequest.identity as String, ArrayList())) as Publisher<AuthenticationResponse>
        } else Flowable.just<AuthenticationFailed>(AuthenticationFailed()) as Publisher<AuthenticationResponse>
    }

}