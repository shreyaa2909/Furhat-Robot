package furhatos.app.iisembodiment.flow

import furhatos.flow.kotlin.*
import furhatos.util.*

/* We define two states
* Idle state
* Greetings state*/

val Idle: State = state {
    /* Handles the resting behavior of the robot.
    * The state acts on users entering and if so
    * transitions to another active state */

    init {
        /* This is run the first time only.
        * Setting up the initial voice and
        * check if we have any users. */

        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        // Resets Furhat attention.
        // It runs everytime the state is transitioned to
        furhat.attendNobody()
    }

    onUserEnter {
        // Attends the user and goes to the state Start
        furhat.attend(it)
        goto(Start)
    }
}

val Greetings: State = state {
    /* Handles basic user management during an interaction
    * allowing the robot to behave in a socially appropriate
    * manner as users come and go from the interaction state.*/

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}
